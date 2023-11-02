package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShDAO {

	// 기본값 null, 0로 설정
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private PreparedStatement psmt2 = null;
	private ResultSet rs = null;
	int cnt = 0;
	String un = "";

	// 데이터베이스 연결메소드
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

	// 데이터베이스 종료메소드
	public void close() {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 중고 글쓰기
	public int secondhandwrite(UsersDTO info, String sh_title, String sh_content, int sh_price) {
		// TODO Auto-generated method stub
		int sh_num = 0;
		getConnection();
		String sql = "INSERT INTO SECONDHAND VALUES(SH_NUM_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, 0, 0, ?, 'NF',0)";
		String sql2 = "SELECT * FROM SECONDHAND order by sh_num desc";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sh_title);
			psmt.setString(2, sh_content);
			psmt.setString(3, info.getId());
			psmt.setString(4, info.getNickname());
			psmt.setInt(5, sh_price);
			psmt.setString(6, info.getRole());
			psmt.executeUpdate();

			psmt2 = conn.prepareStatement(sql2);

			rs = psmt2.executeQuery();
			if (rs.next()) {
				sh_num = rs.getInt(1);
				return sh_num;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return sh_num;
	}
	
	
	

	public void fileupload(String fileName, int sh_num) {
		// TODO Auto-generated method stub
		getConnection();
		String sql = "INSERT INTO sh_img VALUES(SH_IMG_NUM_SEQ.NEXTVAL, ?," + sh_num + ")";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, fileName);

			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

	}
//  이미지 보여주기용 문자 저장
	public String sh_img(int sh_Num) {
		String url = null;

		getConnection();

		String sql = "select sh_img_url from sh_img where sh_num=?";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, sh_Num);
			
			rs = psmt.executeQuery();

			if (rs.next()) {
				url = rs.getString(1);
				return url;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			close();

		}
		return url;

	}

// 중고거래 페이지 관련 함수들
	public int getNext() {
		String SQL = "SELECT SH_Num FROM SECONDHAND WHERE SECTION='NF' ORDER BY SH_NUM DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // 첫 번째 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	
	// 중고거래 페이징
	public ArrayList<ShDTO> getList(int pageNumber) {
		getConnection();
		String SQL = "SELECT SH_NUM, SH_TITLE, SH_CONTENT, NICKNAME, SH_DATE, SECTION, SH_VIEWS, SH_PRICE FROM (SELECT * FROM SECONDHAND WHERE SH_NUM < ? order by SH_NUM desc) WHERE ROWNUM <=8 AND SECTION='NF'";
		ArrayList<ShDTO> list = new ArrayList<ShDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ShDTO dto = new ShDTO();
				dto.setSh_Num(rs.getInt(1));
				dto.setSh_title(rs.getString(2));
				dto.setSh_content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setSh_date(rs.getString(5));
				dto.setSection(rs.getString(6));
				dto.setSh_views(rs.getInt(7));
				dto.setSh_price(rs.getInt(8));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

// 다음페이지 넘어가는 기능
	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM SECONDHAND WHERE SH_NUM < ? AND SECTION='NF' ";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 게시물 보기
	public ShDTO getShDTO(int sh_Num) {
		getConnection();
		String SQL = "SELECT SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS, SH_CONTENT, ID, SH_PRICE FROM SECONDHAND WHERE SH_NUM=? AND SECTION='NF'";
		ShDTO dto = new ShDTO();
		try {
			psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, sh_Num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setSection(rs.getString(1));
				dto.setSh_Num(rs.getInt(2));
				dto.setSh_title(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setSh_date(rs.getString(5));
				dto.setSh_views(rs.getInt(6));
				dto.setSh_content(rs.getString(7));
				dto.setId(rs.getString(8));
				dto.setSh_price(rs.getInt(9));
				return dto;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
// 업데이트 기능 중고 // 여기도 그냥 SH_NUM 만 알면 됨 info 값을 조회해서 자신의 아이디와 같으면 버튼이 잇고 아니면 없게 하는게 더 좋아보임
	public int update(UsersDTO info, String sh_Title, String sh_Content, String section, int sh_Num) {
		getConnection();
		String SQL = "UPDATE SECONDHAND SET SH_TITLE = ? , SH_CONTENT =? WHERE ID=? AND SECTION= ? AND SH_NUM = ? AND SECTION='NF'"; // 여기도 SH_TITLE을 고칠일은 쉽지 않을 거 같다는 판단 ? 종화형에게 물어보자
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sh_Title);
			pstmt.setString(2, sh_Content);
			pstmt.setString(3, info.getId());
			pstmt.setString(4, section);
			pstmt.setInt(5, sh_Num);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

 //글 삭제
	public int delete(int sh_Num, String section) {
		getConnection();
		String SQL = "DELETE FROM SECONDHAND WHERE SH_NUM=? AND SECTION=? AND SECTION='NF'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, sh_Num);
			pstmt.setString(2, section);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	public int getCount() {
		String SQL = "SELECT COUNT(*) FROM SECONDHAND WHERE SECTION='NF'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/*
	 * 여기부터 검색(중고거래 글 보기)
	 * DAO입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
//	  제목+내용 으로 검색!

	public ArrayList<ShDTO> getList_Search_Title_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE ROWNUM<= 10 AND SECTION='NF' AND (SH_TITLE  LIKE ? OR SH_CONTENT LIKE ?) ";
		ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			pstmt.setString(2, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ShDTO dto = new ShDTO();
				dto.setSection(rs.getString(2));
				dto.setSh_Num(rs.getInt(3));
				dto.setSh_title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setSh_date(rs.getString(6));
				dto.setSh_views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//	(전체 글 보기)	 제목으로 검색!		
	public ArrayList<ShDTO> getList_Search_Title(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE SECTION='NF' AND ROWNUM<= 10 AND (SH_TITLE LIKE ?) ";
		ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ShDTO dto = new ShDTO();
				dto.setSection(rs.getString(2));
				dto.setSh_Num(rs.getInt(3));
				dto.setSh_title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setSh_date(rs.getString(6));
				dto.setSh_views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//(전체 글 보기)내용 으로 검색!	
	public ArrayList<ShDTO> getList_Search_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE ROWNUM<= 10 AND (SH_CONTENT  LIKE ?)AND SECTION='NF' ";
		ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ShDTO dto = new ShDTO();
				dto.setSection(rs.getString(2));
				dto.setSh_Num(rs.getInt(3));
				dto.setSh_title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setSh_date(rs.getString(6));
				dto.setSh_views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//(중고거래 보기) 작성자 로 검색!
	public ArrayList<ShDTO> getList_Search_Writer(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE ROWNUM<= 10 AND (NICKNAME LIKE ?) AND SECTION='NF' ";
		// (NICKNAME LIKE '%?%')
		ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
		System.out.println(sentence);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ShDTO dto = new ShDTO();
				dto.setSection(rs.getString(2));
				dto.setSh_Num(rs.getInt(3));
				dto.setSh_title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setSh_date(rs.getString(6));
				dto.setSh_views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_Search;
			
	}
	
//*******************************************************************************************************************************************************
//	*********여기부터 나눔 *********************************************************************************************************************************************

	// 나눔 글쓰기
		public int shareWrite(UsersDTO info, String sh_title, String sh_content) {
			// TODO Auto-generated method stub
			int sh_num = 0;
			getConnection();
			String sql = "INSERT INTO SECONDHAND VALUES(SH_NUM_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, 0, 0, 0, ?, 'F', 0)";
			String sql2 = "SELECT * FROM SECONDHAND order by sh_num desc";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, sh_title);
				psmt.setString(2, sh_content);
				psmt.setString(3, info.getId());
				psmt.setString(4, info.getNickname());
				psmt.setString(5, info.getRole());
				psmt.executeUpdate();

				psmt2 = conn.prepareStatement(sql2);
				rs = psmt2.executeQuery();
				if (rs.next()) {
					sh_num = rs.getInt(1);
					return sh_num;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			return sh_num;
		}

	
	

	// 나눔 페이지 관련 함수들
		public int getNextShare() {
			String SQL = "SELECT SH_Num FROM SECONDHAND WHERE SECTION='F' ORDER BY SH_NUM DESC";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1) + 1;
				}
				return 1; // 첫 번째 게시물인 경우
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return -1; // 데이터베이스 오류
		}

		//나눔 페이지 보기
		public ArrayList<ShDTO> getListShare(int pageNumber) {
			getConnection();
			String SQL = "SELECT SH_NUM, SH_TITLE, SH_CONTENT, NICKNAME, SH_DATE, SECTION, SH_VIEWS FROM (SELECT * FROM SECONDHAND WHERE SH_NUM < ? order by SH_NUM desc) WHERE ROWNUM <=8 AND SECTION='F'";
			ArrayList<ShDTO> list = new ArrayList<ShDTO>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ShDTO dto = new ShDTO();
					dto.setSh_Num(rs.getInt(1));
					dto.setSh_title(rs.getString(2));
					dto.setSh_content(rs.getString(3));
					dto.setNickname(rs.getString(4));
					dto.setSh_date(rs.getString(5));
					dto.setSection(rs.getString(6));
					dto.setSh_views(rs.getInt(7));
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

	// 다음페이지 넘어가는 기능 여부 확인 
		public boolean nextPageShare(int pageNumber) {
			String SQL = "SELECT * FROM SECONDHAND WHERE SH_NUM < ? AND SECTION='F' ";

			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, getNextShare() - (pageNumber - 1) * 10);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		// 나눔 게시물 보기
		public ShDTO getShDTOShare(int sh_Num) {
			getConnection();
			String SQL = "SELECT SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS, SH_CONTENT, ID FROM SECONDHAND WHERE SH_NUM=? AND SECTION='F'";
			ShDTO dto = new ShDTO();
			try {
				psmt = conn.prepareStatement(SQL);
				psmt.setInt(1, sh_Num);
				rs = psmt.executeQuery();

				if (rs.next()) {
					dto.setSection(rs.getString(1));
					dto.setSh_Num(rs.getInt(2));
					dto.setSh_title(rs.getString(3));
					dto.setNickname(rs.getString(4));
					dto.setSh_date(rs.getString(5));
					dto.setSh_views(rs.getInt(6));
					dto.setSh_content(rs.getString(7));
					dto.setId(rs.getString(8));
					return dto;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//나눔 업데이트 실제로 필요한거는 sh_num만 알면됨
		public int updateShare(UsersDTO info, String sh_Title, String sh_Content, String section, int sh_Num) {
			getConnection();
			String SQL = "UPDATE SECONDHAND SET SH_TITLE = ? , SH_CONTENT =? WHERE ID=? AND SECTION= ? AND SH_NUM = ? AND SECTION='F' ";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, sh_Title);
				pstmt.setString(2, sh_Content);
				pstmt.setString(3, info.getId());
				pstmt.setString(4, section);
				pstmt.setInt(5, sh_Num);

				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1; // 데이터베이스 오류
		}

		public int deleteShare(int sh_Num, String section) {
			getConnection();
			String SQL = "DELETE FROM SECONDHAND WHERE SH_NUM=? AND SECTION=? AND SECTION='F'";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, sh_Num);
				pstmt.setString(2, section);

				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1; // 데이터베이스 오류
		}

		public int getCountShare() {
			String SQL = "SELECT COUNT(*) FROM SECONDHAND WHERE SECTION='F'";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}

		/*
		 * 여기부터 검색(나눔 글 보기)
		 * DAO입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * !!!!!!
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
		 */
//		  제목+내용 으로 검색!

		public ArrayList<ShDTO> getListShare_Search_Title_Content(String sentence, int pageNumber) {
			getConnection();
			String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE ROWNUM<= 10 AND SECTION='F' AND (SH_TITLE  LIKE ? OR SH_CONTENT LIKE ?) ";
			ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + sentence + "%");
				pstmt.setString(2, "%" + sentence + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ShDTO dto = new ShDTO();
					dto.setSection(rs.getString(2));
					dto.setSh_Num(rs.getInt(3));
					dto.setSh_title(rs.getString(4));
					dto.setNickname(rs.getString(5));
					dto.setSh_date(rs.getString(6));
					dto.setSh_views(rs.getInt(7));
					list_Search.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list_Search;
		}

//		(전체 글 보기)	 제목으로 검색!		
		public ArrayList<ShDTO> getListShare_Search_Title(String sentence, int pageNumber) {
			getConnection();
			String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE SECTION='F' AND ROWNUM<= 10 AND (SH_TITLE LIKE ?) ";
			ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + sentence + "%");

				rs = pstmt.executeQuery();
				while (rs.next()) {
					ShDTO dto = new ShDTO();
					dto.setSection(rs.getString(2));
					dto.setSh_Num(rs.getInt(3));
					dto.setSh_title(rs.getString(4));
					dto.setNickname(rs.getString(5));
					dto.setSh_date(rs.getString(6));
					dto.setSh_views(rs.getInt(7));
					list_Search.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list_Search;
		}

	//(전체 글 보기)내용 으로 검색!	
		public ArrayList<ShDTO> getListShare_Search_Content(String sentence, int pageNumber) {
			getConnection();
			String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE ROWNUM<= 10 AND (SH_CONTENT  LIKE ?)AND SECTION='F' ";
			ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + sentence + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ShDTO dto = new ShDTO();
					dto.setSection(rs.getString(2));
					dto.setSh_Num(rs.getInt(3));
					dto.setSh_title(rs.getString(4));
					dto.setNickname(rs.getString(5));
					dto.setSh_date(rs.getString(6));
					dto.setSh_views(rs.getInt(7));
					list_Search.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list_Search;
		}

	//(전체 글 보기) 작성자 로 검색!
		public ArrayList<ShDTO> getListShare_Search_Writer(String sentence, int pageNumber) {
			getConnection();
			String SQL = "SELECT ROWNUM, SECTION, SH_NUM, SH_TITLE, NICKNAME, SH_DATE, SH_VIEWS FROM SECONDHAND WHERE ROWNUM<= 10 AND (NICKNAME LIKE ?) AND SECTION='F' ";
			// (NICKNAME LIKE '%?%')
			ArrayList<ShDTO> list_Search = new ArrayList<ShDTO>();
			System.out.println(sentence);
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + sentence + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ShDTO dto = new ShDTO();
					dto.setSection(rs.getString(2));
					dto.setSh_Num(rs.getInt(3));
					dto.setSh_title(rs.getString(4));
					dto.setNickname(rs.getString(5));
					dto.setSh_date(rs.getString(6));
					dto.setSh_views(rs.getInt(7));
					list_Search.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list_Search;
				
		}
		// 좋아요 관련
				// 좋아요 갯수 하나 늘리기
				public int Likesup(int shNum) {
					getConnection();
					int cnt= 0;
					String sql ="UPDATE SECONDHAND SET SH_LIKES= SH_LIKES+1 WHERE SH_NUM =?";
					try {
						psmt=conn.prepareStatement(sql);
						psmt.setInt(1, shNum);
						cnt=psmt.executeUpdate();
					
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close();
					}
					return cnt;
				}
				public int LikesDown(int shNum) {
					getConnection();
					int cnt= 0;
					String sql ="UPDATE SECONDHAND SET SH_LIKES= SH_LIKES-1 WHERE SH_NUM =?";
					try {
						psmt=conn.prepareStatement(sql);
						psmt.setInt(1, shNum);
						cnt=psmt.executeUpdate();
					
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close();
					}
					return cnt;
				}
				
				// 좋아요 갯수가져오기
				public int getSh_Likes(int shNum) {
					getConnection();
					int cnt= 0;
					String sql ="Select SH_LIKES FROM SECONDHAND WHERE SH_NUM =?";
					try {
						psmt=conn.prepareStatement(sql);
						psmt.setInt(1, shNum);
						rs=psmt.executeQuery();
						if(rs.next()) {
							cnt =rs.getInt(1);
					}
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close();
					}
					
					
					// TODO Auto-generated method stub
					return cnt;
				
				}
		
		
		
		
		
		
		//신고관련
				// 신고 하나 올리기
				public int ReportUp(int comNum) {
					getConnection();
					int cnt= 0;
					String sql ="UPDATE SECONDHAND SET SH_REPORT= SH_REPORT+1 WHERE SH_NUM =?";
					try {
						psmt=conn.prepareStatement(sql);
						psmt.setInt(1, comNum);
						cnt=psmt.executeUpdate();
					
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close();
					}
					return cnt;
				}
		
		
		// 신고 내리기
				public int ReportDown(int shNum) {
					getConnection();
					int cnt= 0;
					String sql ="UPDATE SECONDHAND SET SH_REPORT= SH_REPORT+1 WHERE SH_NUM =?";
					try {
						psmt=conn.prepareStatement(sql);
						psmt.setInt(1, shNum);
						cnt=psmt.executeUpdate();
					
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close();
					}
					return cnt;
				}
		
		
		
		 // 신고수 가져오기
		   public int getSh_Report(int shNum) {
		      getConnection();
		      int cnt= 0;
		      String sql ="Select SH_REPORT FROM SECONDHAND WHERE SH_NUM =?";
		      try {
		         psmt=conn.prepareStatement(sql);
		         psmt.setInt(1, shNum);
		         rs=psmt.executeQuery();
		         if(rs.next()) {
		            cnt =rs.getInt(1);
		      }
		      }catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		         close();
		      }
		      return cnt;
		   
		   }
		
		
		
		
		
	//조회수관련
		
		public int Viewup(int shNum) {
			getConnection();
			int cnt= 0;
			String sql ="UPDATE SECONDHAND SET SH_VIEWS= SH_VIEWS+1 WHERE SH_NUM =?";
			try {
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, shNum);
				cnt=psmt.executeUpdate();
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return cnt;
		}
		
		//조회수, 좋아요, 신고 등에 쓰이는 아이디 매개변수 가져오기
		   public String getId(int shNum) {
		      getConnection();
		      String result ="";
		      String sql = "SELECT ID FROM SECONDHAND WHERE SH_NUM=?";
		      try {
		         psmt=conn.prepareStatement(sql);
		         psmt.setInt(1, shNum);
		         rs=psmt.executeQuery();
		         if(rs.next()) {
		            result =rs.getString(1);
		         }
		      
		      }catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		         close();
		      }
		      
		      // TODO Auto-generated method stub
		      return result;
		   }

		   
		   
}
