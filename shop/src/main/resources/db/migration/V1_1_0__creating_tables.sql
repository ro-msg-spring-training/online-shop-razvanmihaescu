create table address
(
    id      integer not null auto_increment,
    city    varchar(255),
    country varchar(255),
    county  varchar(255),
    street  varchar(255),
    primary key (id)
) engine = InnoDB;
create table cart
(
    id         integer not null auto_increment,
    product_id integer,
    quantity   integer,
    user_id    integer,
    primary key (id)
) engine = InnoDB;
create table location
(
    id         integer not null auto_increment,
    name       varchar(255),
    address_id integer,
    order_id   integer,
    primary key (id)
) engine = InnoDB;
create table order_detail
(
    id         integer not null auto_increment,
    product_id integer,
    quantity   integer,
    order_id   integer,
    primary key (id)
) engine = InnoDB;
create table orders
(
    id                  integer not null auto_increment,
    created_at          datetime,
    customer_id         integer,
    delivery_address_id integer,
    primary key (id)
) engine = InnoDB;
create table product
(
    id          integer not null auto_increment,
    description varchar(255),
    image_url   varchar(255),
    name        varchar(255),
    price       float,
    weight      double precision,
    category_id integer,
    primary key (id)
) engine = InnoDB;
create table product_category
(
    id          integer not null auto_increment,
    description varchar(255),
    name        varchar(255),
    primary key (id)
) engine = InnoDB;
create table revenue
(
    id          integer not null auto_increment,
    date        date,
    sum         float,
    location_id integer,
    primary key (id)
) engine = InnoDB;
create table roles
(
    name varchar(255) not null,
    primary key (name)
) engine = InnoDB;
create table stock
(
    id          integer not null auto_increment,
    quantity    integer,
    location_id integer,
    product_id  integer,
    primary key (id)
) engine = InnoDB;
create table user
(
    id         integer not null auto_increment,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    username   varchar(255),
    roles_name varchar(255),
    primary key (id)
) engine = InnoDB;
alter table cart
    add constraint FKl70asp4l4w0jmbm1tqyofho4o foreign key (user_id) references user (id);
alter table location
    add constraint FKt8psi9b5mkkfc0r9fgptngwhg foreign key (address_id) references address (id);
alter table location
    add constraint FK42oskjqgbk1mivrmqwa9b7i5f foreign key (order_id) references orders (id);
alter table order_detail
    add constraint FKrws2q0si6oyd6il8gqe2aennc foreign key (order_id) references orders (id);
alter table orders
    add constraint FK14n2jkmoyhpimhracvcdy7sst foreign key (customer_id) references user (id);
alter table orders
    add constraint FKbwhiubtkxf94knbm9oo55wdbm foreign key (delivery_address_id) references address (id);
alter table product
    add constraint FK5cypb0k23bovo3rn1a5jqs6j4 foreign key (category_id) references product_category (id);
alter table revenue
    add constraint FK6xukepd8ssa1ok6iakkhp83p7 foreign key (location_id) references location (id);
alter table stock
    add constraint FK6t3m0kaf6fubuus331gf7xmn8 foreign key (location_id) references location (id);
alter table stock
    add constraint FKjghkvw2snnsr5gpct0of7xfcf foreign key (product_id) references product (id);
alter table user
    add constraint FK5u6mi09c6ogbcnym8crovsvds foreign key (roles_name) references roles (name);