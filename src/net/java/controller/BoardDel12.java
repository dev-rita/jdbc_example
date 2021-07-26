package net.java.controller;

import java.util.Scanner;

import net.java.dao.BoardDAOImpl;
import net.java.dto.BoardDTO;

public class BoardDel12 {
	public static void main(String[] args) {
		BoardDAOImpl bdao=new BoardDAOImpl();
		Scanner scan=new Scanner(System.in);
		System.out.print("게시판 번호 입력>>");
		int bno=Integer.parseInt(scan.next());

		BoardDTO db_no=bdao.getFindNo(bno);//오라클 db로 부터 번호값 검색.
		if(db_no!=null) {
			int re=bdao.delBoard(bno);
			if(re==1) {
				System.out.println("삭제 성공했습니다.");
			}
		}else {
			System.out.println("번호 값이 없어서 삭제 못합니다.");
		}
	}	
}
