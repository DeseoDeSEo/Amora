package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersDAO {

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
		
		//회원가입 메소드(관리자)
		public int adminJoin(UsersDTO dto) {
			getConnection();
			try {
				String sql = "INSERT INTO USERS VALUES(?,?,?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),SYSDATE,?,'A',?,NULL,0,'Y')";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPw());
				psmt.setString(3, dto.getNickname());
				psmt.setString(4, dto.getName());
				psmt.setString(5, dto.getPhone());
				psmt.setString(6, dto.getEmail());
				psmt.setString(7, dto.getBirthdate());
				psmt.setString(8, dto.getGender());
				psmt.setString(9, dto.getAddr());
				cnt = psmt.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				close();
			}
			return cnt;
		}
		//회원가입 서비스 메소드(유저, 입주민)
		public int userJoin(UsersDTO dto) {
			getConnection();
			try {
				String sql="INSERT INTO USERS VALUES(?,?,?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),SYSDATE,?,'U',?,?,0,'N')";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPw());
				psmt.setString(3, dto.getNickname());
				psmt.setString(4, dto.getName());
				psmt.setString(5, dto.getPhone());
				psmt.setString(6, dto.getEmail());
				psmt.setString(7, dto.getBirthdate());
				psmt.setString(8, dto.getGender());
				psmt.setString(9, dto.getAddr());
				psmt.setString(10, dto.getDongho());
				cnt = psmt.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				close();
			}
			return cnt;
		}
		
		//로그인 서비스 메소드(입주민, 관리자 공통)
		public UsersDTO login(UsersDTO dto) {
			// TODO Auto-generated method stub
			getConnection();
			UsersDTO info= null;
			try {
				String sql = "SELECT ID, NICKNAME, ROLE, ADDR FROM USERS WHERE ID=? and PW=?";
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPw());
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					String id = rs.getString(1);
					String nickname = rs.getString(2);
					String role = rs.getString(3);
					String addr = rs.getString(4);

					info= new UsersDTO(id, nickname, role, addr);

					info = new UsersDTO(id, nickname, role, addr);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return info;
			
			
			
		}
		
		// 탈퇴 서비스 메소드
		public int dropout(String id) {
			getConnection();
			try {
				String sql ="DELETE FROM USERS WHERE ID = ?";
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, id);
				cnt=psmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return cnt;
		}
		
		// 승인여부를 보여주는 서비스
		public UsersDTO showNonApprove(String admin_addr) {
			getConnection();
			UsersDTO confirm = null;
			try {
				String sql="SELECT ID, NICKNAME, NAME, ADDR, DONGHO, APPROVAL FROM USERS WHERE ADDR=? AND APPROVAL ='N'";
				psmt=conn.prepareStatement(sql);
				psmt.setString(1,admin_addr); //관리자가 로그인하면 세션에 저장된 ADDR 
				while(rs.next()) {
					String id=rs.getString(1);
					String nickname=rs.getString(2);
					String name=rs.getString(3);
					String addr=rs.getString(4);
					String dongho=rs.getString(5);
					String approval =rs.getString(6);
					
					confirm = new UsersDTO(id, nickname, name, addr,dongho, approval);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return confirm;
		}
		// 승인서비스
		public int approval(String addr) {
			getConnection();
			try {
				String sql ="UPDATE FROM USERS SET APPROVAL ='Y' WHERE ADDR =? AND APPROVAL ='N'";
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, addr);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return cnt;
		}
		
		// 업데이트 메소드(회원정보 수정)
		public int updateUsers(UsersDTO dto) {
			getConnection();
			
			String sql="UPDATE FROM USERS SET PW=?, NICKNAME=?, EMAIL=?, PHONE=? WHERE ID=?";
			try {
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, dto.getPw());
				psmt.setString(2, dto.getNickname());
				psmt.setString(3, dto.getEmail());
				psmt.setString(4, dto.getPhone());
				psmt.setString(5, dto.getId());
				cnt=psmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			
			return cnt;
			
			
			
		}
		
		// 회원정보 수정에서 빈칸을 채워주기 위한 메소드 getpw, getphone(세션에 저장되지 않는 정보)
		public String getPw(String id) {
			getConnection();
			String sql="SELECT PW FROM USERS WHERE ID =?";
			try {
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, id);
				rs=psmt.executeQuery();
				if(rs.next()) {
					un = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close();
			}
			
			return un;
		}
		
		public String getPhone(String id) {
			getConnection();
			String sql="SELECT PHONE FROM USERS WHERE ID =?";
			try {
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, id);
				rs=psmt.executeQuery();
				if(rs.next()) {
					un = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close();
			}
			
			return un;
		}
}
