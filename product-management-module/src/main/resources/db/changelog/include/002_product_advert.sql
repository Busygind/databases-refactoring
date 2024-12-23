--liquibase formatted sql

--changeset rsushe:products_create_table:1
CREATE TABLE IF NOT EXISTS products (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    brand VARCHAR NOT NULL,
    size DOUBLE PRECISION NOT NULL,
    color VARCHAR
);


--changeset rsushe:advert_create_table:2
CREATE TABLE IF NOT EXISTS advert (
    id VARCHAR PRIMARY KEY,
    product_id VARCHAR NOT NULL,
    seller_id VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    price DOUBLE PRECISION NOT NULL CHECK (price >= 1),
    active BOOLEAN NOT NULL
);

ALTER TABLE advert ADD CONSTRAINT products_fk FOREIGN KEY (product_id) REFERENCES products(id);
CREATE UNIQUE INDEX IF NOT EXISTS products_seller_unq ON advert(product_id, seller_id);

--changeset rsushe:advert_count_field:3
ALTER TABLE advert ADD COLUMN product_count INTEGER NOT NULL DEFAULT 1;

--changeset rsushe:advert_description_field:3
ALTER TABLE advert ADD COLUMN description TEXT;

--changeset rsushe:advert_seller_address_field:4
ALTER TABLE advert ADD COLUMN seller_address_id VARCHAR NOT NULL;
