alter table if exists coffee_machines add column state varchar(255);
alter table if exists coffee_machines drop column available;
create table refills (id int4 not null, coffee int4, cups int4, milk int4, status varchar(255), status_updated timestamp, water int4, coffee_machine_id int4, primary key (id));
create sequence maintenance_id_seq start 1 increment 1;
alter table if exists refills add constraint refills_coffee_machine_fk foreign key (coffee_machine_id) references coffee_machines;
