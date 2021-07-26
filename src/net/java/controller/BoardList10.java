package net.java.controller;
import java.util.List;
import net.java.dao.BoardDAOImpl;
import net.java.dto.BoardDTO;

public class BoardList10 {
	public static void main(String[] args) {
		BoardDAOImpl bdao=new BoardDAOImpl();
		List<BoardDTO> blist=bdao.getBoardList();//게시판 목록

		System.out.println("no \t title \t name \t cont \t date");
		System.out.println("=====================================================");

		if((blist !=null)&&(blist.size()>0)) {
			for(BoardDTO b:blist) {
				System.out.println(b.getBno()+"\t"+b.getBtitle()+"\t"+b.getBname()
				+"\t"+b.getBcont()+"\t"+b.getBdate());
			}
		}else {
			System.out.println("게시판 목록이 없다!");
		}//이클립스 개발툴에서 ctrl+a 전체선택하고 ctrl+i 하면 정렬된다.
	}
}
