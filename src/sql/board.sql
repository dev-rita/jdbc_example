create or replace procedure sel_board6 --sel_board6은 저장프로시저 이름
(vname out find_board.bname%type, --vname매개변수는 타입이 find_board테이블의 bname컬럼 타입을 따라간다.
--out >>오라클에서 검색되는 값 가져옴
vtitle out find_board.btitle%type,
vcont out find_board.bcont%type,
vno in find_board.bno%type --in은 값을 전달받을 때 사용
)
is
begin
	--begin~end 사이에 실제 실행되는 쿼리문 작성
	select bname,btitle,bcont into vname,vtitle,vcont from find_board where bno=vno;
end;
/
