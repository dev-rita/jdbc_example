import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BoardDel08 {
	public static void main(String[] args) {
		String driver="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user="night";
		String password="night";
		
		Connection con=null;
		PreparedStatement pt=null;
		ResultSet rs=null;
		String sql=null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			Scanner scan=new Scanner(System.in);
			System.out.print("번호 입력>>");
			int bno=Integer.parseInt(scan.nextLine());
			sql="select bno from find_board where bno=?";
			pt=con.prepareStatement(sql);//쿼리문 미리 컴파일
			pt.setInt(1, bno);
			rs=pt.executeQuery();
			
			if(rs.next()) {
				sql="delete from find_board where bno=?";
				pt=con.prepareStatement(sql);
				pt.setInt(1, bno);
				int re=pt.executeUpdate();//삭제 쿼리문 성공 후 성공한 레코드 행의 개수를 반환
				
				if(re==1) {
					System.out.println("삭제 성공!");
				}
			}else {
				System.out.println("번호가 없어서 삭제 못함.");
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pt!=null) pt.close();
				if(con!=null) con.close();
			}
			catch(Exception e) {e.printStackTrace();}
		}
	}
}
