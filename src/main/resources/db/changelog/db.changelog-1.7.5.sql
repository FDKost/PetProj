--liquibase formatted sql

--changeset FDKost:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--changeset FDKost:2
CREATE TABLE IF NOT EXISTS "client"
(
    id          uuid           PRIMARY KEY ,
    login       TEXT                NOT NULL ,
    password    TEXT                NOT NULL ,
    role        TEXT                DEFAULT 'ROLE_USER'
);
CREATE TABLE IF NOT EXISTS "product"
(
    id  uuid PRIMARY KEY ,
    name        TEXT        NOT NULL ,
    price       INT         NOT NULL ,
    details     TEXT        NOT NULL ,
    imageURL   TEXT        NOT NULL
);
CREATE TABLE IF NOT EXISTS "address"
(
    id uuid PRIMARY KEY ,
    userId    uuid ,
    street     TEXT ,
    house      TEXT ,
    apartment  TEXT
);
CREATE TABLE IF NOT EXISTS "product_cart"
(
    id uuid PRIMARY KEY ,
    productId uuid NOT NULL ,
    cartId uuid NOT NULL ,
    quantity INT NOT NULL
);
CREATE TABLE IF NOT EXISTS "cart"
(
    id uuid PRIMARY KEY ,
    userId uuid NOT NULL ,
    createdIn DATE NOT NULL DEFAULT current_date
);
CREATE TABLE IF NOT EXISTS "orders"
(
    id    uuid PRIMARY KEY,
    userId     uuid       NOT NULL ,
    paymentId  uuid       NOT NULL ,
    addressId  uuid       NOT NULL ,
    date        DATE      NOT NULL ,
    status      uuid
);
CREATE TABLE IF NOT EXISTS "products_in_order"
(
    id   uuid PRIMARY KEY NOT NULL ,
    orderId    uuid     NOT NULL ,
    productId  uuid     NOT NULL ,
    quantity    INT     NOT NULL
);
CREATE TABLE IF NOT EXISTS "payment"
(
    id uuid PRIMARY KEY ,
    total       INT      NOT NULL ,
    checkURL   TEXT     NOT NULL ,
    userId     uuid     REFERENCES   client(id)
);
CREATE TABLE IF NOT EXISTS status
(
    id uuid PRIMARY KEY ,
    description TEXT
);
CREATE TABLE IF NOT EXISTS bank
(
  id uuid PRIMARY KEY ,
  bank_name TEXT,
  bank_code INT
);
CREATE TABLE IF NOT EXISTS user_bank
(
    id uuid PRIMARY KEY ,
    user_id uuid,
    bank_id uuid
)

