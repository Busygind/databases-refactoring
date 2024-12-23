--liquibase formatted sql

--changeset rsushe:user_wishlist_create_table:1
CREATE TABLE IF NOT EXISTS user_wishlist_item (
    id VARCHAR PRIMARY KEY,
    user_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL
);

ALTER TABLE user_wishlist_item ADD CONSTRAINT products_fk FOREIGN KEY (product_id) REFERENCES products(id);

CREATE UNIQUE INDEX IF NOT EXISTS user_wishlist_item_user_idx ON user_wishlist_item(user_id);
