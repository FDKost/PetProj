--liquibase formatted sql

--changeset FDKost:1
INSERT INTO status(id, description)
VALUES("d89c8c71-7e3a-4448-9f54-278c76392cef",'Completed'),
      ("436616b2-6a49-42fd-a983-08214d2d287a",'In process');

INSERT INTO product(name, price, details, imageURL)
VALUES ('Cheese 4 u',230,'mozzarella, tilsiter, parmesan, dor blue and tasty sauce only 4 u','https://drive.google.com/thumbnail?id=1pYq2b9pZuk4M6J8eK91N4cMAB8cPh7li'),
       ('Jalapeno hell',240,'Taste the spirit of street! Jalapeno,tomato and cheese with our sauce will make you burn','https://drive.google.com/thumbnail?id=1OiZZ_gBSqMCymhWCXeb1dIgkH4W5zK6b'),
       ('Vegan choice',999, 'Who ever would eat this?YOU!','https://drive.google.com/thumbnail?id=1iXVNmLmOFMm68UKDizVM_1bXrj2zgTPI'),
       ('Mozzarella lake',460, 'Mozzarella with some secret leafs and nobody  reset you to zero','https://drive.google.com/thumbnail?id=1qq4npyaxcXwfjARq2cvl3I9LHz3DfvAO'),
       ('Golden choice',389,'Golden choice for every man, pepperoni and tomato never let you down','https://drive.google.com/thumbnail?id=1PabCaOXz2w_8ql3q4LueUdhYJrXS-Hq1'),
       ('Shrimp paradise',720,'Will you ever taste it?Shrimp, cheese and flavoring, somewhere one italian dies when hear that combo...','https://drive.google.com/thumbnail?id=10W-T03HcE79M0QfArJrD0V-US-eawI4l'),
       ('Imposter',888,'There is imposter among our pizzeria...','https://drive.google.com/thumbnail?id=1Tyxs4XfTmFck8-PwmFL1X3ENbqzHomKF'),
       ('Hamslide',520,'Our cook choose dark side','https://drive.google.com/thumbnail?id=1kHX7ozXM3gGTTm6LKboFeUwBcqxpVFDn'),
       ('Victor''s secret',21000,'V think this is funny, but we think that is too much','https://drive.google.com/thumbnail?id=1yFtxPKAqlGkGksT0E8BKBHmy1ITj3aod'),
       ('Biological hazard',999,'ATTENTION! We will not provide hazard mask when you order one. 4 types of onions in one pizza,next level of 4 cheese pizza','https://drive.google.com/thumbnail?id=1S0rBxrDklosSaft9mj_25vSMoPGWcWcE'),
       ('Fishing tale',670, 'Peppers in pizza,how u look at it?We think it is ok :)', 'https://drive.google.com/thumbnail?id=1TOauazNPxT1HK5mPq8m9toz9LKPUUSXg');

INSERT INTO client(login,password,role)
VALUES ('ttt','$2a$08$IBofPRr1u3qKN9vlQWXhAeJmVslTxTkImF6ZLaKI3Q425MC/IIb.m','ROLE_ADMIN');

INSERT INTO cart(id,createdIn)
VALUES ("60392778-c041-4d5b-9514-aa9f6af85660",'2024-10-14');