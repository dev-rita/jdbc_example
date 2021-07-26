package net.java.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import net.java.dao.BoardDAOImpl;
import net.java.dto.BoardDTO;

public class BoardPro13 {
	public static void main(String[] args) {
		String driver="oracle.jdbc.OracleDriver";//oracle.jdbc는 패키지명. OracleDriver는 jdbc 오라클 드라이버 클래스명
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user="night";
		String password="night";
		
		Connection con=null;//데이터베이스 연결 참조변수 con
		CallableStatement cs=null;//저장프로시저 실행 참조변수 cs
		String sql=null;//쿼리문 저장변수
		BoardDAOImpl bdao=new BoardDAOImpl();
		Scanner scan=new Scanner(System.in);
		System.out.println("번호값을 입력>>");
		int bno=Integer.parseInt(scan.nextLine());//문자열을 번호로 입력 받아서 정수 숫자로 변경
		BoardDTO db_no=bdao.getFindNo(bno);//오라클로부터 번호검색
		
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
