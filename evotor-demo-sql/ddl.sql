create table users (
    id serial,
    login varchar(32),
    password varchar(32),
    balance decimal,
    primary key (id)
);

create index users_idx1 on users (login);

insert into users (login, password, balance) values ('test', 'qwerty', 123.45);


   
