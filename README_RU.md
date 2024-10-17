# ***CYBERPIZZA*** - веб приложение для моего обучения

---
<div style="text-align: center;"><img src="src/main/resources/static/images/Logo.png" width="100%"></div>

[EN](README.md)|[RU](README_RU.md)

Этот проект существует для моего развития в фреймворке Spring и языка Java. На данный момент в проекте представлены следующие фреймворки:
+ ✅Spring
    + JDBC
    + ❌JPA
    + Security
    + Web
    + thymeleaf
+ ✅Lombok
+ ✅Liquibase

## Как посмотреть проект? 👀

---

1. Скопируйте репозиторий в любую папку;

```
$git clone https://github.com/FDKost/PetProj 
```
2. Создайте **postgresql** базу данных с именем **cyberpizzaTest**;

```postgresql
CREATE DATABASE cyberpizzaTest;
```
3. Запустите приложение:
    ```
    C:\Users\yfili\Desktop\IdeaProjects\Education>mvn
    C:\Users\yfili\Desktop\IdeaProjects\Education>mvn spring-boot:run
   ```

4. Зайдите на локальный сервер (localhost:8080/ по стандарту);

5. Залогиньтесь в приложение следующими способами:
   1. логин **ttt** пароль **ttt**;
   2. зарегистрируйте нового пользователя.

6. Чтобы посмотреть панель администратора, войдите с правами админа (логин ttt пароль ttt) и перейдите по url localhost:8080/**admin**

## Решение проблем и что в будущем исправлю/добавлю? 🩹

---

### ❌Я создаю нового пользователя и не могу оформить заказ:

1. перейдите в url localhost:8080/**profile** и введите адрес;
2. перейдите в url localhost:8080/**order** и добавьте что-нибудь в корзину.

Ошибка происходит когда мы добавляем **нового пользователя**, поскольку у пользователя нет адреса, приложение не может создать корзину.
Пофикшу в будущем.

### ✅ Не могу обновить адрес:

    SOLVED

### ❌Как я могу посмотреть заказы?

Сейчас только пользователи с ролью ADMIN могут смотреть заказы. В будущем добавлю эту функцию для авторизованных пользователей. 

## Соц. сети автора 🌍

+ Tg : https://t.me/kubirito
+ VK : https://vk.com/nmmfs
+ Mail : yfilipp123@mail.ru 