# CASCADE: 참조되는 테이블의 행이 삭제되었을 경우에는 참조하는 테이블과 대응되는 모든 행들이 삭제된다. 참조되는 테이블의 행이 갱신되었을 경우에는 참조하는 테이블의 외래 키 값은 같은 값으로 갱신된다.
# RESTRICT: 참조하는 테이블의 행이 남아 있는 경우에는 참조되는 테이블의 행을 갱신하거나 삭제할 수 없다. 이 경우에는 데이터 변경이 이루어지지 않는다.
# NO ACTION:참조되는 테이블에 대해 UPDATE 또는 DELETE가 실행된다. DBMS에서 SQL 문장의 실행 종료시에 참조 정합성을 만족하는지를 검사한다. RESTRICT와 차이점은, 트리거 또는 SQL 문장의 시멘틱스 자체가 외래 키의 제약을 채울 것이라는 데에 있다. 이 때는 SQL 문장 실행이 성공한다. 외래 키의 제약이 만족되지 않은 경우에는 SQL 문장 실행이 실패한다.
# SET NULL:참조되는 테이블에 대해 행이 갱신 또는 삭제되었을 경우, 참조하는 테이블의 행에 대한 외래 키 값은 NULL로 설정된다. 이 옵션은 참조하는 테이블의 외래 키에 NULL을 설정할 수 있는 경우에만 가능하다. NULL의 시멘틱스에 의해, 참조하는 테이블에 대해 NULL이 있는 행은, 참조되는 테이블의 행을 필요로 하지 않는다.
# SET DEFAULT: SET NULL과 비슷하지만, 참조되는 테이블의 행이 갱신 또는 삭제되었을 경우, 참조하는 테이블의 외래 키 값은 속성의 기본값(default)으로 설정된다.

CREATE DATABASE IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	id VARCHAR(10) PRIMARY KEY,	
	name VARCHAR(20) NOT NULL,
	password VARCHAR(10) NOT NULL
);