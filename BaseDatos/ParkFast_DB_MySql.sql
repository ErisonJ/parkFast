delimiter $
	create database if not exists db_scp;
$
delimiter $
	use db_scp;
$
delimiter $
	create table tipos_usuarios(
		codigo int not null primary key auto_increment,
        descripcion varchar(150) not null,
        estado bit not null default 1 check(estado = 1 or estado = 0)
    ) ENGINE=INNODB;
$
delimiter $
	create table usuarios(
		codigo int not null primary key auto_increment,
		tipo_usuario int not null,
        login varchar(250) not null,
        clave varchar(250) not null,
        fecha_creacion datetime not null default now(),
        codigo_usuario int not null,
        numero_intentos int not null default 5,
        fecha_exp_clave date not null,
        estado bit not null default 1 check(estado = 1 or estado = 0),
        foreign key(codigo_usuario) references usuarios(codigo),
        foreign key(tipo_usuario) references tipos_usuarios(codigo)
    ) ENGINE=INNODB;
$
delimiter $
	create table tipos_parqueos(
		codigo int not null primary key auto_increment,
        descripcion varchar(150) not null,
        cantidad int not null,
        precio numeric(11,2) not null,
        codigo_usuario int not null,
        fecha_creacion datetime not null default now(),
        estado bit not null default 1 check(estado = 1 or estado = 0),
        foreign key(codigo_usuario) references usuarios(codigo)
    ) ENGINE=INNODB;
$
delimiter $
	create table tickets(
		numero int not null primary key auto_increment,
        tipo_parqueo int not null,
        fecha_entrada datetime not null default now(),
        fecha_salida datetime,
        horas int not null check(horas>0),
        precio numeric(11,2) not null default 0,
        monto numeric(11,2) not null default 0,
        balance numeric(11,2) not null default 0,
        estado bit not null default 1 check(estado = 1 or estado = 0),
        foreign key(tipo_parquero) references tipos_parqueros(codigo)
    ) ENGINE=INNODB;
$
delimiter $
	create table pagos(
		numero int not null primary key auto_increment,
        fecha datetime not null default now(),
        numero_volante int not null,
        monto numeric(11,2) not null,
        codigo_usuario int not null,
        estado bit not null default 1 check(estado = 1 or estado = 0),
        foreign key(codigo_usuario) references usuarios(codigo),
        foreign key(numero_volante) references tickets(numero)
    ) ENGINE=INNODB;
$
delimiter $
	create table tipos_pagos(
		codigo int not null primary key auto_increment,
        descripcion varchar(150) not null,
        estado bit not null default 1 check(estado = 1 or estado = 0)
    ) ENGINE=INNODB;
$
delimiter $
	create table detalles_pagos(
		id int not null auto_increment,
        numero_pago int not null,
        tipo_pago int not null,
        monto numeric(11,2) not null,
        estado bit not null default 1 check(estado = 1 or estado = 0),
        primary key(id,numero_pago),
        foreign key(numero_pago) references pagos(numero),
        foreign key(tipo_pago) references tipos_pagos(codigo)
    ) ENGINE=INNODB;
$
delimiter $
	create table cierres_cajas(
		numero int not null primary key auto_increment,
        fecha datetime not null default now(),
        codigo_usuario int not null,
        monto_total numeric(11,2) not null,
        estado bit not null default 1 check(estado = 1 or estado  = 0),
        foreign key(codigo_usuario) references usuarios(codigo)
    ) ENGINE=INNODB;
$
delimiter $
	create table detalles_cierres(
		id int not null auto_increment,
		numero int not null,
        numero_pago int not null,
        monto_cheques numeric(11,2) not null default 0,
        monto_efectivos numeric(11,2) not null default 0,
        monto_tarjetas numeric(11,2) not null default 0,
        monto_bonos numeric(11,2) not null default 0,
        subtotal numeric(11,2) not null default 0,
        estado bit not null default 1 check(estado = 1 or estado  = 0),
        foreign key(numero_pago) references pagos(numero),
        foreign key(numero) references cierres_cajas(numero),
        primary key(id,numero,numero_pago)
    ) ENGINE=INNODB;
$

delimiter $
	create table estados_parqueos(
		codigo int not null auto_increment primary key,
        descripcion varchar(150) not null,
        estado bit not null default 1 check(estado )
    ) ENGINE=INNODB;
$

delimiter $
	create table parqueos_averiados(
		id int not null primary key auto_increment,
        detalles varchar(250) not null default '',
        estado_parqueo int not null,
        fecha_registro datetime not null default now(),
        fecha_reparacion datetime,
        costo_reparacion decimal(11,2) default 0,
        foreign key(estado_parqueo) references estados_parqueos(codigo)
    ) ENGINE=INNODB;
$


-- aqui empiezan los procedimientos
delimiter $
	create procedure p_login(
		_login varchar(250),
        _clave varchar(250)
    )
    begin
		if exists(select codigo from usuarios where login = _login and clave = _clave and estado = 1)
        then
			select u.codigo,u.login,tu.descripcion as tipo_usuario,1 as existe from usuarios as u inner join tipos_usuarios as tu on u.tipo_usuario = tu.codigo where login = _login and clave = _clave and u.estado = 1 limit 1;
		else
			select 0 as existe;
        end if;
    end;
$
delimiter $
	create procedure p_generar_ticker(_tipo_parqueo varchar(150))
    begin
		declare _numero int;
        declare _codigo_tipo int;
        declare _precio int;
		set _codigo_tipo = (select codigo from tipos_parqueos where descripcion = _tipo_parqueo and estado = 1);
		set _precio = (select precio from tipos_parqueos where codigo = _codigo_tipo and estado = 1);
        insert into tickets(tipo_parqueo,fecha_entrada,precio) values(_codigo_tipo,now(),_precio);
        set _numero = (select max(numero) from tickets where estado = 1);
        select numero,_tipo_parqueo as tipo_parqueo,DATE_FORMAT(fecha_entrada,'%d-%m-%Y a las %h:%i %p') as fecha_completa,date(fecha_entrada) as fecha_entrada,time(fecha_entrada) as hora,_precio as precio from tickets where numero = _numero and estado = 1;
    end;
$
delimiter $
	create procedure p_listar_parqueo(_numero int)
    begin
		declare _monto decimal(11,2) default 0;
		declare _horas int default 0;
		if exists(select numero from tickets where numero = _numero and estado = 1)
        then
			set _horas = (select TIMESTAMPDIFF(MINUTE,fecha_entrada,now())/60 from tickets where estado = 1 and numero = _numero);
            if(_horas < 1)
			then
				set _monto  = 25;
			else
				set _monto  = (select _horas * precio as monto from tickets where estado = 1 and numero = _numero);
			end if;
			select 1 as existe,p.numero,tp.descripcion as tipo_parqueo,DATE_FORMAT(p.fecha_entrada,'%d-%m-%Y a las %h:%i %p') as fecha_entrada, 
            DATE_FORMAT(now(),'%d-%m-%Y a las %h:%i %p') as fecha_salida,now() as fecha_completa,_horas as horas,p.precio,_monto as monto,
            p.estado from tickets as p inner join tipos_parqueos as tp where p.numero = _numero and p.estado = 1;
        else
			select 0 as existe, 'Tickets ya ha sido pagado o no existe!' as mensaje;
        end if;
    end;
$
delimiter $
	create procedure p_registrar_pagos(_numero_volante int,_monto decimal(11,2),_codigo_usuario int)
    begin
		declare numero_pago int default 0;
        insert into pagos(numero_volante,monto,codigo_usuario) values(_numero_volante,_monto,_codigo_usuario);
        set numero_pago = (select max(numero) from pagos where estado  = 1);
        select numero_pago as numero_pago; 
    end;
$
delimiter $
	create procedure p_actuaizar_parqueos(_numero int,_fecha_salida varchar(25),_horas int,_monto decimal(11,2))
    begin
		if exists(select numero from tickets where numero = _numero and estado = 1)
        then
			update tickets set fecha_salida = _fecha_salida,horas = _horas,monto = _monto, estado = 0 where numero = _numero;
            select _numero as numero, 1 as actualizado;
        end if;
    end;
$
delimiter $
	create procedure p_actualizar_detalles_pagos(_numero_pago int,_tipo_pago int,_subtotal decimal(11,2))
    begin
		insert into detalles_pagos(numero_pago,tipo_pago,monto)values(_numero_pago,_tipo_pago,_subtotal);
        select 1 as insertado;
    end;
$
delimiter $
	create procedure p_listarUsuarios(_text varchar(250) )
    begin
		if(_text != '')
        then
			select u.codigo,u.tipo_usuario as codigo_tipo,tu.descripcion as tipo_usuario,u.login,u.fecha_creacion,
            u.codigo_usuario,u.numero_intentos,u.estado
            from usuarios as u inner join tipos_usuarios as tu on u.tipo_usuario = tu.codigo
            where (u.codigo like concat('%',_text,'%') or u.login like  concat('%',_text,'%') or tu.descripcion like  concat('%',_text,'%'));
        else
			select u.codigo,u.tipo_usuario as codigo_tipo,tu.descripcion as tipo_usuario,u.login,u.fecha_creacion,
            u.codigo_usuario,u.numero_intentos,u.estado
            from usuarios as u inner join tipos_usuarios as tu on u.tipo_usuario = tu.codigo 
             limit 10;
        end if;
    end;
$
delimiter $
	create procedure p_actualizarUsuarios(_codigo int,_login varchar(250),_clave varchar(250),_tipo_usuario varchar(150),_numero_intentos int,_estado bit,_codigo_usuario int)
    begin
		declare _codtip int;
        declare mensaje varchar(500) default '';
        set _codtip = (select codigo from tipos_usuarios where descripcion = _tipo_usuario);
		if exists(select codigo from usuarios where codigo = _codigo)
        then
			if(_clave != '')
            then
				update usuarios set tipo_usuario = _codtip, clave = _clave,numero_intentos = _numero_intentos,fecha_exp_clave = DATE_ADD(CURDATE(), INTERVAL 90 DAY),estado = _estado where codigo = _codigo;
				set mensaje = 'Datos y Clave actualizados exitosamente!';
			else
				update usuarios set tipo_usuario = _codtip,numero_intentos = _numero_intentos,estado = _estado where codigo = _codigo;
				set mensaje = 'Datos Actualizados exitosamente!';
            end if;
        else
			insert into usuarios(tipo_usuario,login,clave,codigo_usuario,numero_intentos,fecha_exp_clave)values(_codtip,_login,_clave,_codigo_usuario,_numero_intentos, DATE_ADD(CURDATE(), INTERVAL 90 DAY));
            set mensaje = 'Datos insertados exitosamente';
            set _codigo = (select  max(codigo) from usuarios);
        end if;
        select mensaje as mensaje;
    end;
$
delimiter $
	create procedure p_listarTiposUsuarios(_buscar varchar(250))
    begin
		if(_buscar != '')
        then
			select codigo,descripcion,estado from tipos_usuarios where (codigo like concat('%',_buscar,'%') or descripcion  like concat('%',_buscar,'%') or estado  like concat('%',_buscar,'%'));
        else
			select codigo,descripcion,estado from tipos_usuarios limit 10;
        end if;
    end;
$
delimiter $
	create procedure p_listarTiposPagos(_buscar varchar(250))
    begin
		if(_buscar != '')
        then
			select codigo,descripcion,estado from tipos_pagos where (codigo like concat('%',_buscar,'%') or descripcion  like concat('%',_buscar,'%') or estado  like concat('%',_buscar,'%'));
        else
			select codigo,descripcion,estado from tipos_pagos limit 10;
        end if;
    end;
$
delimiter $
	create procedure p_listarTiposParqueos(_buscar varchar(250))
    begin
		if(_buscar != '')
        then
			select codigo,descripcion,cantidad,precio,codigo_usuario,fecha_creacion,estado from tipos_parqueos where (codigo like concat('%',_buscar,'%') or descripcion like concat('%',_buscar,'%') or estado like concat('%',_buscar,'%'));
        else
			select codigo,descripcion,cantidad,precio,codigo_usuario,fecha_creacion,estado from tipos_parqueos limit 10;
        end if;
    end;
$
delimiter $
	create procedure p_actualizarTiposUsuarios(_codigo int,_descripcion varchar(150),_estado bit)
    begin
		declare _mensaje varchar(250) default '';
		if exists(select codigo from tipos_usuarios where codigo = _codigo)
        then
			update tipos_usuarios set descripcion = _descripcion, estado = _estado where codigo = _codigo;
            set _mensaje = 'Datos Actualizados exitosamente!';
        else
			insert into tipos_usuarios(descripcion)values(_descripcion);
            set _codigo = (select max(codigo) from tipos_usuarios);
            set _mensaje ='Datos insertados exitosamente!';
        end if;
        select _mensaje as mensaje, codigo,descripcion,estado from tipos_usuarios where codigo = _codigo;
    end;
$
delimiter $
	create procedure p_actualizarTiposPagos(_codigo int,_descripcion varchar(150),_estado bit)
    begin
		declare _mensaje varchar(250) default '';
		if exists(select codigo from tipos_pagos where codigo = _codigo)
        then
			update tipos_pagos set descripcion = _descripcion, estado = _estado where codigo = _codigo;
            set _mensaje = 'Datos Actualizados exitosamente!';
        else
			insert into tipos_pagos(descripcion)values(_descripcion);
            set _codigo = (select max(codigo) from tipos_pagos);
            set _mensaje ='Datos insertados exitosamente!';
        end if;
        select _mensaje as mensaje, codigo,descripcion,estado from tipos_pagos where codigo = _codigo;
    end;
$
delimiter $
	create procedure p_actualizarTiposParqueos(_codigo int,_descripcion varchar(150),_cantidad int,_precio numeric(11,2),_codigo_usuario int,_estado bit)
    begin
		declare _mensaje varchar(250) default '';
		if exists(select codigo from tipos_parqueos where codigo = _codigo)
        then
			update tipos_parqueos set descripcion = _descripcion,cantidad = _cantidad,precio = _precio, estado = _estado where codigo = _codigo;
            set _mensaje = 'Datos Actualizados exitosamente!';
        else
			insert into tipos_parqueos(descripcion,cantidad,precio,codigo_usuario)values(_descripcion,_cantidad,_precio,_codigo_usuario);
            set _codigo = (select max(codigo) from tipos_parqueos);
            set _mensaje ='Datos insertados exitosamente!';
        end if;
        select _mensaje as mensaje, codigo,descripcion,cantidad,precio,codigo_usuario,fecha_creacion,estado from tipos_parqueos where codigo = _codigo;
    end;
$

delimiter $
	insert into tipos_pagos(descripcion)values('efectivo'),('cheque'),('tarjeta'),('bonos');
$
delimiter $
   insert into tipos_usuarios(descripcion) values('administrador'),('cajero');
$

delimiter $
   insert into usuarios(tipo_usuario,login,clave,codigo_usuario,numero_intentos,fecha_exp_clave) values(1,'admin',sha1('admin'),1,5,DATE_ADD(CURDATE(), INTERVAL 90 DAY));
$