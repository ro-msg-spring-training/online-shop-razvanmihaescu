insert into roles (name)
values ('CUSTOMER');
insert into roles (name)
values ('ADMIN');

insert into user (id, username, email, first_name, last_name, password, roles_name)
values (1, 'admin', 'admin@mail.com', 'Admin', 'Adminescu',
        '$2a$10$KLmoKpcd/aeHqk/yaR9Vl.K1a6LjMlP/N9yJhVomU75MgwUZb3TJi', 'ADMIN');
insert into user (id, username, email, first_name, last_name, password, roles_name)
values (2, 'doej', 'doej@mail.com', 'John', 'Doe', '$2a$10$KLmoKpcd/aeHqk/yaR9Vl.K1a6LjMlP/N9yJhVomU75MgwUZb3TJi',
        'CUSTOMER');