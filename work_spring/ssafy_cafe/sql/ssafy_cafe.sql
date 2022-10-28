drop database if exists ssafy_cafe;

create database ssafy_cafe;
use ssafy_cafe;

create table t_user(
	id varchar(100) primary key,
    name varchar(100) not null,
    pass varchar(100) not null,
    stamps integer default 0
);

create table t_product(
	id integer auto_increment primary key,
    name varchar(100) not null,
    type varchar(20) not null,
    price integer not null,
    img varchar(100) not null,
    org_img varchar(100) not null
);

create table t_order(
	id integer auto_increment primary key,
    user_id varchar(100) not null,
    order_time timestamp default CURRENT_TIMESTAMP,    
    completed char(1) default 'N',
    constraint fk_order_user foreign key (user_id) references t_user(id) on delete cascade
);

create table t_order_detail(
	id integer auto_increment primary key,
    order_id integer not null,
    product_id integer not null,
    quantity integer not null default 1,
    constraint fk_order_detail_product foreign key (product_id) references t_product(id) on delete cascade,
    constraint fk_order_detail_order foreign key (order_id) references t_order(id) on delete cascade
);

INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy01', '김싸피', 'pass01', 5);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy02', '이싸피', 'pass02', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy03', '박싸피', 'pass03', 3);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy04', '최싸피', 'pass04', 4);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy05', '정싸피', 'pass05', 5);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy06', '강싸피', 'pass06', 6);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy07', '조싸피', 'pass07', 7);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy08', '윤싸피', 'pass08', 8);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy09', '장싸피', 'pass09', 9);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('ssafy10', '임싸피', 'pass10', 20);

INSERT INTO t_product (name, type, price, img, org_img) VALUES ('아메리카노', 'coffee', 4100, 'coffee1.png', 'coffee1.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('카페라떼', 'coffee', 4500, 'coffee2.png', 'coffee2.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('카라멜 마끼아또', 'coffee', 4800, 'coffee3.png', 'coffee3.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('카푸치노', 'coffee', 4800, 'coffee4.png', 'coffee4.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('모카라떼', 'coffee', 4800, 'coffee5.png', 'coffee5.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('민트라떼', 'coffee', 4300, 'coffee6.png', 'coffee6.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('화이트 모카라떼', 'coffee', 4800, 'coffee7.png', 'coffee7.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('자몽에이드', 'coffee', 5100, 'coffee8.png', 'coffee8.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('레몬에이드', 'coffee', 5100, 'coffee9.png', 'coffee9.png');
INSERT INTO t_product (name, type, price, img, org_img) VALUES ('초코칩 쿠키', 'cookie', 1500, 'cookie.png', 'cookie.png');
commit;

INSERT INTO t_order (user_id) VALUES ('ssafy01');
INSERT INTO t_order (user_id) VALUES ('ssafy01');
INSERT INTO t_order (user_id) VALUES ('ssafy03');
INSERT INTO t_order (user_id) VALUES ('ssafy04');
INSERT INTO t_order (user_id) VALUES ('ssafy05');
INSERT INTO t_order (user_id) VALUES ('ssafy06');
INSERT INTO t_order (user_id) VALUES ('ssafy07');
INSERT INTO t_order (user_id) VALUES ('ssafy08');
INSERT INTO t_order (user_id) VALUES ('ssafy09');
INSERT INTO t_order (user_id) VALUES ('ssafy10');

INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (1, 1, 1);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (1, 2, 3);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (2, 8, 1);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (3, 3, 3);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (4, 4, 4);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (5, 5, 5);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (6, 6, 6);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (7, 7, 7);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (8, 8, 8);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (9, 9, 9);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (10, 8, 10);
INSERT INTO t_order_detail (order_id, product_id, quantity) VALUES (10, 10, 10);

commit;

select *
from t_user;

select *
from t_product;

select *
from t_order;

select *
from t_order_detail;

select o.user_id "사용자ID", u.name "이름", p.name "제품명", p.price "가격", od.quantity "수량", o.order_time "주문시간", o.completed "완료여부"
from t_order o
join t_user u on o.user_id = u.id
join t_order_detail od on o.id = od.order_id
join t_product p on od.product_id = p.id;