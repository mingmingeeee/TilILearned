use housedata2;

select * 
from dongcode;

# housedeal -> 거래 정보
select * 
from housedeal
where aptCode = '11110000000001';

# houseinfo -> dongCode: 해당 동에 대한 아파트 정보 -> 위도 경도 이용해서 좌표 찍으면 됨
select *
from houseinfo; 

SELECT dongCode, gugunName
FROM dongcode
WHERE dongCode LIKE '11___00000'
AND gugunName IS NOT NULL;

# 시도 뽑기
SELECT dongCode, sidoName FROM dongcode WHERE dongcode LIKE '__00000000';

# 시 뽑아온 거로 구군 뽑기 
SELECT dongCode, gugunName FROM dongcode WHERE dongcode LIKE '11___00000'
	AND gugunName is not null;

# 구군 뽑아온 거로 동 뽑기
SELECT dongCode, dongName FROM dongcode WHERE dongcode LIKE '26440_____'
	AND dongName is not null;