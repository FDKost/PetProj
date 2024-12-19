--liquibase formatted sql

--changeset FDKost:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--changeset FDKost:2
CREATE TABLE IF NOT EXISTS "client"
(
    id          uuid           PRIMARY KEY ,
    login       TEXT                NOT NULL ,
    password    TEXT                NOT NULL ,
    role        TEXT                DEFAULT 'USER'
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
    bank_id uuid ,
    privateKey  TEXT            DEFAULT '-----BEGIN PRIVATE KEY-----
MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCqgZEijmMM2kCy
f+g9v6Pgh6IC1rq/IVE0AUO582LT68ffDaZlsUzOOuAzj1tzesE6CWj/PJEAeJ4g
QuanwK4on2DOGseB/b6gmZ0S3DEQS5Cpsknc9Xq1igEeCuMYAdUI5Sd2NhKiMwg9
MzeC/YVrcBhZpq34FchtLYDHkswr0tFQWqhknIy+kx04vZFZKKb9cEiizJghPLYD
O/XObxAeMeZtSSGDIdCvegnGIt9I1dop1p3acqxEpVQ3BaBqXK2G6y4OSswG4vyG
BZF2btOAefcyOQui7KnK5NcjfuIKwulU92qNpjL//XdcM1mCFy9PSNKoQoyc3M7g
VK/MSHsXAgMBAAECggEAAgJrPBhSwtmmKgNQFyOKwB4rOkLe20m6MtV4UJB52GSq
eIYgjxHJa+gel3E8CpQiNoJSFPYdZ39RNo7awLZd2TalPVIwm2c3l53QYLg5aUUv
g5IR5xzXx3pEluc3nEVjH5WMDb6hMjBeYMn/1chWPRkS/9u/7GRDdkSZppGKXIW0
Mv0P40QX8CAekysMOw3wNpIeqptUMXpPhgyrIciASRthZTalaoyJJzXylpkuhark
MgU2hpNm1h0wHfa0afeMhnrPqx7YP0kN8ZKb1yDkwFi3m2eEhJNAs6au4lxhApko
+Kw0ZzE147YLYBIM8Z0WT4oME8wy+iJu1/Jhkyp9UQKBgQDr2rQh+e3+b4ReagP8
HuLB63Ssb9KyGEqPlrzznXh6YXkjqcxo1s9ApmRM61SWy3oAfHI0ke7FlLYpLfCt
F19JP/aF0rpxdody6E/wAXXRuFEZ0xryMLUShOTCZfzBJ50HEcj/Z7a3mvwCUyKf
DVv/rcVb5YDDjvQyqecbGMzQMQKBgQC5Ee4uXROaLfAY5kAS/VdGAURUJ8v+Ly7I
XF5Efz2O9kAaA5YwXGSakjokZSbDHAH+6s6R8dflsuBGmEssABAFjPe5QjXpfyp2
ZOG+ASGSaa9e+NAwxX905qLupGhibu4IoiNmf3SNv+ktctVIpflm6Gb8qYqhLlIs
QYMpe+C1xwKBgHGRNO2rUAsVHZLsz5BmOuAPhXXGvpidE8ICXmeb6rhbGR6wE0pk
LRW/SUq0gl2+CrgTNFbV8jhXfENl6J8gQvSdhRI5PyeFYIFXFa7GtTnP29VojnAS
Pg51yUVGIKfNcFJIIRXClfvg9/oSl/goUaVSwlaWsyL3QeTnZO3qSizxAoGAGr7D
nAGpdouS+hY0RkpEVYKTXFIQAeDRsvTeTqnQvJtLsja4eq6vf0MIkf/oNSQUCcHn
LmIz4CcUkD8sBeTQa+zuU+CEqATPnyJ/GRVSw7TwOhpdA99Wxw8ZlsE9sJP1ZFwC
H6rlriKA0SeaORB7VWraj0L1HMvz5h9llBYp6DsCgYBOqMYfJRQ1jFABxCvmSYW2
rDySRIvgZaXQ1HfEaGt0CeKi0WzGnIZPd2DyeqEzBjQNXNB4JOpFrITvl0CZRrt4
920U4kICvmUamcZoRnIl59YhuYouEqPIZJTM3Zpy/LHdW+uV+lrZdJWgIdaoWAMs
Lc/mG0CPMaoJI1hNVl+2Xw==
-----END PRIVATE KEY-----'
)

