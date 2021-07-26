package net.java.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.java.dto.BoardDTO;

public class BoardDAOImpl {//JDBC 
	String driver="oracle.jdbc.OracleDriver";//oracle.jdbc는 패키지명, OracleDriver는 jdbc 드라이버 클래스명
	String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user="night";
	String password="night";

	Connection con=null;//오라클 연결 con
	Statement st=null;//쿼리문 수행 st
	PreparedStatement pt=null;//쿼리문 수행 pt
	ResultSet rs=null;//
	String sql=null;

	public int insertBoard(BoardDTO b){
		int re=-1;//저장 실패 시 반환 값
		try {
			Class.forName(driver);//jdbc 드라이버 클래스 로드 실행
			con=DriverManager.getConnection(url, user, password);
			st=con.createStatement();
			sql="insert into find_board (bno,bname,btitle,bcont,bdate) values(bno2_seq.nextval,'"+b.getBname()+"','"+b.getBtitle()+"','"+b.getBcont()+"',sysdate)";
			re=st.executeUpdate(sql);//저장 쿼리문 성공 시 성공한 레코드 행의 개수를 반환
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(st!=null) st.close();
				if(con!=null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}//insert()

	//목록
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> blist=new ArrayList<>();//BoardDTO타입만 저장. 업캐스팅 하면서 컬렉션 제네릭 blist생성.

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			sql="select * from find_board order by bno desc";//번호를 기준으로 내림차순으로 정렬
			pt=con.prepareStatement(sql);//쿼리문을 미리 컴파일하여 수행 pt생성
			rs=pt.executeQuery();//select검색 쿼리문을 실행할 때는 excuteQuery()메소드를 사용한다.

			while(rs.next()) {//다음 레코드 행이 존재하면 참. 복수개 레코드행일때는 while반복문으로 처리(1개는 if문)
				BoardDTO b=new BoardDTO();
				b.setBno(rs.getInt("bno"));//bno컬럼 레코드가 정수 숫자이면 getInt()메소드로 가져옴.
				b.setBname(rs.getString("bname"));//bname컬럼 레코드가 문자열이면 getString()메소드로 가져옴.
				b.setBtitle(rs.getString("btitle"));
				b.setBcont(rs.getString("bcont"));
				b.setBdate(rs.getString("bdate"));

				blist.add(b);
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return blist;
	}//getBoardList()

	public BoardDTO getFindNo(int bno) {
		BoardDTO db_no=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			sql="select bno from find_board where bno=?";//번호를 기준으로 데이터베이스로 부터 번호 값 검색
			pt=con.prepareStatement(sql);///쿼리문을 미리 컴파일 하여 수행 pt생성
			pt.setInt(1, bno);//쿼리문 물음표(?)에 정수숫자로 번호값 저장.
			rs=pt.executeQuery();
			if(rs.next()) {//번호값 있으면 참
				db_no=new BoardDTO();
				db_no.setBno(rs.getInt("bno"));//bno컬럼 번호가 정수 숫자이면 getInt()메소드로 가져와서 저장.
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return db_no;
	}

	public int editBoard(BoardDTO eb) {
		int re=-1;//저장 실패 시 반환 값
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			/*sql="update find_board set bname='"+eb.getBname()+"',btitle='"+eb.getBtitle()+
					"',bcont='"+eb.getBcont()+"' where bno="+eb.getBno();*///번호를 기준으로 데이터베이스로 부터 번호 값 검색
			sql="update find_board set bname=?,btitle=?,bcont=? where bno=?";
			pt=con.prepareStatement(sql);///쿼리문을 미리 컴파일 하여 수행 pt생성
			pt.setString(1,eb.getBname());
			pt.setString(2,eb.getBtitle());
			pt.setString(3,eb.getBcont());
			pt.setInt(4,eb.getBno());
			
			re=pt.executeUpdate();//수정 쿼리문 성공 후 성공한 레코드 행의 개수를 반환.
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}

	public int delBoard(int bno) {
		int re=-1;//삭제 실패시 반환값. 보통 -1로 줌.
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			sql="delete from find_board where bno=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,bno);//첫 번째 물음표에 정수숫자로 번호값 저장
			re=pt.executeUpdate();//삭제 쿼리문 수행 후 성공한 레코드 행의 개수를 반환
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt !=null) pt.close();
				if(con !=null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}
	
	CallableStatement cs=null;
	
	public void storedProcedure(int bno) {
		BoardDTO db_no=this.getFindNo(bno);
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			if(db_no==null) {
				System.out.println("번호값이 없어서 저장 프로시저 수행못함!");//매개변수가 없어서 수행 못함.
			}else {
				sql="{call sel_board6(?,?,?,?)}";//저장 프로시저 호출 쿼리문
				cs=con.prepareCall(sql);//저장 프로시저 실행 cs 생성
				cs.setInt(4, bno);//4 번째 물음표에 정수숫자로 번호 저장
				
				cs.registerOutParameter(1, java.sql.Types.VARCHAR);//출력될 첫 번째 물음표 타입을 문자로 지정
				cs.registerOutParameter(2, java.sql.Types.VARCHAR);
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
				
				cs.execute();//저장 프로시저 실행
				System.out.println("no \t title \t name \t cont");
				System.out.println("===================================");
				System.out.println(bno+"\t"+cs.getString(2)+"\t"+cs.getString(1)+"\t"+cs.getString(3));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(cs!=null) cs.close();
				if(con!=null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
}
