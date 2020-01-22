drop table if exists address;
drop table if exists hibernate_sequence;
drop table if exists location;
drop table if exists order_detail;
drop table if exists orders;
drop table if exists product;
drop table if exists product_category;
drop table if exists revenue;
drop table if exists roles;
drop table if exists stock;
drop table if exists user;
create table address (id integer not null, city varchar(255), country varchar(255), county varchar(255), street varchar(255), primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table location (id integer not null, name varchar(255), address_id integer, primary key (id)) engine=InnoDB;
create table order_detail (id integer not null, product_id integer, quantity integer, order_id integer, primary key (id)) engine=InnoDB;
create table orders (id integer not null, created_at datetime, customer_user_id integer, delivery_location_id integer, shipped_from_id integer, primary key (id)) engine=InnoDB;
create table product (product_id integer not null, description varchar(255), image_url varchar(255), name varchar(255), price float, weight double precision, category_id integer, primary key (product_id)) engine=InnoDB;
create table product_category (id integer not null, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table revenue (id integer not null, date date, sum float, location_id integer, primary key (id)) engine=InnoDB;
create table roles (name varchar(255) not null, primary key (name)) engine=InnoDB;
create table stock (id integer not null, quantity integer, location_id integer, product_product_id integer, primary key (id)) engine=InnoDB;
create table user (user_id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), roles_name varchar(255), primary key (user_id)) engine=InnoDB;
alter table location add constraint FKt8psi9b5mkkfc0r9fgptngwhg foreign key (address_id) references address (id);
alter table order_detail add constraint FKrws2q0si6oyd6il8gqe2aennc foreign key (order_id) references orders (id);
alter table orders add constraint FK8fvgue6769ma18to0aiv8f54s foreign key (customer_user_id) references user (user_id);
alter table orders add constraint FKiofaquiek92d4rb32e3b1ksso foreign key (delivery_location_id) references location (id);
alter table orders add constraint FKftf58a58sqnkcw7h7b5dbmgst foreign key (shipped_from_id) references location (id);
alter table product add constraint FK5cypb0k23bovo3rn1a5jqs6j4 foreign key (category_id) references product_category (id);
alter table revenue add constraint FK6xukepd8ssa1ok6iakkhp83p7 foreign key (location_id) references location (id);
alter table stock add constraint FK6t3m0kaf6fubuus331gf7xmn8 foreign key (location_id) references location (id);
alter table stock add constraint FKrwdkwjf037066qtbpq0pg0h6n foreign key (product_product_id) references product (product_id);
alter table user add constraint FK5u6mi09c6ogbcnym8crovsvds foreign key (roles_name) references roles (name);