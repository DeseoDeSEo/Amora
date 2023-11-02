package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdDAO {
   // 기본값 null, 0로 설정
   private Connection conn=null;
   private PreparedStatement psmt=null;
   private ResultSet rs= null;
   
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
   //날짜 가져오는 함수
   public String getDate() {
      String SQL = "SELECT NOW()";
      try {
         PreparedStatement pstmt = conn.prepareStatement(SQL);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            return rs.getString(1);
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
      return "";
   }
   //
   public int getNext() {
      String SQL = "SELECT ad_Num FROM AD ORDER BY AD_NUM DESC";
      try {
         PreparedStatement pstmt = conn.prepareStatement(SQL);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            return rs.getInt(1) + 1;
         }
         return 1; // 첫 번째 게시물인 경우
      } catch(Exception e) {
         e.printStackTrace();
      }
      return -1; // 데이터베이스 오류
   }
   
   public void close() {
      try {
         if(rs!=null) {
            rs.close();
         }
         if(psmt!=null) {
            psmt.close();
         }
         if(conn!=null) {
            conn.close();
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
   }
   //DB에 적어주는 기능
   public int adwrite(UsersDTO info, String ad_Title, String ad_Content, String ad_Period) {
       int cnt = 0;
       getConnection();
       
       String sql = "INSERT INTO AD VALUES(AD_NUM_SEQ.NEXTVAL,?,?,?,?,sysdate,TO_DATE(REPLACE(?, '-', ''), 'YYYYMMDD'),1,?)";
       
       try {
           psmt = conn.prepareStatement(sql);
           // email이 private이니까 get을 사용함.
           psmt.setString(1, ad_Title);
           psmt.setString(2, ad_Content);
           psmt.setString(3, info.getId());
           psmt.setString(4, info.getNickname());
           psmt.setString(5, ad_Period);
           psmt.setString(6, info.getRole());
           
           cnt = psmt.executeUpdate();
       } catch(SQLException e) {
           e.printStackTrace();
       } finally {
           close();
       }
       return cnt;
   }
   
   //목록을 보여주는 기능
   public ArrayList<AdDTO> getList(int pageNumber) {
      getConnection();
      //String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM COMMUNITY WHERE COM_NUM < ? ORDER BY COM_NUM DESC FETCH FIRST 3 ROWS ONLY";
      String SQL="SELECT AD_NUM, AD_TITLE, AD_CONTENT, NICKNAME, AD_DATE, AD_PERIOD, AD_VIEWS FROM (SELECT * FROM AD WHERE AD_NUM < ? order by AD_NUM desc) WHERE ROWNUM <=8";
      ArrayList<AdDTO> list = new ArrayList<AdDTO>();
      try {
         PreparedStatement pstmt = conn.prepareStatement(SQL);
         pstmt.setInt(1, getNext() - (pageNumber -1) * 8);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            AdDTO dto = new AdDTO();
            
            dto.setAd_Num(rs.getInt(1));
            
            // Convert AD_DATE to String
                java.sql.Date adDate = rs.getDate("AD_DATE");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                String dateString = dateFormat.format(adDate);
                dto.setAd_Date(dateString);

            dto.setAd_Title(rs.getString(2));
            dto.setAd_Content(rs.getString(3));
            dto.setNickname(rs.getString(4));

            dto.setAd_Period(rs.getString(6));
            dto.setAd_Views(rs.getInt(7)); 
            list.add(dto);
         }         
      } catch(Exception e) {
         e.printStackTrace();
      }
      return list;
   }
   
   //다음페이지 넘어가는 기능
   public boolean nextPage(int pageNumber) {
      String SQL = "SELECT * FROM AD WHERE AD_NUM < ? ";
      
      try {
         PreparedStatement pstmt = conn.prepareStatement(SQL);
         pstmt.setInt(1, getNext() - (pageNumber -1) * 8);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            return true;
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
      return false;
   }
   
      // 게시물 보기
      public AdDTO getAdDTO(int ad_Num) {
         getConnection();
         String SQL ="SELECT AD_PERIOD, AD_NUM, AD_TITLE, NICKNAME, AD_DATE, AD_VIEWS, AD_CONTENT, ID, ROLE FROM AD WHERE AD_NUM=?";
         AdDTO dto = new AdDTO();
         try {
            psmt=conn.prepareStatement(SQL);
            psmt.setInt(1, ad_Num);
            rs = psmt.executeQuery();
            
            if (rs.next()) {
               dto.setAd_Period(rs.getString(1));
               dto.setAd_Num(rs.getInt(2));
               dto.setAd_Title(rs.getString(3));
               dto.setNickname(rs.getString(4));
               dto.setAd_Date(rs.getString(5));
               dto.setAd_Views(rs.getInt(6));
               dto.setAd_Content(rs.getString(7));
               dto.setId(rs.getString(8));
               dto.setRole(rs.getString(9));
               return dto;
               
            }         
         } catch(Exception e) {
            e.printStackTrace();
         }
         return null;
      }
      
      
      public int update(UsersDTO info, String ad_Title, String ad_Content, String ad_Period, int ad_Num) {
         getConnection();
         String SQL = "UPDATE AD SET AD_TITLE = ? , AD_CONTENT =? WHERE ID=? AND AD_PERIOD= ? AND AD_NUM = ? ";
         try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, ad_Title);
            pstmt.setString(2, ad_Content);
            pstmt.setString(3, info.getId());
            pstmt.setString(4, ad_Period);
            pstmt.setInt(5, ad_Num);
            
            return pstmt.executeUpdate(); 
         } catch(Exception e) {
            e.printStackTrace();
         }
         return -1; // 데이터베이스 오류
      }
      
      
      
      public int delete(int ad_Num, String ad_Period) {
         getConnection();
         String SQL = "DELETE FROM AD WHERE AD_NUM=? AND AD_PERIOD=?";
         try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, ad_Num);
            pstmt.setString(2,ad_Period);
         
            return pstmt.executeUpdate(); 
         } catch(Exception e) {
            e.printStackTrace();
         }
         return -1; // 데이터베이스 오류
      }
      
      
      public int getCount() {
         String SQL = "SELECT COUNT(*) FROM AD";
         try {
            PreparedStatement pstmt=conn.prepareStatement(SQL);
            rs=pstmt.executeQuery();
            if(rs.next()) {
               return rs.getInt(1);
            }         
         } catch(Exception e) {
            e.printStackTrace();
         }
         return -1;
      }
      
      
      public void fileupload(String selectFile, int ad_num) {
         // TODO Auto-generated method stub
         
         getConnection();
         String sql = "INSERT INTO ad_img VALUES(ad_IMG_NUM_SEQ.NEXTVAL, ?,"+ad_num+")";
         
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
   
      
      public String sh_img() {
         String url = null;
         
         getConnection();
         
         String sql = "SELECT myfile_url FROM AD_IMG_TEST";
         
         try {
            psmt = conn.prepareStatement(sql);
            
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
      
      
      /*
  	 * 여기부터 검색(홍보 글 보기)
  	 * DAO입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  	 * !!!!!!
  	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
  	 */
//  	  제목+내용 으로 검색!

  	public ArrayList<AdDTO> getList_Search_Title_Content(String sentence, int pageNumber) {
  		getConnection();
  		String SQL = "SELECT ROWNUM, AD_NUM, AD_TITLE, NICKNAME, AD_DATE, AD_VIEWS FROM AD WHERE ROWNUM<= 8 AND (AD_TITLE  LIKE ? OR AD_CONTENT LIKE ?) ";
  		ArrayList<AdDTO> list_Search = new ArrayList<AdDTO>();
  		System.out.println(sentence);
  		try {
  			PreparedStatement pstmt = conn.prepareStatement(SQL);
  			pstmt.setString(1, "%" + sentence + "%");
  			rs = pstmt.executeQuery();
  			while (rs.next()) {
  				AdDTO dto = new AdDTO();
  				dto.setAd_Num(rs.getInt(2));
  				dto.setAd_Title(rs.getString(3));
  				dto.setNickname(rs.getString(4));
  				dto.setAd_Date(rs.getString(5));
  				dto.setAd_Views(rs.getInt(6));
  				list_Search.add(dto);
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return list_Search;
  	}

//  	(전체 글 보기)	 제목으로 검색!		
  	public ArrayList<AdDTO> getList_Search_Title(String sentence, int pageNumber) {
  		getConnection();
  		String SQL = "SELECT ROWNUM, AD_NUM, AD_TITLE, NICKNAME, AD_DATE, AD_VIEWS FROM AD WHERE ROWNUM<= 10 AND (AD_TITLE LIKE ?) ";
  		ArrayList<AdDTO> list_Search = new ArrayList<AdDTO>();
  		System.out.println(sentence);
  		try {
  			PreparedStatement pstmt = conn.prepareStatement(SQL);
  			pstmt.setString(1, "%" + sentence + "%");
  			rs = pstmt.executeQuery();
  			while (rs.next()) {
  				AdDTO dto = new AdDTO();
  				dto.setAd_Num(rs.getInt(2));
  				dto.setAd_Title(rs.getString(3));
  				dto.setNickname(rs.getString(4));
  				dto.setAd_Date(rs.getString(5));
  				dto.setAd_Views(rs.getInt(6));
  				list_Search.add(dto);
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return list_Search;
  	}

  //(전체 글 보기)내용 으로 검색!	
  	public ArrayList<AdDTO> getList_Search_Content(String sentence, int pageNumber) {
  		getConnection();
  		String SQL = "SELECT ROWNUM, AD_NUM, AD_TITLE, NICKNAME, AD_DATE, AD_VIEWS FROM AD WHERE ROWNUM<= 8 AND (AD_CONTENT  LIKE ?) ";
  		ArrayList<AdDTO> list_Search = new ArrayList<AdDTO>();
  		System.out.println(sentence);
  		try {
  			PreparedStatement pstmt = conn.prepareStatement(SQL);
  			pstmt.setString(1, "%" + sentence + "%");
  			rs = pstmt.executeQuery();
  			while (rs.next()) {
  				AdDTO dto = new AdDTO();
  				dto.setAd_Num(rs.getInt(2));
  				dto.setAd_Title(rs.getString(3));
  				dto.setNickname(rs.getString(4));
  				dto.setAd_Date(rs.getString(5));
  				dto.setAd_Views(rs.getInt(6));
  				list_Search.add(dto);
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return list_Search;
  	}

  //(전체 글 보기) 작성자 로 검색!
  	public ArrayList<AdDTO> getList_Search_Writer(String sentence, int pageNumber) {
  		getConnection();
  		String SQL = "SELECT ROWNUM, AD_NUM, AD_TITLE, NICKNAME, AD_DATE, AD_VIEWS FROM AD WHERE ROWNUM<= 8 AND (NICKNAME LIKE ?) ";
  		// (NICKNAME LIKE '%?%')
  		ArrayList<AdDTO> list_Search = new ArrayList<AdDTO>();
  		System.out.println(sentence);
  		try {
  			PreparedStatement pstmt = conn.prepareStatement(SQL);
  			pstmt.setString(1, "%" + sentence + "%");
  			rs = pstmt.executeQuery();
  			while (rs.next()) {
  				AdDTO dto = new AdDTO();
  				dto.setAd_Num(rs.getInt(2));
  				dto.setAd_Title(rs.getString(3));
  				dto.setNickname(rs.getString(4));
  				dto.setAd_Date(rs.getString(5));
  				dto.setAd_Views(rs.getInt(6));
  				list_Search.add(dto);
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return list_Search;
  			
  	}
      
      
      

}