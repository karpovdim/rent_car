CREATE DATABASE IF NOT EXISTS rent_car;
CREATE TABLE IF NOT EXISTS rent_car.role_users
(
    role VARCHAR(25) PRIMARY KEY NOT NULL
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS rent_car.users
(
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(30)  NOT NULL,
    last_name   VARCHAR(30)  NOT NULL,
    email_login VARCHAR(30)  NOT NULL UNIQUE,
    password    VARCHAR(50)  NOT NULL UNIQUE,
    role_user   VARCHAR(25)  NOT NULL,
    phone_user  INT(12),
    foreign key (role_user) REFERENCES role_users (role)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS rent_car.orders
(
    id           INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    start_time   DATETIME,
    end_time     DATETIME,
    order_price  DECIMAL,
    car_id       INT UNSIGNED NOT NULL,
    order_status BOOLEAN DEFAULT FALSE,
    foreign key (car_id) references cars (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS rent_car.cars
(
    id               INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    license_plate    VARCHAR(10)  NOT NULL UNIQUE,
    price_by_day     DECIMAL      NOT NULL,
    insurance_number INT(10) UNIQUE,
    insurance_status BOOLEAN,
    car_description  VARCHAR(250)


) engine = InnoDB;

CREATE TABLE IF NOT EXISTS rent_car.users_orders
(
    users_id  INT UNSIGNED NOT NULL,
    orders_id INT UNSIGNED NOT NULL UNIQUE,
    FOREIGN KEY (users_id) REFERENCES users (id) on delete cascade,
    FOREIGN KEY (orders_id) REFERENCES orders (id) on delete cascade
) engine = InnoDB;






