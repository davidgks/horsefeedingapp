use horsefeedingapp;

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
	 feeding_time time null,
	 completed boolean,
	 primary key (idfeeding_event),
	 foreign key (horse_id) references horses(idhorse),
	 foreign key (feeding_schedule_id) references feeding_schedules(idfeeding_schedule));