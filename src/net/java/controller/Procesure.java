package net.java.controller;

import java.util.Scanner;

import net.java.dao.BoardDAOImpl;

public class Procesure {
	public static void main(String[] args) {
		BoardDAOImpl bdao=new BoardDAOImpl();
		Scanner scan=new Scanner(System.in);
		System.out.print("번호값을 입력>>");
		int bno=Integer.parseInt(scan.nextLine());//문자열을 번호로 입력 받아서 정수 숫자로 변경
		bdao.storedProcedure(bno);
	}
}
