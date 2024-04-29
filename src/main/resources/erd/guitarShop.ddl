DROP TABLE member;
DROP TABLE item;
DROP TABLE orders;
DROP TABLE order_item;
DROP TABLE cart;

CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    confirm_password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    rrn VARCHAR(255) NOT NULL,
    gender VARCHAR(100) NOT NULL,
    role VARCHAR(100) DEFAULT 'USER' NOT NULL
);

ALTER TABLE member ALTER role SET DEFAULT 'USER';


CREATE TABLE item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    price INT NOT NULL,
    image VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id NUMBER PRIMARY KEY,
    member_id NUMBER NOT NULL,
    price NUMBER NOT NULL,
    name VARCHAR2(100) NOT NULL,
    phone VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    image VARCHAR2(2000),
    address VARCHAR2(100) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE order_items (
    id NUMBER PRIMARY KEY,
    order_id NUMBER NOT NULL,
    product_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL CHECK (quantity >= 1)
);

CREATE TABLE cart (
    id NUMBER PRIMARY KEY,
    member_id NUMBER NOT NULL,
    product_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL CHECK (quantity >= 1)
);