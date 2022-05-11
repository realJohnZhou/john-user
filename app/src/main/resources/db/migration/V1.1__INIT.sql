create table `user`
(
    `id`          varchar(20)  not null comment 'id',
    `create_time` datetime    default current_timestamp comment 'create time',
    `update_time` datetime    default current_timestamp comment 'update time',
    `create_by`   varchar(20)  not null comment 'create user id',
    `update_by`   varchar(20)  not null comment 'update user id',
    `username`    varchar(50)  not null comment 'username for login',
    `password`    varchar(100) not null comment 'password for login',
    `name`        varchar(20)  not null comment 'name',
    `phone`       varchar(20) default null comment 'phone',
    `email`       varchar(50) default null comment 'email',
    primary key (`id`)
);

create table `menu`
(
    `id`          varchar(20)  not null comment 'id',
    `create_time` datetime default current_timestamp comment 'create time',
    `update_time` datetime default current_timestamp comment 'update time',
    `create_by`   varchar(20)  not null comment 'create user id',
    `update_by`   varchar(20)  not null comment 'update user id',
    `code`        varchar(20)  not null comment 'code',
    `name`        varchar(20)  not null comment 'name',
    `path`        varchar(100) not null comment 'router path',
    `type`        varchar(20)  not null comment 'menu or button',
    `parent`      varchar(20)  not null comment 'parent id',
    `icon`        varchar(50)  not null comment 'icon component',
    primary key (`id`)
);

create table `role`
(
    `id`          varchar(20) not null comment 'id',
    `create_time` datetime default current_timestamp comment 'create time',
    `update_time` datetime default current_timestamp comment 'update time',
    `create_by`   varchar(20) not null comment 'create user id',
    `update_by`   varchar(20) not null comment 'update user id',
    `code`        varchar(20) not null comment 'code',
    `name`        varchar(20) not null comment 'name',
    primary key (`id`)
);

create table `permission`
(
    `id`          varchar(20) not null comment 'id',
    `create_time` datetime default current_timestamp comment 'create time',
    `update_time` datetime default current_timestamp comment 'update time',
    `create_by`   varchar(20) not null comment 'create user id',
    `update_by`   varchar(20) not null comment 'update user id',
    `menu_id`     varchar(20) not null comment 'menu id',
    `role_id`     varchar(20) not null comment 'role id',
    primary key (`id`)
);
create table `user_role`
(
    `id`          varchar(20) not null comment 'id',
    `create_time` datetime default current_timestamp comment 'create time',
    `update_time` datetime default current_timestamp comment 'update time',
    `create_by`   varchar(20) not null comment 'create user id',
    `update_by`   varchar(20) not null comment 'update user id',
    `user_id`     varchar(20) not null comment 'user id',
    `role_id`     varchar(20) not null comment 'role id',
    primary key (`id`)
);