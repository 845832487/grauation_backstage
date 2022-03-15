create table application_announce
(
    application_id varchar(255)                         not null
        primary key,
    applicant_id   varchar(255)                         not null,
    announce_id    varchar(255)                         null,
    is_finish      tinyint(1) default 0                 not null,
    create_date    timestamp  default CURRENT_TIMESTAMP not null,
    finish_time    timestamp                            null
);

create table application_checkout
(
    application_id varchar(255)                        not null
        primary key,
    applicant_id   varchar(255)                        not null,
    dormkeeper_id  varchar(255)                        null,
    counselor_id   varchar(255)                        null,
    dormmanager_id varchar(255)                        null,
    message        varchar(255)                        not null,
    file_addr      varchar(255)                        null,
    create_time    timestamp default CURRENT_TIMESTAMP not null,
    finish_time    timestamp                           null
);

create table application_late_return
(
    application_id varchar(255)                        not null
        primary key,
    applicant_id   varchar(255)                        not null,
    approval_id    varchar(255)                        not null,
    message        varchar(255)                        null,
    file_addr      varchar(255)                        null,
    late_date      date                                not null,
    create_time    timestamp default CURRENT_TIMESTAMP not null,
    finish_time    timestamp                           null
);

create table application_repair
(
    application_id varchar(255)                        not null
        primary key,
    applicant_id   varchar(255)                        not null,
    approval_id    varchar(255)                        not null,
    message        varchar(255)                        null,
    file_addr      varchar(255)                        null,
    create_time    timestamp default CURRENT_TIMESTAMP not null,
    finish_time    timestamp                           null
);

create table application_term_finish_checkout
(
    application_id varchar(255)                        not null
        primary key,
    applicant_id   varchar(255)                        not null,
    approval_id    varchar(255)                        null,
    file_addr      varchar(255)                        null,
    create_time    timestamp default CURRENT_TIMESTAMP not null,
    finish_time    timestamp                           null
);

create table application_term_start_checkin
(
    application_id varchar(255)                        not null
        primary key,
    applicant_id   varchar(255)                        not null,
    approval_id    varchar(255)                        null,
    file_addr      varchar(255)                        null,
    create_time    timestamp default CURRENT_TIMESTAMP not null,
    finish_time    timestamp                           null
);

create table dorm_detail
(
    building_id varchar(255)  not null,
    dorm_id     varchar(255)  not null,
    rooms_left  int           null,
    capacity    int default 6 not null,
    primary key (building_id, dorm_id)
);

create table student_detail
(
    id             varchar(255)                                                                               not null,
    name           varchar(60)                                                                                not null,
    telephone      varchar(50)                                                                                null,
    email          varchar(255)                                                                               null,
    sex            tinyint(1)                                                                                 not null,
    icon           varchar(255) default 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' null,
    is_dorm_leader tinyint(1)   default 0                                                                     null,
    building_num   varchar(50)                                                                                null,
    dorm_num       varchar(50)                                                                                null,
    in_out         tinyint(1)   default 0                                                                     not null,
    constraint user_detail_user_id_uindex
        unique (id)
);

alter table student_detail
    add primary key (id);

create table user_login_test
(
    id       varchar(255)                   not null,
    password varchar(255) default '123456'  not null,
    role     varchar(50)  default 'student' not null,
    constraint user_login_user_id_uindex
        unique (id)
);

alter table user_login
    add primary key (id);

create table worker_detail
(
    id                 varchar(255)                                                                               not null,
    name               varchar(50)                                                                                not null,
    telephone          varchar(40)                                                                                null,
    email              varchar(255)                                                                               null,
    sex                tinyint(1)                                                                                 not null,
    icon               varchar(255) default 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' not null,
    job                varchar(255) default '0'                                                                   not null,
    building_in_charge varchar(255)                                                                               null,
    constraint worker_detail_id_uindex
        unique (id)
);

