insert into roles (name)
values ('CUSTOMER');
insert into roles (name)
values ('ADMIN');

insert into user (id, username, email, first_name, last_name, password, roles_name)
values (1, 'TEST_ADMIN', 'TEST_ADMIN@mail.com', 'TEST', 'ADMIN',
        '$2a$10$KLmoKpcd/aeHqk/yaR9Vl.K1a6LjMlP/N9yJhVomU75MgwUZb3TJi', 'ADMIN');
insert into user (id, username, email, first_name, last_name, password, roles_name)
values (2, 'TEST_CUSTOMER', 'TEST_CUSTOMER@mail.com', 'TEST', 'CUSTOMER',
        '$2a$10$KLmoKpcd/aeHqk/yaR9Vl.K1a6LjMlP/N9yJhVomU75MgwUZb3TJi', 'CUSTOMER');