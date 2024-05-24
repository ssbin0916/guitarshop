-- Drop existing tables if they exist
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS delivery;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;

-- Create tables
CREATE TABLE member (
    id BIGINT AUTO_INCREMENT,
    create_date TIMESTAMP,
    update_date TIMESTAMP,
    address VARCHAR(255),
    address_detail VARCHAR(255),
    login_email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255),
    password VARCHAR(255),
    phone VARCHAR(255),
    request VARCHAR(255),
    role VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE board (
    id BIGINT AUTO_INCREMENT,
    view INT,
    create_date TIMESTAMP,
    update_date TIMESTAMP,
    member_id BIGINT,
    content VARCHAR(255),
    title VARCHAR(255),
    writer VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT,
    member_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE item (
    id BIGINT AUTO_INCREMENT,
    price INT,
    quantity INT,
    brand VARCHAR(255) CHECK (brand IN ('FENDER', 'GIBSON', 'PRS', 'SUHR', 'JAMESTYLER', 'MARTIN', 'TAYLOR')),
    category VARCHAR(255) CHECK (category IN ('ELECTRIC_GUITAR', 'ACOUSTIC_GUITAR')),
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE cart_item (
    id BIGINT AUTO_INCREMENT,
    price INT,
    cart_id BIGINT,
    item_id BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE comment (
    id BIGINT AUTO_INCREMENT,
    board_id BIGINT,
    create_date TIMESTAMP,
    update_date TIMESTAMP,
    member_id BIGINT,
    comment VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (board_id) REFERENCES board(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE delivery (
    id BIGINT AUTO_INCREMENT,
    address VARCHAR(255),
    address_detail VARCHAR(255),
    delivery_status VARCHAR(255) CHECK (delivery_status IN ('READY', 'COMPLETE', 'DELIVERING', 'CANCEL')),
    request VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT,
    delivery_id BIGINT UNIQUE,
    member_id BIGINT,
    order_date TIMESTAMP,
    order_status VARCHAR(255) CHECK (order_status IN ('ORDER', 'CANCEL')),
    PRIMARY KEY (id),
    FOREIGN KEY (delivery_id) REFERENCES delivery(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE order_item (
    id BIGINT AUTO_INCREMENT,
    price INT,
    item_id BIGINT,
    order_id BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES item(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Add foreign key constraints
ALTER TABLE board
    ADD CONSTRAINT FKsds8ox89wwf6aihinar49rmfy
    FOREIGN KEY (member_id) REFERENCES member(id);

ALTER TABLE cart
    ADD CONSTRAINT FKix170nytunweovf2v9137mx2o
    FOREIGN KEY (member_id) REFERENCES member(id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK1uobyhgl1wvgt1jpccia8xxs3
    FOREIGN KEY (cart_id) REFERENCES cart(id);

ALTER TABLE cart_item
    ADD CONSTRAINT FKdljf497fwm1f8eb1h8t6n50u9
    FOREIGN KEY (item_id) REFERENCES item(id);

ALTER TABLE comment
    ADD CONSTRAINT FKlij9oor1nav89jeat35s6kbp1
    FOREIGN KEY (board_id) REFERENCES board(id);

ALTER TABLE comment
    ADD CONSTRAINT FKmrrrpi513ssu63i2783jyiv9m
    FOREIGN KEY (member_id) REFERENCES member(id);

ALTER TABLE order_item
    ADD CONSTRAINT FKija6hjjiit8dprnmvtvgdp6ru
    FOREIGN KEY (item_id) REFERENCES item(id);

ALTER TABLE order_item
    ADD CONSTRAINT FKt4dc2r9nbvbujrljv3e23iibt
    FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE orders
    ADD CONSTRAINT FKtkrur7wg4d8ax0pwgo0vmy20c
    FOREIGN KEY (delivery_id) REFERENCES delivery(id);

ALTER TABLE orders
    ADD CONSTRAINT FKpktxwhj3x9m4gth5ff6bkqgeb
    FOREIGN KEY (member_id) REFERENCES member(id);
