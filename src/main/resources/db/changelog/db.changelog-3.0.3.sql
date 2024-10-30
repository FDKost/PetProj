--liquibase formatted sql

--changeset FDKost:1
ALTER TABLE "address" ADD
    FOREIGN KEY (userId) REFERENCES "client" (id);

ALTER TABLE  "product_cart" ADD
    FOREIGN KEY (productId) REFERENCES product (id);

ALTER TABLE "product_cart" ADD
    FOREIGN KEY (cartId) REFERENCES cart (id);

ALTER TABLE "cart" ADD
    FOREIGN KEY (userId) REFERENCES client (id);

ALTER TABLE "orders" ADD
    FOREIGN KEY (userId) REFERENCES client (id);

ALTER TABLE "orders" ADD
    FOREIGN KEY (paymentId) REFERENCES payment (id);

ALTER TABLE "orders" ADD
    FOREIGN KEY (addressId) REFERENCES address (id);

ALTER TABLE "orders" ADD
    FOREIGN KEY (status) REFERENCES status (id);

ALTER TABLE "products_in_order" ADD
    FOREIGN KEY (orderId) REFERENCES orders (id);

ALTER TABLE "products_in_order" ADD
    FOREIGN KEY (productId) REFERENCES product (id);
ALTER TABLE "user_bank" ADD
    FOREIGN KEY (user_id) REFERENCES client (id);