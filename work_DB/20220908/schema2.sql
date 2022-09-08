# 1. 
use world;

# 2. 
desc city;
desc country;
desc countrylanguage;

# 3. 
select * 
from country 
where code='KOR';

# 4. 
select code, name, gnp, gnpold , gnp-gnpold "gnp변동량" 
from country 
where gnp-gnpold > 0
order by gnp변동량;

# 5. 
select distinct(continent) 
from country
order by char_length(continent);

# 6.
select concat(name,'은 ', region,'에 속하며 인구는 ', population,'명이다.') "정보" 
from country 
where continent='asia'
order by name;

# 7.
select name, continent, gnp, population 
from country 
where indepyear is null and population >= 10000 
order by population;

# 8.
select code, name, Population 
from country 
where Population between 100000000 and 200000000 
order by 3 desc limit 3;

# 9. 
select code, name, indepyear 
from country 
where indepyear in(800, 1810, 1811, 1901) 
order by indepyear, code desc;

# 10.
select code, name, region 
from country 
where region like '%asia%' and name like '_o%';

# 11. 
select char_length('홍길동') "한글", char_length('hong') "영문"
from dual;

# 12. 
select code, name, governmentform 
from country 
where char_length(name) >= 10 and governmentform like '%republic%' 
order by char_length(name) desc limit 10;

# 13.
select code, name 
from country 
where substr(code, 1, 1) in ('a', 'e', 'i', 'o', 'u') 
order by name limit 2, 3;

# 14. 
select name, concat(left(name, 2), lpad('*', char_length(name)-4, '*'), right(name, 2)) 'guess'
from country;

# 15. 
select replace(region, ' ', '_') 지역들
from country
group by region
order by char_length(region) desc;

# 16. 
select name, surfacearea, population, round((surfacearea/population),3) '1인당 점유면적'
from country
where population >= 100000000
order by 2;