insert into roles (name) values ('CUSTOMER');
insert into roles (name) values ('ADMIN');

insert into user (id, username, email, first_name, last_name, password, roles_name) values (1, 'admin', 'admin@mail.com', 'Admin', 'Adminescu', '1','ADMIN');
insert into user (id, username, email, first_name, last_name, password, roles_name) values (2, 'doej', 'doej@mail.com', 'John', 'Doe', 'password','CUSTOMER');