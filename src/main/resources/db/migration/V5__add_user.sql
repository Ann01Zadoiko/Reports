create table users(
    id bigint auto_increment primary key,
    first_name varchar(20),
    last_name varchar(50),
    role varchar(10),
    login varchar(20),
    password varchar(20),
    token varchar(255)
);