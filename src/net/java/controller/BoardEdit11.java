package net.java.controller;

import java.util.Scanner;

import net.java.dao.BoardDAOImpl;
import net.java.dto.BoardDTO;

public class BoardEdit11 {

	public static void main(String[] args) {
		BoardDAOImpl bdao=new BoardDAOImpl();
		Scanner scan=new Scanner(System.in);
		System.out.print("게시판 번호 입력>>");
		int bno=Integer.parseInt(scan.nextLine());
		
		BoardDTO db_no=bdao.getFindNo(bno);//오라클로부터 번호값을 검색
		if(db_no == null) {
			System.out.println("번호 값이 없어서 수정 못합니다!");
		}else {
			System.out.println("검색된 번호 값:"+db_no.getBno());
			System.out.print("수정할 글쓴이>>");
			String name=scan.nextLine();
			System.out.print("수정할 제목>>");
			String title=scan.nextLine();
			System.out.print("수정할 내용>>");
			String cont=scan.nextLine();
			
			BoardDTO eb=new BoardDTO();
			eb.setBname(name); eb.setBtitle(title); eb.setBcont(cont);eb.setBno(bno);
			
			int re=bdao.editBoard(eb);//번호를 기준으로 글쓴이, 제목, 내용을 수정
			// 번호를 기준으로 글쓴이, 제목, 내용을 수정하는 editBoard(eb)메소드를 작성.
			if(re==1) {
				System.out.println("수정 성공!");
			}
		}
	}

}
