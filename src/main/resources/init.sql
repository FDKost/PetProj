/*CREATE TABLE "client"
(
    id          BIGSERIAL           PRIMARY KEY ,
    login       TEXT                NOT NULL ,
    password    TEXT                NOT NULL
);
CREATE TABLE "product"
(
    product_id  BIGSERIAL PRIMARY KEY ,
    name        TEXT        NOT NULL ,
    price       INT         NOT NULL ,
    details     TEXT        NOT NULL ,
    image_URL   TEXT        NOT NULL
)*/
/*CREATE TABLE "address"
(
    id_address BIGSERIAL PRIMARY KEY ,
    id_user    INT      NOT NULL ,
    street     TEXT     NOT NULL ,
    house      TEXT     NOT NULL ,
    apartment  TEXT     NOT NULL
);*/
/*CREATE TABLE "product_cart"
(
    cart_item_id BIGSERIAL PRIMARY KEY,
    product_id INT NOT NULL ,
    id_cart INT NOT NULL ,
    quantity INT NOT NULL
)*/
/*CREATE TABLE "cart"
(
    id_cart BIGSERIAL PRIMARY KEY ,
    id_user INT NOT NULL ,
    created_in DATE NOT NULL
)*/
/*ALTER TABLE "address"
    ADD CONSTRAINT fk_client_id
        FOREIGN KEY (id_user)
            REFERENCES "client" (id);*/
/*ALTER TABLE product_cart
    ADD CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES product (product_id);*/
/*CREATE TABLE "order"
(
    id_order    BIGSERIAL PRIMARY KEY,
    id_user     INT       NOT NULL ,
    id_payment  INT       NOT NULL ,
    id_address  INT       NOT NULL ,
    date        DATE      NOT NULL
);*/
/*CREATE TABLE "products_in_order"
(
    order_item_id   BIGSERIAL NOT NULL ,
    id_order    INT     NOT NULL ,
    product_id  INT     NOT NULL ,
    quantity    INT     NOT NULL
)*/
/*CREATE TABLE "payment"
(
    id_payment BIGSERIAL PRIMARY KEY ,
    total       INT      NOT NULL ,
    check_url   TEXT     NOT NULL
)*/
ALTER TABLE "products_in_order"
    ADD CONSTRAINT pk_order_item_id PRIMARY KEY (order_item_id);