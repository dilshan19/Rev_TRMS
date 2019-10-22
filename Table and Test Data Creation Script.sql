
create table employeeinfo (
	firstname  varchar,
	lastname varchar,
	email varchar primary key,
	password varchar not null
);

insert into employeeinfo values ('brian', 'money', 'bmoney@gmail.com', 'pass');

create table supervisorinfo (
	firstname  varchar,
	lastname varchar,
	email varchar primary key,
	password varchar not null
);

insert into supervisorinfo values ('test', 'supervisor', 'test@gmail.com', 'pass');

create table departmentheadinfo (
	firstname  varchar,
	lastname varchar,
	email varchar primary key,
	password varchar not null
);

insert into departmentheadinfo values ('test', 'depthead', 'test@gmail.com', 'pass');

create table bencoinfo (
	firstname  varchar,
	lastname varchar,
	email varchar primary key,
	password varchar not null
);

insert into bencoinfo values ('test', 'benco', 'test@gmail.com', 'pass');