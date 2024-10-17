--liquibase formatted sql

--changeset FDKost:1
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
    /*FOREIGN KEY (userId) REFERENCES "client" (id)*/
);
CREATE TABLE IF NOT EXISTS "product_cart"
(
    id uuid PRIMARY KEY ,
    productId uuid NOT NULL ,
    cartId uuid NOT NULL ,
    quantity INT NOT NULL
    /*FOREIGN KEY (productId) REFERENCES product (productId) ,
    FOREIGN KEY (cartId) REFERENCES cart (cartId)*/
);
CREATE TABLE IF NOT EXISTS "cart"
(
    id uuid PRIMARY KEY ,
    userId uuid NOT NULL ,
    createdIn DATE NOT NULL
    /*FOREIGN KEY (userId) REFERENCES client (id)*/
);
CREATE TABLE IF NOT EXISTS "orders"
(
    id    uuid PRIMARY KEY,
    userId     uuid       NOT NULL ,
    paymentId  uuid       NOT NULL ,
    addressId  uuid       NOT NULL ,
    date        DATE      NOT NULL ,
    status      INT       DEFAULT 1
    /*FOREIGN KEY (userId) REFERENCES client (id) ,
    FOREIGN KEY (paymentId) REFERENCES payment (paymentId) ,
    FOREIGN KEY (addressId) REFERENCES address (addressId) ,
    FOREIGN KEY (status) REFERENCES status (statusId)*/
);
CREATE TABLE IF NOT EXISTS "products_in_order"
(
    id   uuid PRIMARY KEY NOT NULL ,
    orderId    uuid     NOT NULL ,
    productId  uuid     NOT NULL ,
    quantity    INT     NOT NULL
    /*FOREIGN KEY (orderId) REFERENCES orders (orderId) ,
    FOREIGN KEY (productId) REFERENCES product (productId)*/
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

