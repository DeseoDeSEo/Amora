package model;

public class GbLikeDTO {
	private int gbLikeNum;
	private String id;
	private int gbNum;
	
	// 테이블 행 삭제 생성자
	public GbLikeDTO(int gbLikeNum) {
		this.gbLikeNum = gbLikeNum;
	}
	
	// 테이블 행 추가 생성자
	public GbLikeDTO(String id, int gbNum) {
		this.id = id;
		this.gbNum = gbNum;
	}
	
	// 전체 생성자
	public GbLikeDTO(int gbLikeNum, String id, int gbNum) {
		this.gbLikeNum = gbLikeNum;
		this.id = id;
		this.gbNum = gbNum;
	}
	
	public int getGbLikeNum() {
		return gbLikeNum;
	}
	public String getId() {
		return id;
	}
	public int getGbNum() {
		return gbNum;
	}
	
	
	
}
