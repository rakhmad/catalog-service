CREATE DATABASE catalog;
CREATE USER catalogsvc WITH ENCRYPTED PASSWORD 'r3dh4t1!';
GRANT ALL PRIVILEGES ON DATABASE catalog TO catalogsvc;

-- auto-generated definition
create table catalog_item
(
    id                bigint       not null
        constraint catalog_item_pkey
            primary key,
    image_uri         varchar(255),
    item_category     integer      not null,
    item_enabled      boolean      not null,
    item_name         varchar(255) not null,
    item_price        double precision,
    long_description  text         not null,
    short_description varchar(255) not null
);

alter table catalog_item
    owner to catalogsvc;

