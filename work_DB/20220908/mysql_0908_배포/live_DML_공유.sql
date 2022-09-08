-- create database dbtest
-- default character set utf8mb3 collate utf8mb3_general_ci;

-- create database dbtest
-- default character set utf8mb4 collate utf8mb4_general_ci;

-- alter database dbtest
-- default character set utf8mb4 collate utf8mb4_general_ci;

-- drop database dbtest;

use ssafydb;

-- 회원 정보 table 생성.
-- table name : ssafy_member
-- column
-- idx			int			auto_increments		PK
-- userid		varchar(16)	not null
-- username		varchar(20)
-- userpwd		varchar(16)
-- emailid		varchar(20)
-- emaildomain	varchar(50)
-- joindate		timestamp	default	current_timestamp
create table ssafy_member 
(
    userid varchar(16) not null,
    username varchar(20),
    userpwd varchar(16),
    gender char(1) check (gender in ('m', 'f')), -- 'm', 'f'중 하나를 가진다는 뜻, check: .equals() 
    emailid varchar(20),
    emaildomain varchar(50),
    joindate timestamp default current_timestamp,
    constraint ssafy_member_idx_pk primary key (idx)
)

-- 회원 정보 등록
-- 'kimssafy', '김싸피', '1234', 'kimssafy', 'ssafy.com' 등록시간
insert into ssafy_member (userid, username, userpwd, emailid, emaildomain, joindate)
values ('kimssafy', '김싸피', '1234', 'kimssafy', 'ssafy.com' now());

select *
from ssafy_member;


-- '김싸피', 'kimssafy', '1234'
insert into ssafy_member (userid, username, userpwd)
values ('parkssafy', '박싸피', '1234');
insert into ssafy_member (userid, username, userpwd, gender)
values ('parkssafy2', '박싸피2', '1234', 'f');
insert into ssafy_member (userid, username, userpwd, gender)
values ('parkssafy3', '박싸피3', '1234', 'w');


-- '이싸피', 'leessafy', '1234'
-- '박싸피', 'parkssafy', '9876'
insert into ssafy_member (userid, username, userpwd)
values ('leessafy', '이싸피', '1234'), ('choissafy', '최싸피', '9876');


-- userid가 kimssafy인 회원의 비번을 9876, 이메일 도메인을 ssafy.com으로 변경.
select * 
from ssafy_member
where userid = 'choissafy';

update ssafy_member
set userpwd = 1234, emaildomain = 'ssafy.com'
where userid = 'choissafy';


-- userid가 kimssafy 회원 탈퇴
select * 
from ssafy_member
where userid = 'kimssafy';
delete from ssafy_member
where userid='kimssafy';
