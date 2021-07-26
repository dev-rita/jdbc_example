import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BoardEdit07 {

	public static void main(String[] args) {
		String driver="oracle.jdbc.driver.OracleDriver";//oracle.jdbc.driver는 패키지 폴더명, OracleDriver는 jdbc 드라이버 클래스명.
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속 주소, 1521 포트번호, xe 데이터베이스명
		String user="night";
		String password="night";

		Connection con=null;//데이터 베이스 연결 con
		PreparedStatement pstmt=null;//쿼리문 수행 프로그램실행속도가 빠르고 가독성이좋음. 코드라인이 길어짐.
		ResultSet rs=null;
		String sql=null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			Scanner scan=new Scanner(System.in);
			
			System.out.print("게시판 번호 입력>>");
			int bno=Integer.parseInt(scan.nextLine());
			sql="select bno from find_board where bno=?";//오라클로 부터 번호값 검색
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);//쿼리문의 첫 번째 물음표에 정수 숫자로 번호값 저장
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//검색번호가 있는 경우
				//pstmt.close();//해당 문구가 없으면 pstmt is not closed at this location라고 경고.
				System.out.print("수정할 이름 입력>>");
				String name=scan.nextLine();
				System.out.print("수정할 제목 입력>>");
				String title=scan.nextLine();
				System.out.print("수정할 내용 입력>>");
				String cont=scan.nextLine();
				
				sql="update find_board set bname=?, btitle=?, bcont=? where bno=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,name);//쿼리문 첫 번째 물음표에 문자열로 수정할 이름 저장
				pstmt.setString(2, title);
				pstmt.setString(3, cont);
				pstmt.setInt(4, bno);
				
				int re=pstmt.executeUpdate();//수정 성공 후 성공한 레코드행의 개수 반환
				if(re==1) {
					System.out.println("수정 성공!");
				}else {
					System.out.println("번호가 없어서 수정 못함!");
				}
			}else {
				System.out.println("번호가 없어서 수정못함!");
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
}
