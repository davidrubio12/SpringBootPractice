create or replace table category
(
    id          bigint auto_increment
        primary key,
    name        varchar(100)  not null,
    description varchar(200)  not null,
    image_url   varchar(1000) null
);

create or replace table product
(
    price          double        not null,
    stock_quantity int           not null,
    category_id    bigint        not null,
    id             bigint auto_increment
        primary key,
    name           varchar(100)  not null,
    description    varchar(200)  not null,
    image_url      varchar(1000) null,
    constraint FK1mtsbur82frn64de7balymq9s
        foreign key (category_id) references category (id)
);

create or replace table cart
(
    price        double        not null,
    quantity     int           not null,
    date         datetime(6)   not null,
    id           bigint auto_increment
        primary key,
    modified     datetime(6)   not null,
    product_id   bigint        not null,
    product_name varchar(200)  not null,
    image_url    varchar(1000) not null,
    constraint UKey2t23ju6wbpsypqcd6rnm0go
        unique (product_id),
    constraint FK3d704slv66tw6x5hmbm6p2x3u
        foreign key (product_id) references product (id)
);

create or replace table rating
(
    rating_number double        null,
    date          datetime(6)   not null,
    id            bigint auto_increment
        primary key,
    product_id    bigint        not null,
    name          varchar(100)  not null,
    comentary     varchar(1000) not null,
    image_url     varchar(1000) not null,
    constraint UKhcohrg884dvalsqiih3cn08ad
        unique (name, product_id),
    constraint FKlkuwie0au2dru36asng9nywmh
        foreign key (product_id) references product (id)
);


create table app_users
(

    user_id    bigint primary key auto_increment,
    first_name varchar(200) not null,
    last_name  varchar(200) not null,
    email      varchar(200) not null unique,
    password   varchar(200) not null

);
