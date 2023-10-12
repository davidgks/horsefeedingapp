use horsefeedingapp;

drop table if exists stables, owners, feeding_events, feeding_schedules, food_types, horses;


create table stables (
	idstable bigint not null auto_increment,
    stable_name VARCHAR(100),
    location VARCHAR(100),
    primary key (idstable));

create table owners (
	idowner bigint not null auto_increment,
    owner_name VARCHAR(100) not null,
    email VARCHAR(100),
    primary key (idowner));

create table horses (
	idhorse bigint not null auto_increment,
    guid VARCHAR(100),
	official_name VARCHAR(45) not null,
	nickname VARCHAR(45),
	breed VARCHAR(100),
	owner_id bigint,
	stable_id bigint,
	primary key (idhorse),
    foreign key (owner_id) references owners(idowner),
    foreign key (stable_id) references stables(idstable));

CREATE TABLE food_types (
    idfood_type BIGINT NOT NULL AUTO_INCREMENT,
    food_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (idfood_type)
);

 create table feeding_schedules (
 idfeeding_schedule bigint not null auto_increment,
 horse_id bigint,
 foodtype_id bigint,
 feeding_start_time time,
 feeding_end_time time,
 food_quantity_in_kg bigint,
 primary key (idfeeding_schedule),
 foreign key (horse_id) references horses(idhorse),
 foreign key (foodtype_id) references food_types(idfood_type));


create table feeding_events (
 idfeeding_event bigint not null auto_increment,
 horse_id bigint,
 feeding_schedule_id bigint,
 feeding_time datetime null,
 completed boolean,
 primary key (idfeeding_event),
 foreign key (horse_id) references horses(idhorse),
 foreign key (feeding_schedule_id) references feeding_schedules(idfeeding_schedule));

insert into stables (stable_name, location)
	values
	("Stall 1", "Friedrichshain"),
    ("Stall 2", "Kreuzberg"),
    ("Stall 3", "Schöneberg");

insert into owners (owner_name, email)
	values
    ("tyson fury", "adfladjl"),
    ("max müller", "afsjdkadsjfadslk");

insert into horses (guid, official_name, nickname, breed, owner_id, stable_id)
	values
    ("asf134234", "hans peter", "hansi", "schimmel", 1, 1),
	("xyz987", "gabriele", "gabi", "pony", 2, 1);

insert into food_types (food_name)
	values
    ("heu"),
    ("hafer"),
    ("obst");

insert into feeding_schedules (horse_id, foodtype_id, feeding_start_time, feeding_end_time, food_quantity_in_kg)
	values
    (1, 2, '08:00:00', '09:00:00', 5),
    (1, 1, '12:00:00', '13:00:00', 4),
    (1, 1, '18:00:00', '19:00:00', 4),
    (2, 2, '07:00:00', '08:00:00', 2),
    (2, 1, '11:00:00', '12:00:00', 1),
    (2, 3, '17:00:00', '19:00:00', 1);


select * from stables;
select * from horses;
select * from feeding_schedules;