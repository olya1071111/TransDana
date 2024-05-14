create table user
(
    id   int          not null primary key auto_increment,
    name varchar(256) not null,
    job  varchar(256)
);

create table login
(
    id                      int          not null primary key,
    login                   varchar(256),
    password                varchar(256),
    enabled                 bit(1)       not null default true,
    credentials_non_expired bit(1)       not null default true,
    account_non_expired     bit(1)       not null default true,
    account_non_locked      bit(1)       not null default true,
    user_id                 int unique,
    role                    varchar(256) not null,
    CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES user (id)
);

create table car
(
    id      int          not null primary key auto_increment,
    number  varchar(256) not null,
    tonnage varchar(256) not null,
    status  varchar(256) not null
);

create table city
(
    id   int          not null primary key auto_increment,
    name varchar(256) not null
);

create table route
(
    id                int not null primary key auto_increment,
    number            int,
    arrivaldate       TIMESTAMP,
    departuredate     TIMESTAMP,
    arrival_city_id   int,
    arrival_address   varchar(256),
    departure_city_id int,
    departure_address varchar(256),
--     route_time        time,
    car_id            int,
    user_id           int,
    status            varchar(256),
    CONSTRAINT FK_ARRIVAL_CITY FOREIGN KEY (arrival_city_id) REFERENCES city (id),
    CONSTRAINT FK_DEPARTURE_CITY FOREIGN KEY (departure_city_id) REFERENCES city (id),
    CONSTRAINT FK_CAR1 FOREIGN KEY (car_id) REFERENCES car (id),
    CONSTRAINT FK_USER2 FOREIGN KEY (user_id) REFERENCES user (id)
);
