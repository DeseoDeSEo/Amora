package model;

public class ApartDTO {
	private int id;
	private String city;
	private String region;
	private String addr;
	private String name;
	private String post;
	private String apart_url;
	public int getId() {
		return id;
	}
	public String getCity() {
		return city;
	}
	public String getRegion() {
		return region;
	}
	public String getAddr() {
		return addr;
	}
	public String getName() {
		return name;
	}
	public String getPost() {
		return post;
	}
	public String getApart_url() {
		return apart_url;
	}
	public ApartDTO(int id, String city, String region, String addr, String name, String post, String apart_url) {
		this.id = id;
		this.city = city;
		this.region = region;
		this.addr = addr;
		this.name = name;
		this.post = post;
		this.apart_url = apart_url;
	}
	
	
	
	
}
