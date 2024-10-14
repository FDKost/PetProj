--liquibase formatted sql

--changeset FDKost:1
ALTER TABLE "address" ADD
    FOREIGN KEY (userId) REFERENCES "client" (id);

ALTER TABLE  "product_cart" ADD
    FOREIGN KEY (productId) REFERENCES product (productId);

ALTER TABLE "product_cart" ADD
    FOREIGN KEY (cartId) REFERENCES cart (cartId);

ALTER TABLE "cart" ADD
    FOREIGN KEY (userId) REFERENCES client (id);

ALTER TABLE "orders" ADD
    FOREIGN KEY (userId) REFERENCES client (id);

ALTER TABLE "orders" ADD
    FOREIGN KEY (paymentId) REFERENCES payment (paymentId);

ALTER TABLE "orders" ADD
    FOREIGN KEY (addressId) REFERENCES address (addressId);

ALTER TABLE "orders" ADD
    FOREIGN KEY (status) REFERENCES status (statusId);

ALTER TABLE "products_in_order" ADD
    FOREIGN KEY (orderId) REFERENCES orders (orderId);

ALTER TABLE "products_in_order" ADD
    FOREIGN KEY (productId) REFERENCES product (productId);