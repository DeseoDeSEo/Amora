package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Con_purchaseDAO {
			// 기본값 null, 0로 설정
			private Connection conn=null;
			private PreparedStatement psmt=null;
			private ResultSet rs= null;
			int cnt=0;
			String un="";
			
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
	
	
			
			//공동구매 등록
			public int purchase(int gb_num, String id){
				
				getConnection();  
				
				String sql= "INSERT INTO gb_purchase (purchase_id, gb_Num, id) VALUES (gb_pur_seq.NEXTVAL, ?, ?)";
				
				try {
					psmt = conn.prepareStatement(sql);
					
					psmt.setInt(1, gb_num);
					psmt.setString(2, id);
					
					cnt = psmt.executeUpdate();
					
					return cnt;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				close();
				return cnt;
			}
			
			
			//공동구매 목록과 아이디를 조회하여 구매/취소 버튼을 나오게 한다
			public int purchase_check(int gb_num, String id) {
				
				getConnection();
				
				String sql = "select * from gb_purchase where gb_num = ? and id = ?";
				
				try {
					psmt = conn.prepareStatement(sql);
					
					psmt.setInt(1, gb_num);
					psmt.setString(2, id);
					
					rs = psmt.executeQuery();
					
			        if (rs.next()) {
			        	cnt= 1;
			        	 
			        	return cnt;
			        }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				close();

				return cnt;
				
			}
			
			public int purchase_del(int gb_num, String id) {
				
				getConnection();
				
				String sql = "DELETE FROM gb_purchase WHERE gb_Num = ? AND id = ?";
				
				try {
					psmt = conn.prepareStatement(sql);
					
					psmt.setInt(1, gb_num);
					psmt.setString(2, id);
					
					rs = psmt.executeQuery();
					
			        if (rs.next()) {
			        	cnt= 1;
			        	 
			        	return cnt;
			        }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				close();
				
				return cnt;
			}
			
			public int purchase_count(int gb_num) {
				int count= 0;
				
				getConnection();
				
				String sql = "SELECT gb_Num, COUNT(*) AS count FROM gb_purchase WHERE gb_Num = ? GROUP BY gb_Num";
				
				try {
					psmt = conn.prepareStatement(sql);
					
					psmt.setInt(1, gb_num);
					
					rs = psmt.executeQuery();
					
			        if (rs.next()) {
			        	count = rs.getInt(2);
			        	return count;
			        }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				close();
				
				return count;
			}




			

			
			
			
			
			
			
			
			
}
