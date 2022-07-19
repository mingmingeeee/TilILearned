drop table wordcloud;

create table wordcloud
(
	word varchar(100) primary key,
	weight double
);

insert into wordcloud
values 
('java', 3.5),
('spring', 5.5),
('자바', 2.5),
('Vue', 2.7),
('스프링', 4.5),
('알고리즘', 4.9),
('algo', 2.5),
('SSAFY', 9.4),
('싸피', 6.0),
('Vuejs', 5.0),
('뷰', 3.3),
('코로나', 3.9),
('covid19', 2.5),
('웹', 4.5),
('취업', 11.15),
('삼성', 14.5),
('samsung', 12.5),
('naver', 9),
('네이버', 8),
('카카오', 7.8),
('kakao', 7),
('돈', 12),
('희망', 7),
('소망', 7),
('사랑', 7),
('합격', 11.15),
('면접', 14.5),
('코테', 12.5),
('코딩테스트', 9),
('프레임웤', 8),
('framework', 7.8),
('react', 7.2),
('frontend', 6.2),
('backend', 5.6),
('데이터베이스', 5),
('db', 3);

commit;
