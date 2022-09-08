use ssafydb;

-- 모든 사원의 모든 정보 검색.
select * 
from employees;


-- 사원이 근무하는 부서의 부서번호 검색.
select department_id
from employees;


-- 회사의 모든 부서번호 검색.
select department_id
from departments;

-- 사원이 근무하는 부서의 부서번호 검색.(중복제거)
select distinct department_id
from employees;


-- 회사에 존재하는 모든 부서.
select *
from departments;


-- 모든 사원의 사번, 이름, 급여 검색.
select employee_id, first_name, salary
from employees;


-- 모든 사원의 사번, 이름, 급여, 급여 * 12 (연봉) 검색.
select employee_id, first_name, salary
from employees;


-- 모든 사원의 사번, 이름, 급여, 급여 * 12 (연봉), 커미션, 커미션포함 연봉 검색.
select employee_id as 사원, first_name "이 름", salary as "급여", salary * 12 연봉,
		commission_pct "커미션",  (salary + salary * ifnull(commission_pct, 0)) * 12 "커미션 포함 연봉"
from employees;


-- 모든 사원의 사번, 이름, 급여, 급여에 따른 등급표시 검색.
-- 급여에 따른 등급
--   15000 이상 “고액연봉“      
--   8000 이상 “평균연봉”      
--   8000 미만 “저액연봉＂
select employee_id, first_name, salary,
		case when salary >= 15000
			 then "고액연봉"
             when salary >= 8000
             then "평균연봉"
             else "저액연봉"
		end "연봉등급"
        
from employees;



-- 부서번호가 50인 /// 사원중 급여가 7000이상인 사원의
-- = 부서 번호 50 && 사원증 급여 7000 이상
-- 사번, 이름, 급여, 부서번호
select employee_id, first_name, salary, department_id
from employees
where department_id = 50
and salary >= 7000;

-- 50이 어느 부서인지 알아보기 위하여..^^
select *
from departments
where department_id = 50;


-- 근무 부서번호가 50, 60, 70에 근무하는 사원의 사번, 이름, 부서번호
select employee_id, first_name, salary, department_id
from employees
where department_id = 50
or department_id = 60
or department_id = 70;

select employee_id, first_name, salary, department_id
from employees
where department_id in (50, 60, 70);


-- 근무 부서번호가 50, 60, 70이 아닌 사원의 사번, 이름, 부서번호
-- 50도 아니고 60도 아니고 70도 아닌 사람
select employee_id, first_name, salary, department_id
from employees
where department_id != 50
and department_id != 60
and department_id != 70;

select employee_id, first_name, salary, department_id
from employees
where not (department_id = 50
or department_id = 60
or department_id = 70);

-- 근무 부서번호가 50, 60, 70에 근무하는 사원의 사번, 이름, 부서번호
select employee_id, first_name, salary, department_id
from employees
where department_id in (50, 60, 70, 80, 90);


-- 근무 부서번호가 50, 60, 70이 아닌 사원의 사번, 이름, 부서번호 
select employee_id, first_name, salary, department_id
from employees
where department_id not in (50, 60, 70, 80, 90);


-- 급여가 6000이상 10000이하인 사원의 사번, 이름, 급여
select employee_id, first_name, salary
from employees
where salary between 6000 and 10000;


-- 근무 부서가 지정되지 않은(알 수 없는) 사원의 사번, 이름, 부서번호 검색.
select employee_id, first_name, department_id
from employees
where department_id is null;

select employee_id, first_name, department_id
from employees
where department_id is not null;


-- 커미션을 받는 사원의 사번, 이름, 급여, 커미션
select employee_id, first_name, salary, commission_pct
from employees
where commission_pct is not null;


-- 이름에 'x'가 들어간 사원의 사번, 이름
select employee_id, first_name
from employees
where first_name like '%x%';


-- 이름의 끝에서 3번째 자리에 'x'가 들어간 사원의 사번, 이름
select employee_id, first_name
from employees
where first_name like '%x__';

select employee_id, first_name
from employees
where first_name like 'A%';


-- 모든 사원의 사번, 이름, 급여
-- 단 급여순 정렬(내림차순)
select employee_id, first_name, salary
from employees
order by salary desc;
-- 기본: 오름차순 정렬 -> asc
-- 내림차순 정렬 -> desc

-- 50, 60, 70에 근무하는 사원의 사번, 이름, 부서번호, 급여
-- 단, 부서별 정렬(오름차순) 후 급여순(내림차순) 검색
select department_id, employee_id, first_name, salary
from employees
where department_id in (50, 60, 70)
order by department_id desc, salary desc;

select department_id, employee_id, first_name, salary
from employees
where department_id in (50, 60, 70)
order by 1, 4 desc;
