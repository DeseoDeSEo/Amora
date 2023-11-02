package model;

public class ComLikeDTO {
	private int comLikeNum;
	private String id;
	private int comNum;
	
	// 테이블 행 삭제 생성자
	public ComLikeDTO(int comLikeNum) {
		this.comLikeNum = comLikeNum;
	}
	
	// 테이블 행 추가 생성자 , 조회 생성자
	public ComLikeDTO(String id, int comNum) {
		this.id = id;
		this.comNum = comNum;
	}
	
	// 전체 생성자
	public ComLikeDTO(int comLikeNum, String id, int comNum) {
		this.comLikeNum = comLikeNum;
		this.id = id;
		this.comNum = comNum;
	}
	
	public int getComLikeNum() {
		return comLikeNum;
	}
	public String getId() {
		return id;
	}
	public int getComNum() {
		return comNum;
	}
	
	
	
}
