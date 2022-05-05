create table `user`
(
    `id`          varchar(20)   not null comment 'id',
    `create_time` datetime    default current_timestamp comment 'create time',
    `update_time` datetime    default current_timestamp comment 'update time',
    `create_by`   varchar(20)   not null comment 'create user id',
    `update_by`   varchar(20)   not null comment 'update user id',
    `username`    varchar(50)  not null comment 'username for login',
    `password`    varchar(100) not null comment 'password for login',
    `name`        varchar(20)  not null comment 'name',
    `phone`       varchar(20) default null comment 'phone',
    `email`       varchar(50) default null comment 'email',
    primary key (`id`)
)