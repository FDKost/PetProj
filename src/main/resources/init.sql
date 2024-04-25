/*CREATE TABLE "client"
(
    id          BIGSERIAL           PRIMARY KEY ,
    login       TEXT                NOT NULL ,
    password    TEXT                NOT NULL
);*/
CREATE TABLE "product"
(
    product_id  BIGSERIAL PRIMARY KEY ,
    name        TEXT        NOT NULL ,
    price       INT         NOT NULL ,
    details     TEXT        NOT NULL ,
    image_URL   TEXT        NOT NULL
)
