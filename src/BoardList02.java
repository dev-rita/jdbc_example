import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardList02 {

	public static void main(String[] args) {
		String driver="oracle.jdbc.driver.OracleDriver";//oracle.jdbc.driver는 패키지명, OracleDriver는 jdbc 드라이버 클래스명
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속 주소, 1521 오라클 접속 포트 번호, xe는 데이터 베이스 명
		String user="night";//오라클 사용자명
		String password="night";//사용자 비번
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;//select문 수행 후 결과 레코드 행을 저장할 rs
		String sql=null;
		
		try {
			Class.forName(driver);//jdbc 드라이버 클래스 로드 실행
			con=DriverManager.getConnection(url, user, password);//데이터 베이스 연결 con생성
			st=con.createStatement();
			sql="select * from find_board order by bno desc";//번호를 기준으로 내림차순 정렬
			rs=st.executeQuery(sql);
			
			System.out.println("no \t title \t name \t cont \t date");
			System.out.println("============================================");
			while(rs.next()) {//next()는 다음 레코드 행이 존재하면 참.
				System.out.println(rs.getInt("bno")+"\t"+rs.getString("btitle")+"\t"+rs.getString("bname")+"\t"+
						rs.getString("bcont")+"\t"+rs.getString("bdate"));//getNSString()메소드도 잘쓰임.
				//각 컬럼에 저장된 레코드값이 정수 숫자이면 getInt()메소드로 가져오고, 문자열이나 날짜형은 getString()메소드로 가져온다.
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs !=null) rs.close();
				if(st !=null) st.close();
				if(con !=null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
}
