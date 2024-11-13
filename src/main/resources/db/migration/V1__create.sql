create table trams(
	id bigint auto_increment primary key,
    depo varchar(100) not null,
    number_of_tram varchar(10)
);

create table tracks(
	id bigint auto_increment primary key,
    day date,
    first_part varchar(10),
    second_part varchar(10),
    time time,
    id_tram bigint,
    foreign key (id_tram) references reports.trams(id)
);

create table tickets(
	id bigint auto_increment primary key,
    id_tram bigint,
    day date,
    id_track bigint,
    price int,
    time time,
    foreign key (id_tram) references trams(id),
    foreign key (id_track) references tracks(id)
);
