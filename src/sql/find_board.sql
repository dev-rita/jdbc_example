--오라클 한줄 주석문
/*한줄 이상 주석문
*/
/*
	테이블 생성 문법 형식)
	create table 테이블명(
	컬럼명(필드명) 자료형(크기) 제약조건
	,... 중략
	);
	
	제약 조건이란 컬럼에 저장되는 자료에 어떠한 제한을 가하는 것을 말한다.
*/
--find_board테이블 생성
create table find_board(
 bno number(38) primary key--번호값이 저장됨.
 ,bname varchar2(50) --글쓴이
 ,btitle varchar2(200) --제목
 ,bcont varchar2 (4000) --내용
 ,bdate date--등록 날짜
);

/*
	오라클 자료형 종류)
	1.number(38): 최대 자리수 38자 까지 정수 숫자 자료형.
	2.varchar2; : 가변문자 자료형 (크기가 가변됨. 고정크기x)
	3.date : 날짜 자료형
	
	primary key의 특징)
	1. 기본키 제약조건으로 중복 자료 저장 금지, null저장 금지
	
*/

/*자료 검색문 형식)
 * select * from 테이블명 order by 기준 컬럼명 정렬방식;
 * *는 모든 컬럼을 뜻하고 order by는 정렬문이다.
 * desc 내림차순 정렬로 숫자는 큰숫자 부터 먼저 정렬, 한글은 가나다 역순, 영어는 알파벳 역순으로 정렬된다.
 * desc정렬문은 생략 불가한다. asc는 오름차순.
 * 위의 문장은 find_board테이블에서 모든 데이터를 검색하며, bno를 기준으로 내림차순으로 정렬한다.
 */
--생성된 테이블 컬럼을 확인
select * from find_board order by bno desc;
select * from find_board where bno=2;
/* 자료 검색문 형식)
 * select 컬럼목록 from 테이블명 where 조건식 order by 기준컬럼명 asc(desc);
 * asc문은 오름차순 정렬이다. 즉 생략 가능하다. 번호는 작은 값부터, 한글은 가나다순, 영어는 알파벳 순으로 저장한다.
 */
select bno, btitle, bcont from find_board order by bno asc;--asc는 생략 가능하다.

--시퀀스 다음번호값 확인
select bno2_seq.nextval from dual; -- dual테이블은 오라클 설치 시 설치되는 기본 테이블로 오라클 함수, 연산결과값, 시퀀스값 확인등 용도로 활용된다.
--또는 오라클 날짜 함수값 확인 용도로도 사용된다. seq.nextval을 사용하면 번호가 계속 다음번호로 바뀐다.

--레코드 저장
-- 오라클 날짜함수 sysdate 
select sysdate from dual; --sysdate 오라클 날짜함수값 확인용도

/* 자료 저장문 형식
 * insert into 테이블명 (컬럼목록) values(값);
 * 전체 컬럼에 자료 저장시 컬럼목록을 생략 가능하다.
 * 레코드란 컬럼 즉 필드명에 저장된 한 행의 자료 집합을 말한다.
 */ 
insert into find_board values(bno2_seq.nextval,'홍길동','제목01','내용01',sysdate);--전체 필드명에 자료 저장시 컬럼목록을 생략한다.
--insert into 테이블명 (컬럼목록) values(값);이런 경우는 테이블 생성시 생성된 컬럼 순번대로 자료가 저장된다. 그리고 문자열 값은 ''로 감싸서 저장한다.
--전에 select bno2_seq.nextval from dual;로 시퀀스 번호값을 확인해서 bno가 2로 저장된다.

insert into find_board (bno,bname,btitle,bcont,bdate) values(bno2_Seq.nextval,'이순신','삭제 제목01','내용02',sysdate);

 /* 시퀀스 특징)
 * 1.시퀀스는 번호 발생기이다. 1부터 시작해서 1씩 증가하는 중복번호가 없고,
 * 현재 이후 번호값만 발생하고, 빈 번호값(null)이 없다.
 * 주로 기본키 즉 primary key 제약 조건으로 설정되어 있고, 
 * 정수자료형으로 된 컬럼 번호값 저장 용도로 활용된다. 주로 게시판 번호값 저장용도로 사용된다.
 * 
 * 2. 시퀀스 번호값 발생은 시퀀스명.nextval로 현재 번호값 이후 다음번호값을 발생한다.
 */
create sequence bno2_seq --bno2_seq라는 시퀀스 번호 발생기를 생성
start with 1 --1부터 시작하고
increment by 1 --1씩 증가하고
nocache; --임시 메모리를 사용하지 않는다는 뜻.

/* 자료 수정문 형식)
 * update 테이블명 set 컬럼명=변경할 값, 컬럼명=변경할 값, .. 중략 where 조건식;
*/
update find_board set bname='수정홍길동',btitle='수정제목',bcont='수정내용' where bno=2;
--2번을 기준으로 이름, 제목, 내용을 수정한다.

/*자료 삭제문 형식)
 * delete from 테이블명 where 조건식;
*/
delete from find_board where bno=2;--2번 자료 삭제