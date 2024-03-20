DROP TABLE IF EXISTS furniture_to_apartment_info;
DROP TABLE IF EXISTS furniture;
DROP TABLE IF EXISTS kitchen_to_apartment_info;
DROP TABLE IF EXISTS kitchen;
DROP TABLE IF EXISTS appliance_to_apartment_info;
DROP TABLE IF EXISTS appliance;
DROP TABLE IF EXISTS house_info;
DROP TABLE IF EXISTS house_type;
DROP TABLE IF EXISTS apartment_info;
DROP TABLE IF EXISTS rooms_type;
DROP TABLE IF EXISTS balcony_type;
DROP TABLE IF EXISTS repair_type;
DROP TABLE IF EXISTS bathroom_type;
DROP TABLE IF EXISTS type_of_payment_to_rent_condition;
DROP TABLE IF EXISTS type_of_payment;
DROP TABLE IF EXISTS rent_condition_info;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS rent_type;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    second_name VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
    login       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

CREATE TABLE rent_type
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL
);

CREATE TABLE post
(
    id                     BIGSERIAL PRIMARY KEY,
    users_id               BIGINT REFERENCES users(id),
    rent_type_id           BIGINT REFERENCES rent_type(id),
    name                   VARCHAR NOT NULL,
    title                  VARCHAR NOT NULL,
    date                   DATE
);

CREATE TABLE rent_condition_info
(
    id                 BIGSERIAL PRIMARY KEY,
    post_id            BIGINT UNIQUE REFERENCES post(id),
    price              DOUBLE PRECISION,
    deposit            DOUBLE PRECISION,
    commission_percent INTEGER,
    currency           VARCHAR
);

CREATE TABLE type_of_payment
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE type_of_payment_to_rent_condition
(
    type_of_payment_id        BIGINT REFERENCES type_of_payment(id),
    rent_condition_info_id    BIGINT REFERENCES rent_condition_info(id)
);

CREATE TABLE bathroom_type
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE repair_type
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE balcony_type
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE rooms_type
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE apartment_info
(
    id               BIGSERIAL PRIMARY KEY,
    post_id          BIGINT UNIQUE REFERENCES post(id),
    bathroom_type_id BIGINT REFERENCES bathroom_type(id),
    repair_type_id   BIGINT REFERENCES repair_type(id),
    balcony_type_id  BIGINT REFERENCES balcony_type(id),
    rooms_type_id    BIGINT REFERENCES rooms_type(id),
    rooms_count      INTEGER NOT NULL,
    total_area       DOUBLE PRECISION NOT NULL,
    kitchen_area     DOUBLE PRECISION,
    flour            INTEGER,
    additionally     VARCHAR,
    living_space     DOUBLE PRECISION
);

CREATE TABLE furniture
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE furniture_to_apartment_info
(
    apartment_info_id BIGINT REFERENCES apartment_info(id),
    furniture_id      BIGINT REFERENCES furniture(id)
);

CREATE TABLE appliance
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE appliance_to_apartment_info
(
    appliance_id      BIGINT REFERENCES appliance(id),
    apartment_info_id BIGINT REFERENCES apartment_info(id)
);

CREATE TABLE house_type
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE house_info
(
    id            BIGSERIAL PRIMARY KEY,
    post_id       BIGINT UNIQUE REFERENCES post(id),
    house_type_id BIGINT REFERENCES house_type(id),
    address       VARCHAR NOT NULL,
    flours_count  INTEGER NOT NULL
);

INSERT INTO type_of_payment(name)
VALUES ('Включены в платёж'),
       ('Оплачиваются отдельно');

INSERT INTO balcony_type(name)
VALUES ('Балкон'),
       ('Лоджия');

INSERT INTO bathroom_type(name)
VALUES ('Совмещенный'),
       ('Раздельный');

INSERT INTO repair_type(name)
VALUES ('Требуется'),
       ('Косметический'),
       ('Евро'),
       ('Дизайнерский');

INSERT INTO rooms_type(name)
VALUES ('Смежные'),
       ('Изолированные');

INSERT INTO furniture(name)
VALUES ('Кухня'),
       ('Хранение одежды'),
       ('Спальные места');

INSERT INTO appliance(name)
VALUES ('Холодильник'),
       ('Плита'),
       ('Микроволновка'),
       ('Стиральная машина'),
       ('Посудомойка');

INSERT INTO house_type(name)
VALUES ('Панельный'),
       ('Блочный'),
       ('Кирпичный'),
       ('Монолитно-кирпичный'),
       ('Деревянный');

INSERT INTO rent_type(name)
VALUES ('Долгосрочная'),
       ('Посуточная');