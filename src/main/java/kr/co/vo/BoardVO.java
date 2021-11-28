package kr.co.vo;

public class BoardVO {	
	private int bno;
	private String title;
	private String content;
	private String cuser;
	private String cdate;
	private String udate;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCuser() {
		return cuser;
	}
	public void setCuser(String cuser) {
		this.cuser = cuser;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	
	public void getParameter() {
		System.out.println("bno :: " + this.getBno());
		System.out.println("작성자 :: " + this.getCuser());
		System.out.println("제목 :: " + this.getTitle());
		System.out.println("내용 :: " + this.getContent());
		System.out.println("날짜 :: " + this.getCdate());
	}
	
}
