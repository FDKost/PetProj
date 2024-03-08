CREATE TABLE "user"
(
    id          BIGSERIAL           PRIMARY KEY ,
    login       TEXT                NOT NULL ,
    password    TEXT                NOT NULL
);
CREATE TABLE "cart"
(
    id_cart     BIGSERIAL           PRIMARY KEY,
    id_user     INTEGER             NOT NULL,
    id_user     DATE                NOT NULL
);
CREATE TABLE "product_cart"
(
    cart_item_id    BIGSERIAL       PRIMARY KEY,
    product_id      INTEGER         NOT NULL,
    id_cart         INTEGER         NOT NULL,
    quantity        INTEGER         NOT NULL
);
CREATE TABLE "address"
(
    id_address      BIGSERIAL       PRIMARY KEY,
    id_user         INTEGER         NOT NULL ,
    street          TEXT            NOT NULL ,
    house           TEXT            NOT NULL ,
    apartment       TEXT            NOT NULL
);
CREATE TABLE "payment"
(
    id_payment      BIGSERIAL       PRIMARY KEY ,
    total           INTEGER         NOT NULL ,
    check_URL       TEXT            NOT NULL
);
CREATE TABLE "product"
(
    product_id      BIGSERIAL       PRIMARY KEY ,
    name            TEXT            NOT NULL ,
    price           INTEGER         NOT NULL ,
    details         TEXT            NOT NULL ,
    image_URL       TEXT            NOT NULL
)