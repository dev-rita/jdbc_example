package net.java.dto;

public class BoardDTO {//중간 자료 저장빈 클래스
	//되도록이면 테이블 컬렴명과 변수명을 같게한다. 이유는 코드를 줄일 수 있다.

	private int bno;//게시판 번호
	private String bname;//글쓴이
	private String btitle;//제목
	private String bcont;//글내용
	private String bdate;//등록 날짜
		
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcont() {
		return bcont;
	}
	public void setBcont(String bcont) {
		this.bcont = bcont;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
}
