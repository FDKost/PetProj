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
)