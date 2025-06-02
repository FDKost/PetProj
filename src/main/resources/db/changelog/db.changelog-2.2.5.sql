--liquibase formatted sql

--changeset FDKost:1
INSERT INTO status(id,description)
VALUES(uuid_generate_v4(),'Completed'),
      (uuid_generate_v4(),'In process'),
      (uuid_generate_v4(),'Delivering'),
      (uuid_generate_v4(),'Waiting for courier');

INSERT INTO product(id,name, price, details, imageURL)
VALUES (uuid_generate_v4(),'Чиз фо ю',230,'моцарелла, тильзитер, пармезан, дор блю и вкусный соус только для тебя','https://drive.google.com/thumbnail?id=1pYq2b9pZuk4M6J8eK91N4cMAB8cPh7li'),
       (uuid_generate_v4(),'Халапеньо хелл',240,'Почувствуйте дух улицы! Халапеньо, помидоры и сыр с нашим соусом заставят вас сгореть','https://drive.google.com/thumbnail?id=1OiZZ_gBSqMCymhWCXeb1dIgkH4W5zK6b'),
       (uuid_generate_v4(),'Выбор вегана',999, 'Кто вообще будет это есть? ТЫ!','https://drive.google.com/thumbnail?id=1iXVNmLmOFMm68UKDizVM_1bXrj2zgTPI'),
       (uuid_generate_v4(),'Озеро моцареллы',460, 'Моцарелла с некоторыми секретными листьями и никто не обнулит вас','https://drive.google.com/thumbnail?id=1qq4npyaxcXwfjARq2cvl3I9LHz3DfvAO'),
       (uuid_generate_v4(),'Золотой выбор',389,'Золотой выбор для каждого мужчины: пепперони и помидоры никогда вас не подведут.','https://drive.google.com/thumbnail?id=1PabCaOXz2w_8ql3q4LueUdhYJrXS-Hq1'),
       (uuid_generate_v4(),'Креветочный рай',720,'Вы когда-нибудь попробуете это? Креветки, сыр и ароматизаторы, где-то один итальянец плачет, когда слышит это сочетание...','https://drive.google.com/thumbnail?id=10W-T03HcE79M0QfArJrD0V-US-eawI4l'),
       (uuid_generate_v4(),'Самозванец',888,'В нашей пиццерии завелся самозванец...','https://drive.google.com/thumbnail?id=1Tyxs4XfTmFck8-PwmFL1X3ENbqzHomKF'),
       (uuid_generate_v4(),'Хэмслайд',520,'Наш повар выбирает темную сторону','https://drive.google.com/thumbnail?id=1kHX7ozXM3gGTTm6LKboFeUwBcqxpVFDn'),
       (uuid_generate_v4(),'Секрет игрока',21000,'Другие считают это смешным, но мы считаем, что это уже слишком','https://drive.google.com/thumbnail?id=1yFtxPKAqlGkGksT0E8BKBHmy1ITj3aod'),
       (uuid_generate_v4(),'Биологическая угроза',999,'ВНИМАНИЕ! При заказе пиццы мы не предоставляем маски хим защиты. 4 вида лука в одной пицце, следующий уровень пиццы 4 сыра','https://drive.google.com/thumbnail?id=1S0rBxrDklosSaft9mj_25vSMoPGWcWcE'),
       (uuid_generate_v4(),'Сказка рыбака',670, 'Перец в пицце, как вы на это смотрите? Мы думаем, что это нормально :)', 'https://drive.google.com/thumbnail?id=1TOauazNPxT1HK5mPq8m9toz9LKPUUSXg');

INSERT INTO client(id,login,password,role)
VALUES (uuid_generate_v4(),'ttt','$2a$08$IBofPRr1u3qKN9vlQWXhAeJmVslTxTkImF6ZLaKI3Q425MC/IIb.m','ADMIN'),
       (uuid_generate_v4(),'kura','$2a$08$IBofPRr1u3qKN9vlQWXhAeJmVslTxTkImF6ZLaKI3Q425MC/IIb.m','COURIER');

INSERT INTO bank(id,bank_name,bank_code)
VALUES (uuid_generate_v4(),'GMP',032581723),
       (uuid_generate_v4(),'VGU',056231228)

