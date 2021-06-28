CREATE DATABASE catalog;
CREATE USER catalogsvc WITH ENCRYPTED PASSWORD '<CHANGEME>!';
GRANT ALL PRIVILEGES ON DATABASE catalog TO catalogsvc;

create sequence catalog_item_id_seq;
alter table catalog_item alter column id set default nextval('public.catalog_item_id_seq');
alter sequence catalog_item_id_seq owned by catalog_item.id;