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
ALTER TABLE "address"
    ADD CONSTRAINT fk_client_id
        FOREIGN KEY (id_user)
            REFERENCES "client" (id);