import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BoardDel04 {
	public static void main(String[] args) {
		String driver="oracle.jdbc.OracleDriver";//oracle.jdbc는 패키지명, OracleDriver는 jdbc 드라이버 클래스명
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속 주소, 1521 오라클 접속 포트 번호, xe는 데이터 베이스 명
		String user="night";//오라클 사용자명
		String password="night";//사용자 비번
		
		Connection con=null;//데이터 베이스 연결 con
		Statement st=null;//쿼리문 수행st
		ResultSet rs=null;//검색결과 자료를 저장할 rs
		String sql=null;//쿼리문 저장 변수
		
		try {
			Class.forName(driver);//jdbc드라이버 클래스 로드
			con=DriverManager.getConnection(url, user, password);
			st=con.createStatement();
			
			Scanner scan=new Scanner(System.in);
			System.out.print("번호 입력>>");
			int bno=Integer.parseInt(scan.nextLine());//문자열을 정수숫자로변경
			sql="select * from find_board where bno="+bno;
			rs=st.executeQuery(sql);
			
			if(rs.next()) {//번호값이 있다면
				sql="delete from find_board where bno="+bno;
				int re=st.executeUpdate(sql);//삭제 성공 후 성공한 레코드 행의 개수 반환.
				
				if(re==1) {
					System.out.println("삭제 성공");
				}
			}else {
				System.out.println("번호가 없어서 삭제 못함.");
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs !=null) rs.close(); //객체가 만들어졌으면 close()
				if(st !=null) st.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
}
