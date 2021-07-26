package net.java.controller;

import java.util.Scanner;
import net.java.dao.BoardDAOImpl;
import net.java.dto.BoardDTO;

public class BoardInsert09 {
	public static void main(String[] args) {
		BoardDAOImpl bdao=new BoardDAOImpl();
		BoardDTO bdto=new BoardDTO();
		Scanner scan=new Scanner(System.in);
		
		System.out.println("자료 저장하기>>");
		System.out.print("글쓴이>>");
		String name=scan.nextLine();
		System.out.print("제목>>");
		String title=scan.nextLine();
		System.out.print("내용>>");
		String cont=scan.nextLine();

		bdto.setBname(name);
		bdto.setBtitle(title);
		bdto.setBcont(cont);
		
		int re=bdao.insertBoard(bdto);//게시판 저장
		
		if(re==1) {
			System.out.println("게시판 저장 성공!");
		}
	}

}
