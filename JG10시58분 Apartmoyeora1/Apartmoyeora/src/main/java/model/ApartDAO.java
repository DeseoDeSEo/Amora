package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ApartDTO;

public class ApartDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
	//데이터베이스 연결메소드
	public void getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	    String db_url = "jdbc:oracle:thin:@project-db-stu3.smhrd.com:1524:xe";
	    String db_id = "Insa4_Spring_hacksim_3";
	    String db_pw = "aishcool3";
	      
	    conn = DriverManager.getConnection(db_url, db_id, db_pw);
	    } catch (ClassNotFoundException e) {	
			e.printStackTrace();
			System.out.println("ojdbc6.jar 또는 경로를 체크하세요!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB연결에 필요한 정보가 맞는지 체크하세요!");
		}
	    
	}
	
	//데이터베이스 종료메소드
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null){
		         psmt.close();
		      }
		      if(conn != null){
		         conn.close();
		      }
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 아파트 리스트 보기 
	public ArrayList<ApartDTO> select(){
		ArrayList<ApartDTO> list = new ArrayList<>();
		getConnection();
		String sql = "SELECT * FROM APART";
		try {
			psmt = conn.prepareStatement(sql);
			rs =  psmt.executeQuery();
			while(rs.next()){
		    	 int apart_id = rs.getInt("apart_id");
		    	 String city = rs.getString("city");
		    	 String region = rs.getString("region");
		    	 String addr = rs.getString("addr");
		    	 String name = rs.getString("name");
		    	 String post = rs.getString("post");
		    	 String apart_url = rs.getString("apart_url");
		    	 ApartDTO dto = new ApartDTO(apart_id, city, region, addr, name,post,apart_url);
		    	 list.add(dto);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	
	
	
	// 시공중인 아파트가 건설 완료시 추가 메소드
	public int insertApart(ApartDTO dto) {
		getConnection();
		
		String sql="INSERT INTO APART VALUES(APART_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, dto.getCity());
			psmt.setString(2, dto.getRegion());
			psmt.setString(3, dto.getAddr());
			psmt.setString(3, dto.getName());
			psmt.setString(3, dto.getPost());
			psmt.setString(3, dto.getApart_url());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	
	
}
