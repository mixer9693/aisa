create table coffee_machines (id int4 not null, available boolean not null, coffee_amount int4, cups_amount int4, milk_amount int4, name varchar(255), speed_factor float4, water_amount int4, primary key (id));
create table coffee_types (id int4 not null, coffee_consumption_per100ml int4, milk_consumption_per100ml int4, name varchar(255), water_consumption_per100ml int4, primary key (id));
create table orders (uid varchar(255) not null, comment varchar(255), size varchar(255), status varchar(255), status_updated timestamp, product_id int4, type_id int4, primary key (uid));
create table products (id int4 not null, creation_date_time timestamp, obtained boolean not null, size varchar(255), type varchar(255), primary key (id));
create sequence coffee_machine_id_seq start 1 increment 1;
create sequence coffee_type_id_seq start 1 increment 1;
create sequence product_id_seq start 1 increment 1;
alter table if exists orders add constraint orders_products_fk foreign key (product_id) references products;
alter table if exists orders add constraint orders_type_fk foreign key (type_id) references coffee_types;