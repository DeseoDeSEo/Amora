package model;

public class GbcDTO {
	private int gbcNum;
	private String gbcContent;
	private String id;
	private String gbcDate;
	private int gbcReport;
	private String role;
	private int dadat;
	private String nickname;
	private int gbNum;
	public int getGbNum() {
		return gbNum;
	}

		// 수정용 dto nickname과 comcNum, dadat이 같은걸 where절로 줘서 삭제 가능하게 
		public GbcDTO(String nickname,int gbcNum,  int dadat ) {
			this.nickname=nickname;
			this.gbcNum =gbcNum;
			this.dadat= dadat;
		}
		
		// session에서 가져오기용
		public GbcDTO(String id, String role, String nickname) {
			this.id = id;
			this.role = role;
			this.nickname = nickname;
		}
		
		// 
		
		
		//글쓰기용 dto
		
		public GbcDTO( String gbcContent, String id, int gbNum, String role, int dadat) {
			this.gbcContent = gbcContent;
			this.id = id;
			this.gbNum = gbNum;
			this.role = role;
			this.dadat = dadat;
		
		}
	
	
	//getters
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getGbcNum() {
		return gbcNum;
	}
	public String getGbcContent() {
		return gbcContent;
	}
	public String getId() {
		return id;
	}
	public String getGbcDate() {
		return gbcDate;
	}
	public int getGbcReport() {
		return gbcReport;
	}
	public String getRole() {
		return role;
	}
	public int getDadat() {
		return dadat;
	}
	
	
}
