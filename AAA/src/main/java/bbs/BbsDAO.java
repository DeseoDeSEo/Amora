package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BbsDAO {
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	

	
	

	
	
	//***연결메소드
	public void BbsDAO() {

		// 1. 드라이버 동적로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    String db_url = "jdbc:oracle:thin:@project-db-stu3.smhrd.com:1524:xe";
		    String db_id = "Insa4_Spring_hacksim_3";
		    String db_pw = "aishcool3";

			conn = DriverManager.getConnection(db_url, db_id, db_pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ojbc6.jar또는 경로를 체크하세요!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DB연결에 필요한 정보가 맞는지 체크하세요!");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//***데이터베이스 종료메소드
	public void Bbsclose() {
		try {		
			if (rs != null) {
				rs.close();
			}		
			if (psmt != null) {
				psmt.close();
			}	
			if (conn != null) {
				conn.close();
			}		
		}catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	
	
	

	
	
	
	
	//***현재시간을 가져오는 함수
	public String getDate() {
		BbsDAO();
		
		String SQL ="SELECT SYSDATE FROM DUAL;";
		try {
			PreparedStatement psmt = conn.prepareStatement(SQL);
			rs = psmt.executeQuery();

			if(rs.next()) {
				return rs.getString(1); 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		
		}finally {
			Bbsclose();
		}
		return ""; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	    //***글의 순서를 표시하는 메소드
		public int getNext() {
			BbsDAO();
			 String SQL = "SELECT no_num FROM NOTICE ORDER BY no_num DESC"; 
			try {
			
				PreparedStatement psmt = conn.prepareStatement(SQL);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					//1를 더해서 그 다음게시글이 나 올수 있도록 한다
					return rs.getInt(1) + 1; 
				}
				//현재가 첫번째 게시물인 경우
				return 1; 
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				Bbsclose();
			}
			return -1; 
		}
	

	
		
		
		
		
		
		
		
		
		
		
		
		
		
	

		//*****실제로 글을 작성하는 함수
		public int write(String no_title, String no_content, String id , String nickname) {
			BbsDAO();
			String SQL ="INSERT INTO NOTICE VALUES(NO_NUM_SEQ.NEXTVAL,?,?,?,?, 0,SYSDATE,'A')";
			
			try {			
				PreparedStatement psmt = conn.prepareStatement(SQL);
				
				//psmt.setInt(1, getNext());
				psmt.setString(1, no_title);
				psmt.setString(2, no_content);
				psmt.setString(3, id);
				psmt.setString(4, nickname);
			
				return psmt.executeUpdate();
				
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					Bbsclose();
				}
				//데이터베이스 오류발생시 -1을 반환한다 
				//게시글로 적젎치 않은 -1번호를 알려줌으로서 오류발생을 알수있게 한다 
				return -1; 
			}
	


		
		
		
		
		
		
		
		
		
		
		
		
		
	
	//*****데이터베이스에서 글의 목록을 가져오는 소스코드
	public ArrayList<Bbs> getList(int pageNumber){
		BbsDAO();
		
		String SQL = " SELECT * FROM( SELECT * FROM NOTICE WHERE NO_NUM < ? AND ROLE = 'A' ORDER BY NO_NUM DESC)WHERE ROWNUM <=10 ";

		ArrayList<Bbs>list = new ArrayList<Bbs>(); 
	
		try {
			PreparedStatement psmt = conn.prepareStatement(SQL);			
			psmt.setInt(1, getNext() - (pageNumber -1) * 10);					
			rs = psmt.executeQuery();
						
			while(rs.next()) {
				
				Bbs bbs = new Bbs(); 
				
				bbs.setNo_num(rs.getInt(1));
				bbs.setNo_title(rs.getString(2));
				bbs.setNo_content(rs.getString(3));
				bbs.setId(rs.getString(4));
				bbs.setNickname(rs.getString(5));
				bbs.setNo_views(rs.getInt(6));
				bbs.setNo_date(rs.getString(7));
				bbs.setRole(rs.getString(8));
		
				list.add(bbs);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Bbsclose();
		}
		return list;
	    }

		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//게시글이 10개라면 다음페이지라는 버튼이 없어야한다 
	//*****페이징처리를 위한 함수//특정한페이지가 존재하는지 물어보는 함수 
	public boolean nextPage(int pageNumber) {
		BbsDAO();
	
		String SQL = "SELECT * FROM NOTICE WHERE no_num < ? "; 
			
		try {
			
			PreparedStatement psmt = conn.prepareStatement(SQL);					
			psmt.setInt(1, getNext() - (pageNumber -1 ) * 10);								
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Bbsclose();
		}
	
		return false; 			
		}		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	 // *****글내용을 불러오는 함수 //특정한 아이디에 해당하는 게시들을 가져올수 있도록 한다
	 public Bbs getBbs(int no_num){	
		 BbsDAO();

			String SQL = "SELECT * FROM NOTICE WHERE no_num = ?"; 
			
			try {
				
				PreparedStatement psmt = conn.prepareStatement(SQL);
				psmt.setInt(1, no_num);
										
				rs = psmt.executeQuery();
								
				if(rs.next()) {							
					Bbs bbs = new Bbs(); 
					
					bbs.setNo_num(rs.getInt(1));
					bbs.setNo_title(rs.getString(2));
					bbs.setNo_content(rs.getString(3));
					bbs.setId(rs.getString(4));
					bbs.setNickname(rs.getString(5));
					bbs.setNo_views(rs.getInt(6));
					bbs.setNo_date(rs.getString(7));
					bbs.setRole(rs.getString(8));
					
					return bbs;
				
				}	
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				Bbsclose();
			}
			//해당글이 존재하지 않는경우 null을 반환한다
			return null; 			
			}		
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //***글을 수정하는 함수 만들기 
	 public int update(int no_num, String no_title, String no_content ) {
		 BbsDAO();
		    //특정한 아이디에 해당하는 제목과 내용을 바꿔준다
			String SQL ="UPDATE NOTICE SET no_title = ?, no_content = ? WHERE no_num = ? ";
			
			try {			
				//안쪽에서 PreparedStatement를 넣어준다
				//PreparedStatement로 해서 연결객체 conn을 이용해서 SQL문장을 실행 준비단계로 만들어놓는다
				
				PreparedStatement psmt = conn.prepareStatement(SQL);
	
				psmt.setString(1, no_title);
				psmt.setString(2, no_content);
				psmt.setInt(3, no_num);
			
				return psmt.executeUpdate();
				
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					Bbsclose();
				}
				//데이터베이스 오류발생시 -1을 반환한다 
				//게시글로 적젎치 않은 -1번호를 알려줌으로서 오류발생을 알수있게 한다 
				return -1; 
			}
		 
		 
		 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 	
	 //***삭제하는 메소드
	 	public int delete(int no_num) {
	 		
	 		BbsDAO();
		    // bbsAvailable = 0으로 바꾸는걸로 delete 함수를 구현한다 
	 		// 삭제하더라도 글에 대한정보를 남아있을수 있게 한다 
			String SQL ="DELETE FROM NOTICE WHERE no_num = ? ";
			
			try {			
				//안쪽에서 PreparedStatement를 넣어준다
				//PreparedStatement로 해서 연결객체 conn을 이용해서 SQL문장을 실행 준비단계로 만들어놓는다
				
				PreparedStatement psmt = conn.prepareStatement(SQL);
					
				psmt.setInt(1, no_num);
				
				return psmt.executeUpdate();
				
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					Bbsclose();
				}
				//데이터베이스 오류발생시 -1을 반환한다 
				//게시글로 적절치 않은 -1번호를 알려줌으로서 오류발생을 알수있게 한다 
				return -1; 
			}
	 		
	 	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 





































	
	
	
	
	
	
	
	
	
	
	

	
	
	
	


