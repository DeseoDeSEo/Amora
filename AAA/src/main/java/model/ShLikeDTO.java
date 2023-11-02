package model;

public class ShLikeDTO {
	private int shLikeNum;
	private String id;
	private int shNum;
	
	// 테이블 행 삭제 생성자
	public ShLikeDTO(int shLikeNum) {
		this.shLikeNum = shLikeNum;
	}
	
	// 테이블 행 추가 생성자
	public ShLikeDTO(String id, int shNum) {
		this.id = id;
		this.shNum = shNum;
	}
	
	// 전체 생성자
	public ShLikeDTO(int shLikeNum, String id, int shNum) {
		this.shLikeNum = shLikeNum;
		this.id = id;
		this.shNum = shNum;
	}
	
	public int getShLikeNum() {
		return shLikeNum;
	}
	public String getId() {
		return id;
	}
	public int getShNum() {
		return shNum;
	}
	
	
	
}
