import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardEdit03 {
	public static void main(String[] args) {
		String driver="oracle.jdbc.OracleDriver";//oracle.jdbc는 패키지 폴더명, oracledriver는 jdbc 드라이버 클래스 명
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속 주소, 1521 오라클 접속 포트 번호, xe는 데이터 베이스 명
		String user="night";//오라클 사용자명
		String password="night";//사용자 비번

		Connection con=null;
		Statement st=null;
		ResultSet rs=null;//select문 수행 후 결과 레코드를 rs에 저장.
		String sql=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);//데이터베이스 연결
			st=con.createStatement();//쿼리문 수행 객체
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			//InputStreamReader는 바이트를 문자로 변경. BufferedReader는 읽어들인 문자를 버퍼링 즉 임시 저장했다가
			//한줄 끝가지 한꺼번에 효율적으로 문자열을 읽어들임.
			
			System.out.print("번호입력>>");
			int bno=Integer.parseInt(br.readLine());//읽어들인 번호 문자를 정수 숫자로 변경
			sql="select bno from find_board where bno="+bno;//오라클로 부터 번호 검색문 정수숫자로 따옴표 뺌.
			rs=st.executeQuery(sql);
			if(rs.next()) {//검색 번호값이 한개이면 if문으로 처리
				System.out.print("수정할 글쓴이>>");
				String writer=br.readLine();
				System.out.print("수정할 제목>>");
				String title=br.readLine();
				System.out.print("수정할 내용>>");
				String cont=br.readLine();
				
				sql="update find_board set bname='"+writer+"',btitle='"+title+"',bcont='"+cont+"' where bno="+bno;
				//번호를 기준으로 글쓴이, 제목, 내용을 수정. bno는 정수로 따옴표 안함.
				int re=st.executeUpdate(sql);//수정 쿼리문 성공후 성공한 레코드 행의 개수를 반환
				
				if(re==1) {
					System.out.println("수정 성공!");
				}
			}else {
				System.out.println("번호값이 없어서 수정 못함.");
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs !=null) rs.close();
				if(st != null) st.close();
				if(con !=null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
}
