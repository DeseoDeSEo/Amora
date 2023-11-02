package model;

public class ShDTO {


	private int sh_Num;
	private String sh_title;
	private String sh_content;
	private String sh_date;
	private int sh_price;
	private int sh_views;
	private int sh_report;
	private String fileName;
	private String fileRealName;
	private String section;
	private int sh_likes;
	private String id;
	private String nickname;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSh_likes() {
		return sh_likes;
	}

	public void setSh_likes(int sh_likes) {
		this.sh_likes = sh_likes;
	}

	public void setSh_Num(int sh_Num) {
		this.sh_Num = sh_Num;
	}

	public void setSh_title(String sh_title) {
		this.sh_title = sh_title;
	}

	public void setSh_content(String sh_content) {
		this.sh_content = sh_content;
	}

	public void setSh_date(String sh_date) {
		this.sh_date = sh_date;
	}

	public void setSh_price(int sh_price) {
		this.sh_price = sh_price;
	}

	public void setSh_views(int sh_views) {
		this.sh_views = sh_views;
	}

	public void setSh_report(int sh_report) {
		this.sh_report = sh_report;
	}

	public void setSection(String section) {
		this.section = section;
	}

	

	public int getSh_Num() {
		return sh_Num;
	}

	public String getSh_title() {
		return sh_title;
	}

	public String getSh_content() {
		return sh_content;
	}

	public String getSh_date() {
		return sh_date;
	}

	public int getSh_price() {
		return sh_price;
	}

	public int getSh_views() {
		return sh_views;
	}

	public int getSh_report() {
		return sh_report;
	}

	public String getSection() {
		return section;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

}
