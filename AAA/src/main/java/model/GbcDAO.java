package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GbcDAO {

	private Connection conn=null;
	private PreparedStatement psmt=null;
	private ResultSet rs= null;
	int cnt=0;
	String un="";
	
	//댓글 쓰기 
	public int writeDadgul(ComcDTO dto) {
		getConnection();
		String sql = "INSERT INTO COMMUNITY_C VALUES(comc_num_seq.nextval(),?,?,?,SYSDATE,?,0,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getComcContent()); //댓글 내용
			psmt.setString(2, dto.getId()); // 댓글 작성자 아이디
			psmt.setString(3, dto.getNickname()); // 댓글 작성자 닉네임
			psmt.setInt(4, dto.getComNum()); // 댓글 번호
			psmt.setString(5,dto.getRole());
			psmt.setInt(6,dto.getDadat());
			cnt =psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	// 댓글 보기
	public ArrayList<GbcDTO> vogiDadgul(GbcDTO dto){
		ArrayList<GbcDTO> list = new ArrayList<>();
		String sql ="SELECT NICKNAME, GBC_CONTENT,DADAT FROM COMMUNITY_COMMENT WHERE COM_NUM =?";
		try {
			// 나 여기까지함 
			psmt= conn.prepareStatement(sql);
			psmt.setInt(1, dto.getGbcNum());
			rs=psmt.executeQuery();
			while(rs.next()) {
				String nickname =rs.getString(1);
				int comcContent =rs.getInt(2);
				int dadat =rs.getInt(3);
				dto= new GbcDTO(nickname, comcContent,dadat);
				list.add(dto);
		}
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	// 댓글 수정
		public int comcModify(ComcDTO dto) {
			String sql = "UPDATE COMMUNITY_C SET COM_C CONTENT=? WHERE NICKNAME = ? AND COM_NUM =? AND DADAT=? ";
			try {
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, dto.getComcContent());
				psmt.setString(2, dto.getNickname());
				psmt.setInt(3, dto.getComNum());
				psmt.setInt(4, dto.getDadat());
				cnt=psmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1; //sql 오류 발생
			}
			
			return 1; // sql 오류 없음 완료
		}
	// 댓글 삭제 기능
		public int comcDelete(ComcDTO dto) {
			String sql = "UPDATE COMMUNITY_C SET COM_C CONTENT= '삭제된 게시글 입니다.' WHERE NICKNAME = ? AND COM_NUM =? AND DADAT=? ";
			try {
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, dto.getNickname());
				psmt.setInt(2, dto.getComNum());
				psmt.setInt(3, dto.getDadat());
				cnt=psmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1; //sql 오류 발생
			}
			
			return 1; // sql 오류 없음 완료
		}
		
	// 댓글 신고 기능
		public int comcReport (ComcDTO dto) {
			String sql = "UPDATE COMMUNITY_C SET REPORT = REPORT + 1 WHERE NICKNAME = ? AND COM_NUM =? AND DADAT=? ";
			try {
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, dto.getNickname());
				psmt.setInt(2, dto.getComNum());
				psmt.setInt(3, dto.getDadat());
				cnt=psmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1; //sql 오류 발생
			} finally {
				close();
			}
			
			return cnt; // sql 오류 없음 완료
		}
		
		
	
	// 대댓글 처리를 위한 공백개수 정하기
	public String makeGongback(int dadat) {
	    String gongback = "   "; // 3칸 짜리 공백
	    if (dadat > 0) {
	        gongback = gongback.repeat(dadat - 1);
	    } else {
	        gongback = "";
	    }
	    return gongback;
	}
	
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
}
