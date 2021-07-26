import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BoardInsert05 {
	public static void main(String[] args) {
		String driver="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user="night";
		String password="night";

		Connection con=null;
		PreparedStatement pstmt=null;//쿼리문 수행 프로그램실행속도가 빠르고 가독성이좋음. 코드라인이 길어짐.
		String sql=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			Scanner scan=new Scanner(System.in);
			
			System.out.println("자료저장하기>>");
			System.out.print("글쓴이 입력>>");
			String bname=scan.nextLine();
			System.out.print("제목입력>>");
			String title=scan.nextLine();
			System.out.print("내용입력>>");
			String cont=scan.nextLine();
			
			sql="insert into find_board values(bno2_seq.nextval,?,?,?,sysdate)";
			//sysdate는 오라클 날짜함수.
			pstmt=con.prepareStatement(sql);//쿼리문을 컴파일하여 실행 pstmt생성
			pstmt.setString(1,bname);//쿼리문의 첫번째 물음표에 문자열로 이름을 저장
			pstmt.setString(2,title);//2번째 물음표에 title을 저장
			pstmt.setString(3,cont);
			int re=pstmt.executeUpdate();//저장쿼리문 수행후 성공한 레코드 행의 개수를 반환
			
			if(re==1) {
				System.out.println("저장 성공!");
			}
		}catch(Exception e) {e.printStackTrace();}

		finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			if (con != null)
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
		//생성 역순대로 close() 관례적으로 마지막에 생성한 것을  맨 먼저 닫으면 메모리 낭비가 없다.
		//Connection을 close() 해주지 않으면 사용하지 않는 연결이 유지됨 DB 자원을 낭비하게 됨
	}
}

