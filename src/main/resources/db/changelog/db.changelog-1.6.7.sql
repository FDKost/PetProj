--liquibase formatted sql

--changeset FDKost:1
CREATE TABLE IF NOT EXISTS "client"
(
    id          BIGSERIAL           PRIMARY KEY ,
    login       TEXT                NOT NULL ,
    password    TEXT                NOT NULL ,
    role        TEXT                DEFAULT 'ROLE_USER'
);
CREATE TABLE IF NOT EXISTS "product"
(
    productId  BIGSERIAL PRIMARY KEY ,
    name        TEXT        NOT NULL ,
    price       INT         NOT NULL ,
    details     TEXT        NOT NULL ,
    imageURL   TEXT        NOT NULL
);
CREATE TABLE IF NOT EXISTS "address"
(
    addressId BIGSERIAL PRIMARY KEY ,
    userId    INT ,
    street     TEXT ,
    house      TEXT ,
    apartment  TEXT
    /*FOREIGN KEY (userId) REFERENCES "client" (id)*/
);
CREATE TABLE IF NOT EXISTS "product_cart"
(
    cartItemId BIGSERIAL PRIMARY KEY ,
    productId INT NOT NULL ,
    cartId INT NOT NULL ,
    quantity INT NOT NULL
    /*FOREIGN KEY (productId) REFERENCES product (productId) ,
    FOREIGN KEY (cartId) REFERENCES cart (cartId)*/
);
CREATE TABLE IF NOT EXISTS "cart"
(
    cartId BIGSERIAL PRIMARY KEY ,
    userId INT NOT NULL ,
    createdIn DATE NOT NULL
    /*FOREIGN KEY (userId) REFERENCES client (id)*/
);
CREATE TABLE IF NOT EXISTS "orders"
(
    orderId    BIGSERIAL PRIMARY KEY,
    userId     INT       NOT NULL ,
    paymentId  INT       NOT NULL ,
    addressId  INT       NOT NULL ,
    date        DATE      NOT NULL ,
    status      INT       DEFAULT 1
    /*FOREIGN KEY (userId) REFERENCES client (id) ,
    FOREIGN KEY (paymentId) REFERENCES payment (paymentId) ,
    FOREIGN KEY (addressId) REFERENCES address (addressId) ,
    FOREIGN KEY (status) REFERENCES status (statusId)*/
);
CREATE TABLE IF NOT EXISTS "products_in_order"
(
    orderItemId   BIGSERIAL PRIMARY KEY NOT NULL ,
    orderId    INT     NOT NULL ,
    productId  INT     NOT NULL ,
    quantity    INT     NOT NULL
    /*FOREIGN KEY (orderId) REFERENCES orders (orderId) ,
    FOREIGN KEY (productId) REFERENCES product (productId)*/
);
CREATE TABLE IF NOT EXISTS "payment"
(
    paymentId BIGSERIAL PRIMARY KEY ,
    total       INT      NOT NULL ,
    checkURL   TEXT     NOT NULL ,
    userId     INT     REFERENCES   client(id)
);
CREATE TABLE IF NOT EXISTS status
(
    statusId BIGSERIAL PRIMARY KEY ,
    description TEXT
);

