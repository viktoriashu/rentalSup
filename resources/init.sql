CREATE
DATABASE rent_sup;

--Таблица роль. role_name может быть либо "client" либо "admin".
CREATE TABLE role
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(128) NOT NULL
);

--Таблица тип пользователя. Где number - номер телефона, id_role - выбор роли пользователя
CREATE TABLE user_type
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(128)             NOT NULL,
    last_name  VARCHAR(128)             NOT NULL,
    login      VARCHAR(128) UNIQUE      NOT NULL,
    password   VARCHAR(128)             NOT NULL,
    number     VARCHAR(128)             NOT NULL,
    id_role    INT REFERENCES role (id) NOT NULL
);


-- Для "sup" поле "status" принимает значения : "free", "busy", "reserve".
CREATE TABLE status_sup
(
    id     SERIAL PRIMARY KEY,
    status VARCHAR(128) NOT NULL
);

CREATE TABLE sup
(
    id            SERIAL PRIMARY KEY,
    model         VARCHAR(128)                   NOT NULL,
    id_status_sup INT REFERENCES status_sup (id) NOT NULL
);

-- Для "claim" поле "status" принимает значения : "open" и "reserve", "paid", "close".
CREATE TABLE status_claim
(
    id     SERIAL PRIMARY KEY,
    status VARCHAR(128) NOT NULL
);

CREATE TABLE claim
(
    id              SERIAL PRIMARY KEY,
    id_client       INT REFERENCES user_type (id),
    id_admin        INT REFERENCES user_type (id),
    id_sup          INT REFERENCES sup (id),
    id_status_claim INT REFERENCES status_claim (id) NOT NULL,
    data_start_rent DATE                             NOT NULL,
    duration_rent   INT                              NOT NULL,
    price           DOUBLE PRECISION                 NOT NULL
);