import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BoardList09 {
	public static void main(String[] args) {
		String driver="oracle.jdbc.driver.OracleDriver";//oracle.jdbc.driver는 패키지 폴더명, OracleDriver는 jdbc 드라이버 클래스명.
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속 주소, 1521 포트번호, xe 데이터베이스명
		String user="night";
		String password="night";

		Connection con=null;//데이터 베이스 연결 con
		PreparedStatement pstmt=null;//쿼리문 수행 프로그램 실행 속도가 빠르고 가독성이 좋음. 코드라인이 길어짐.
		ResultSet rs=null;
		String sql=null;
		
		try {
			Class.forName(driver);//jdbc 드라이버 클래스 로드
			con=DriverManager.getConnection(url, user, password);
			Scanner scan=new Scanner(System.in);
			
			System.out.print("검색할 제목 입력>>");
			String title=scan.nextLine();
			sql="select * from find_board where btitle=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,title);
			rs=pstmt.executeQuery();//select문인 경우는 executeQuery()메소드로 쿼리문 실행.
			System.out.println("no \t title \t name \t cont \t date");
			System.out.println("=============================================");
			if(rs.next()){
				//다음 레코드행이 존재하면 참
				System.out.println(rs.getInt(1)+"\t"+rs.getString(3)+"\t"+rs.getString(2)+"\t"+
				rs.getString(4)+"\t"+rs.getString(5));//1,2,3,4,5의 뜻은 select문 뒤에 검색되는 컬럼 순번을 뜻한다.
				//해당 필드 즉 컬럼에 저장된 자료가 숫자정수이면 getInt()메소드로 레코드를 가져오고, 문자열이나 날짜형이면 getString()메소드로 가져온다.
			}else {System.out.println("일치한 내용이 없습니다.");}
		}catch(Exception e){ e.printStackTrace();}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
				//역순으로 닫은 이유는  가장 마지막에 만들어진 것을 먼저 닫아주면 메모리낭비가 덜 심하기 때문이다.(관례적)
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
