import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class BoardInsert01 {
	public static void main(String[] args) {
		String driver="oracle.jdbc.OracleDriver";//oracle.jdbc는 패키지 폴더명, oracledriver는 jdbc 드라이버 클래스 명
		//oracle.jdbc.driver.OracleDriver여도 실행됨.
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속 주소, 1521 오라클 접속 포트 번호, xe는 데이터 베이스 명
		String user="night";//오라클 사용자명
		String password="night";//사용자 비번
				
		Connection con=null;//데이터 베이스 연결 con
		Statement stmt=null;//쿼리문 수행 stmt
		String sql=null;
		
		try {
			Class.forName(driver);//jdbc 드라이버 클래스 로드
			con=DriverManager.getConnection(url,user,password);//데이터베이스 연결 객체인 Connection 객체 생성
			stmt=con.createStatement();//statement객체를 생성.
			Scanner sc=new Scanner(System.in);
			
			System.out.println("자료 저장 실습>>");
			System.out.print("글쓴이 입력>>");
			String name=sc.nextLine();
			System.out.print("제목입력>>");
			String title=sc.nextLine();
			System.out.print("내용입력>>");
			String cont=sc.nextLine();
			
			sql="insert into find_board values(bno2_seq.nextval,'"+name+"','"+title+"','"+cont+"',sysdate)";
			int re=stmt.executeUpdate(sql);//저장 쿼리문 수행 후 성공한 레코드 행의 개수를 반환
			
			if(re==1) {
				System.out.println("저장 성공!");
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(stmt !=null) stmt.close();
				if(con !=null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
}
