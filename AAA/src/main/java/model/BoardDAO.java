package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	// 기본값 null, 0로 설정
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private PreparedStatement psmt2 = null;
	private ResultSet rs = null;

	public void getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String db_url = "jdbc:oracle:thin:@project-db-stu3.smhrd.com:1524:xe";
			String db_id = "Insa4_Spring_hacksim_3";
			String db_pw = "aishcool3";

			conn = DriverManager.getConnection(db_url, db_id, db_pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ojdbc6.jar 또는 경로를 체크하세요!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DB연결에 필요한 정보가 맞는지 체크하세요!");
		}
	}

	// 날짜 가져오는 함수
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	//
	public int getNext() {
		String SQL = "SELECT com_Num FROM COMMUNITY ORDER BY COM_NUM DESC";
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

	// DB에 적어주는 기능
	  public int write(UsersDTO info, String com_Title, String com_Content, String com_Category) {
	      int cnt = 0;
	      getConnection();
	      // 1 2 3 4 5 6 7 8 9 10 11
	      // COM_NUM / COM_TITLE/ COM_CONTENT/ ID/ NICKNAME/COM_DATE/
	      // COM_CATEGORY/COM_VIEWS/COM_LIKES/COM_REPORT/ROLE
	      String sql = "INSERT INTO COMMUNITY VALUES(COM_NUM_SEQ.NEXTVAL,?,?,?,?,SYSDATE,?,1,1,1,?)";
	      String sql2 = "sELECT com_NUM FROM COMMUNITY ORDER BY com_NUM DESC";
	      try {
	         psmt = conn.prepareStatement(sql);
	         // email이 private이니까 get을 사용함.
	         psmt.setString(1, com_Title);
	         psmt.setString(2, com_Content);
	         psmt.setString(3, info.getId());
	         psmt.setString(4, info.getNickname());
	         psmt.setString(5, com_Category);
	         psmt.setString(6, info.getRole());
	         // id, 닉네임, 역할은 info에서 가져옴.info는 세션에 저장된 회원정보
	         
	         psmt.executeUpdate();
	         psmt2 = conn.prepareStatement(sql2);
	         
	         rs = psmt2.executeQuery();
	         
	         if(rs.next()) {
	            cnt = rs.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return cnt;
	   }

	// 목록을 보여주는 기능
	public ArrayList<BoardDTO> getList(int pageNumber) {
		getConnection();
		// String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE,
		// COM_CATEGORY, COM_VIEWS FROM COMMUNITY WHERE COM_NUM < ? ORDER BY COM_NUM
		// DESC FETCH FIRST 3 ROWS ONLY";
		String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM (SELECT * FROM COMMUNITY WHERE COM_NUM < ? order by COM_NUM desc) WHERE ROWNUM <=10";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Num(rs.getInt(1));
				dto.setCom_Title(rs.getString(2));
				dto.setCom_Content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setCom_Date(rs.getString(5));
				dto.setCom_Category(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 다음페이지 넘어가는 기능
	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM COMMUNITY WHERE COM_NUM < ? ";

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
	public BoardDTO getBoardDTO(int com_Num) {
		getConnection();
		String SQL = "SELECT COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS, COM_CONTENT, ID FROM COMMUNITY WHERE COM_NUM=?";
		BoardDTO dto = new BoardDTO();
		try {
			psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, com_Num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setCom_Category(rs.getString(1));
				dto.setCom_Num(rs.getInt(2));
				dto.setCom_Title(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setCom_Date(rs.getString(5));
				dto.setCom_Views(rs.getInt(6));
				dto.setCom_Content(rs.getString(7));
				dto.setId(rs.getString(8));
				return dto;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(UsersDTO info, String com_Title, String com_Content, String com_Category, int com_Num) {
		getConnection();
		String SQL = "UPDATE COMMUNITY SET COM_TITLE = ? , COM_CONTENT =? WHERE ID=? AND COM_CATEGORY= ? AND COM_NUM = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, com_Title);
			pstmt.setString(2, com_Content);
			pstmt.setString(3, info.getId());
			pstmt.setString(4, com_Category);
			pstmt.setInt(5, com_Num);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	public int delete(int com_Num, String com_Category) {
		getConnection();
		String SQL = "DELETE FROM COMMUNITY WHERE COM_NUM=? AND COM_CATEGORY=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, com_Num);
			pstmt.setString(2, com_Category);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	public int getCount() {
		String SQL = "SELECT COUNT(*) FROM COMMUNITY";
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
	 * 여기부터 커뮤니티 검색(전체 글 보기)
	 * DAO입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
//	  제목+내용 으로 검색!

	public ArrayList<BoardDTO> getList_Search_Title_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND (COM_TITLE  LIKE ? OR COM_CONTENT LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			pstmt.setString(2, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//	(전체 글 보기)	 제목으로 검색!		
	public ArrayList<BoardDTO> getList_Search_Title(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND (COM_TITLE LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//(전체 글 보기)내용 으로 검색!	
	public ArrayList<BoardDTO> getList_Search_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND (COM_CONTENT  LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//(전체 글 보기) 작성자 로 검색!
	public ArrayList<BoardDTO> getList_Search_Writer(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND (NICKNAME LIKE ?) ";
		// (NICKNAME LIKE '%?%')
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		System.out.println(sentence);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

// (((((((((((((((((((1.건의사항 ))))))))))))))))))))))))))!!!!!!!		
//*****************************************************************************************************************************************	
//1.건의사항 게시판 목록!!!!!!
	public ArrayList<BoardDTO> getListRequest(int pageNumber) {
		getConnection();
		String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM (SELECT * FROM COMMUNITY WHERE COM_NUM < ? order by COM_NUM desc) WHERE ROWNUM <=10 AND COM_CATEGORY='request'";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Num(rs.getInt(1));
				dto.setCom_Title(rs.getString(2));
				dto.setCom_Content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setCom_Date(rs.getString(5));
				dto.setCom_Category(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

// (1.건의사항 ) 게시판 페이지 수 넘기기 관련 함수
	public boolean nextPageRequest(int pageNumber) {
		String SQL = "SELECT * FROM COMMUNITY WHERE COM_CATEGORY='request' AND COM_NUM < ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextRequest() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCountRequest() {
		String SQL = "SELECT COUNT(*) FROM COMMUNITY WHERE COM_CATEGORY='request'";
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

	public int getNextRequest() {
		String SQL = "SELECT com_Num FROM COMMUNITY ORDER BY COM_NUM DESC WHERE COM_CATEGORY='request'";
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

// (1.건의사항  게시판) 제목+내용으로 검색!
	public ArrayList<BoardDTO> getListRequest_Search_Title_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'request' AND (COM_TITLE  LIKE ? OR COM_CONTENT LIKE ?)";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			pstmt.setString(2, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

// (1.건의사항  게시판) 제목으로 검색!
	public ArrayList<BoardDTO> getListRequest_Search_Title(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'request' AND (COM_TITLE  LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

// (1.건의사항  게시판) 내용 으로 검색!
	public ArrayList<BoardDTO> getListRequest_Search_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'request' AND (COM_CONTENT LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

// (1.건의사항  게시판) 작성자 로 검색!
	public ArrayList<BoardDTO> getListRequest_Search_Writer(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'request' AND (NICKNAME LIKE ?) ";
		// (NICKNAME LIKE '%?%')
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		System.out.println(sentence);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

// (((((((((((((((((((2.취미 ))))))))))))))))))))))))))!!!!!!!		
//*****************************************************************************************************************************************	
//2.취미 게시판 목록!!!!!!
	public ArrayList<BoardDTO> getListHobby(int pageNumber) {
		getConnection();
		String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM (SELECT * FROM COMMUNITY WHERE COM_NUM < ? order by COM_NUM desc) WHERE ROWNUM <=10 AND COM_CATEGORY='hobby'";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Num(rs.getInt(1));
				dto.setCom_Title(rs.getString(2));
				dto.setCom_Content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setCom_Date(rs.getString(5));
				dto.setCom_Category(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// (2.취미 게시판) 게시판 페이지 수 넘기기 관련 함수
	public boolean nextPageHobby(int pageNumber) {
		String SQL = "SELECT * FROM COMMUNITY WHERE COM_CATEGORY='hobby' AND COM_NUM < ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextBaby() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCountHobby() {
		String SQL = "SELECT COUNT(*) FROM COMMUNITY WHERE COM_CATEGORY='hobby'";
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

	public int getNextHobby() {
		String SQL = "SELECT com_Num FROM COMMUNITY ORDER BY COM_NUM DESC WHERE COM_CATEGORY='hobby'";
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

	// (2.취미 게시판) 제목+내용으로 검색!
	public ArrayList<BoardDTO> getListHobby_Search_Title_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'hobby' AND (COM_TITLE  LIKE ? OR COM_CONTENT LIKE ?)";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			pstmt.setString(2, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (2.취미 게시판) 제목으로 검색!
	public ArrayList<BoardDTO> getListHobby_Search_Title(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'hobby' AND (COM_TITLE  LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (2.취미 게시판) 내용 으로 검색!
	public ArrayList<BoardDTO> getListHobby_Search_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'hobby' AND (COM_CONTENT LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (2.취미 게시판) 작성자 로 검색!
	public ArrayList<BoardDTO> getListHobby_Search_Writer(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'hobby' AND (NICKNAME LIKE ?) ";
		// (NICKNAME LIKE '%?%')
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		System.out.println(sentence);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (((((((((((((((((((3.육아 ))))))))))))))))))))))))))!!!!!!!
	// *****************************************************************************************************************************************
	// 3.육아 게시판 목록!!!!!!
	public ArrayList<BoardDTO> getListBaby(int pageNumber) {
		getConnection();
		String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM (SELECT * FROM COMMUNITY WHERE COM_NUM < ? order by COM_NUM desc) WHERE ROWNUM <=10 AND COM_CATEGORY='baby'";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Num(rs.getInt(1));
				dto.setCom_Title(rs.getString(2));
				dto.setCom_Content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setCom_Date(rs.getString(5));
				dto.setCom_Category(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// (3.육아 게시판) 게시판 페이지 수 넘기기 관련 함수
	public boolean nextPageBaby(int pageNumber) {
		String SQL = "SELECT * FROM COMMUNITY WHERE COM_CATEGORY='baby' AND COM_NUM < ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextBaby() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCountBaby() {
		String SQL = "SELECT COUNT(*) FROM COMMUNITY WHERE COM_CATEGORY='baby'";
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

	public int getNextBaby() {
		String SQL = "SELECT com_Num FROM COMMUNITY ORDER BY COM_NUM DESC WHERE COM_CATEGORY='baby'";
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

	// (3.육아 게시판) 제목+내용으로 검색!
	public ArrayList<BoardDTO> getListBaby_Search_Title_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'baby' AND (COM_TITLE  LIKE ? OR COM_CONTENT LIKE ?)";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			pstmt.setString(2, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (3.육아 게시판) 제목으로 검색!
	public ArrayList<BoardDTO> getListBaby_Search_Title(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'baby' AND (COM_TITLE  LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (3.육아 게시판) 내용 으로 검색!
	public ArrayList<BoardDTO> getListBaby_Search_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'baby' AND (COM_CONTENT LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (3.육아 게시판) 작성자 로 검색!
	public ArrayList<BoardDTO> getListBaby_Search_Writer(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'baby' AND (NICKNAME LIKE ?) ";
		// (NICKNAME LIKE '%?%')
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		System.out.println(sentence);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

// 4. 찾아주세요!!!!!!!!!!!!!!!!!!!!!!!!	
//(((((((((((((((((((찾아주세요))))))))))))))))))))))))))!!!!!!!	

//찾아주세요 게시판 목록!!!!!!
	public ArrayList<BoardDTO> getListFind(int pageNumber) {
		getConnection();
		String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM (SELECT * FROM COMMUNITY WHERE COM_NUM < ? order by COM_NUM desc) WHERE ROWNUM <=10 AND COM_CATEGORY='find'";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Num(rs.getInt(1));
				dto.setCom_Title(rs.getString(2));
				dto.setCom_Content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setCom_Date(rs.getString(5));
				dto.setCom_Category(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//(찾아주세요) 게시판 페이지 수 넘기기
	public boolean nextPageFind(int pageNumber) {
		String SQL = "SELECT * FROM COMMUNITY WHERE COM_CATEGORY='find' AND COM_NUM < ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextFind() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCountFind() {
		String SQL = "SELECT COUNT(*) FROM COMMUNITY WHERE COM_CATEGORY='find'";
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

	public int getNextFind() {
		String SQL = "SELECT com_Num FROM COMMUNITY ORDER BY COM_NUM DESC WHERE COM_CATEGORY='find'";
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

//(찾아주세요 게시판) 제목+내용으로 검색!		
	public ArrayList<BoardDTO> getListFind_Search_Title_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'find' AND (COM_TITLE  LIKE ? OR COM_CONTENT LIKE ?)";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			pstmt.setString(2, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//(찾아주세요 게시판)  제목으로 검색!		
	public ArrayList<BoardDTO> getListFind_Search_Title(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'find' AND (COM_TITLE  LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//(찾아주세요 게시판)	내용 으로 검색!	
	public ArrayList<BoardDTO> getListFind_Search_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'find' AND (COM_CONTENT LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

// (((((((((((((((((((5.동네맛집 ))))))))))))))))))))))))))!!!!!!!		
//*****************************************************************************************************************************************
//(5.동네맛집 게시판)  작성자로 검색!
	public ArrayList<BoardDTO> getListFind_Search_Writer(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'find' AND (NICKNAME LIKE ?) ";
		// (NICKNAME LIKE '%?%')
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		System.out.println(sentence);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

//(((((((((((((((((((5.동네맛집 ))))))))))))))))))))))))))!!!!!!!	

//5.동네맛집 게시판 목록!!!!!!
	public ArrayList<BoardDTO> getListFood(int pageNumber) {
		getConnection();
		String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM (SELECT * FROM COMMUNITY WHERE COM_NUM < ? order by COM_NUM desc) WHERE ROWNUM <=10 AND COM_CATEGORY='food'";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Num(rs.getInt(1));
				dto.setCom_Title(rs.getString(2));
				dto.setCom_Content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setCom_Date(rs.getString(5));
				dto.setCom_Category(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// (5.동네맛집 ) 게시판 페이지 수 넘기기 관련 함수
	public boolean nextPageFood(int pageNumber) {
		String SQL = "SELECT * FROM COMMUNITY WHERE COM_CATEGORY='food' AND COM_NUM < ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextFood() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCountFood() {
		String SQL = "SELECT COUNT(*) FROM COMMUNITY WHERE COM_CATEGORY='food'";
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

	public int getNextFood() {
		String SQL = "SELECT com_Num FROM COMMUNITY ORDER BY COM_NUM DESC WHERE COM_CATEGORY='food'";
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

	// (5.동네맛집 게시판) 제목+내용으로 검색!
	public ArrayList<BoardDTO> getListFood_Search_Title_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'food' AND (COM_TITLE  LIKE ? OR COM_CONTENT LIKE ?)";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			pstmt.setString(2, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (5.동네맛집 게시판) 제목으로 검색!
	public ArrayList<BoardDTO> getListFood_Search_Title(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'food' AND (COM_TITLE  LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (5.동네맛집 게시판) 내용 으로 검색!
	public ArrayList<BoardDTO> getListFood_Search_Content(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'find' AND (COM_CONTENT LIKE ?) ";
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}

	// (5.동네맛집 게시판) 작성자 로 검색!
	public ArrayList<BoardDTO> getListFood_Search_Writer(String sentence, int pageNumber) {
		getConnection();
		String SQL = "SELECT ROWNUM, COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY WHERE ROWNUM<= 10 AND COM_CATEGORY= 'food' AND (NICKNAME LIKE ?) ";
		// (NICKNAME LIKE '%?%')
		ArrayList<BoardDTO> list_Search = new ArrayList<BoardDTO>();
		System.out.println(sentence);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sentence + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setCom_Category(rs.getString(2));
				dto.setCom_Num(rs.getInt(3));
				dto.setCom_Title(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setCom_Date(rs.getString(6));
				dto.setCom_Views(rs.getInt(7));
				list_Search.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Search;
	}
	// 좋아요 관련
		// 좋아요 갯수 하나 늘리기
		public int Likesup(int comNum) {
			getConnection();
			int cnt= 0;
			String sql ="UPDATE COMMUNITY SET COM_LIKES= COM_LIKES+1 WHERE COM_NUM =?";
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
		// 좋아요 개수 하나 내리기
		public int LikesDown(int comNum) {
			getConnection();
			int cnt= 0;
			String sql ="UPDATE COMMUNITY SET COM_LIKES= COM_LIKES-1 WHERE COM_NUM =?";
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
		
		// 좋아요 갯수가져오기
		public int getCom_Likes(int comNum) {
			getConnection();
			int cnt= 0;
			String sql ="Select COM_LIKES FROM COMMUNITY WHERE COM_NUM =?";
			try {
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, comNum);
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
			String sql ="UPDATE COMMUNITY SET COM_REPORT= COM_REPORT+1 WHERE COM_NUM =?";
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
		public int ReportDown(int comNum) {
			getConnection();
			int cnt= 0;
			String sql ="UPDATE COMMUNITY SET COM_REPORT= COM_REPORT+1 WHERE COM_NUM =?";
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
		 // 신고수 가져오기
		   public int getCom_Report(int comNum) {
		      getConnection();
		      int cnt= 0;
		      String sql ="Select COM_REPORT FROM COMMUNITY WHERE COM_NUM =?";
		      try {
		         psmt=conn.prepareStatement(sql);
		         psmt.setInt(1, comNum);
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
		
		public int Viewup(int comNum) {
			getConnection();
			int cnt= 0;
			String sql ="UPDATE COMMUNITY SET COM_VIEWS= COM_VIEWS+1 WHERE COM_NUM =?";
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
		 //조회수, 좋아요, 신고 등에 쓰이는 아이디 매개변수 가져오기
		   public String getId(int comNum) {
		      getConnection();
		      String result ="";
		      String sql = "SELECT ID FROM COMMUNITY WHERE COM_NUM=?";
		      try {
		         psmt=conn.prepareStatement(sql);
		         psmt.setInt(1, comNum);
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
		
		
		   //파일 이미지 db업로드 용 입니다
		   public void fileupload(String selectFile, int com_num) {
		      // TODO Auto-generated method stub
		      
		      getConnection();
		      String sql = "INSERT INTO com_img VALUES(com_IMG_NUM_SEQ.NEXTVAL, ?,"+com_num+")";
		      
		      try {
		         psmt = conn.prepareStatement(sql);
		         
		         psmt.setString(1, selectFile);
		         
		         psmt.executeUpdate();
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		         close();
		      }
		   }

		   // 페이지 파일 url 받아오기용 입니다
		   public String com_img(int com_num) {
		      String url = null;
		      
		      getConnection();
		      
		      String sql = "SELECT com_img_url FROM com_IMG where com_num=?";
		      
		      try {
		         psmt = conn.prepareStatement(sql);
		         
		         psmt.setInt(1, com_num);
		         
		         rs = psmt.executeQuery();
		         
		         if(rs.next()) {
		            url = rs.getString(1);
		            return url;
		         }
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		         
		         
		      }finally {
		         close();
		         
		      }
		      return url;
		      
		   }

}
