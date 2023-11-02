package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ContactDAO {
   // 기본값 null, 0로 설정
   private Connection conn=null;
   private PreparedStatement psmt=null;
   private ResultSet rs= null;
   private PreparedStatement psmt2 =null;
   
   
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
      String SQL = "SELECT gb_Num FROM GROUPBUY ORDER BY GB_NUM DESC";
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
   public int adwrite(UsersDTO info, String gb_Title, String gb_Content, String gb_Period) {
	    int cnt = 0;
	    getConnection();
	    
	    String sql = "INSERT INTO GROUPBUY VALUES(GB_NUM_SEQ.NEXTVAL,?,?,?,?,sysdate,0,TO_DATE(REPLACE(?, '-', ''), 'YYYYMMDD'),0,?)";
	    String sql2 = "SELECT GB_NUM FROM GROUPBUY ORDER BY GB_NUM DESC";
	    
	    try {
	        psmt = conn.prepareStatement(sql);
	        // email이 private이니까 get을 사용함.
	        psmt.setString(1, gb_Title);
	        psmt.setString(2, gb_Content);
	        psmt.setString(3, info.getId());
	        psmt.setString(4, info.getNickname());
	        psmt.setString(5, gb_Period);
	        psmt.setString(6, info.getRole());
	        
	        psmt.executeUpdate();
	        
	        psmt2 = conn.prepareStatement(sql2);
	        
	        rs = psmt2.executeQuery();
	        
	        if(rs.next()) {
	        	cnt = rs.getInt(1);
	        	return cnt;
	        }else {
	        	System.out.println(cnt);
	        }
	        
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    return cnt;
	}
   
   
   

   
   
   //목록을 보여주는 기능

   public ArrayList<ContactDTO> getList(int pageNumber) {
      getConnection();
      //String SQL = "SELECT COM_NUM, COM_TITLE, COM_CONTENT, NICKNAME, COM_DATE, COM_CATEGORY, COM_VIEWS FROM COMMUNITY WHERE COM_NUM < ? ORDER BY COM_NUM DESC FETCH FIRST 3 ROWS ONLY";
      String SQL="SELECT GB_NUM, GB_TITLE, GB_CONTENT, NICKNAME, GB_DATE, GB_PERIOD, GB_VIEWS FROM (SELECT * FROM GROUPBUY WHERE GB_NUM < ? order by GB_NUM desc) WHERE ROWNUM <=8";
      ArrayList<ContactDTO> list = new ArrayList<ContactDTO>();
      try {
         PreparedStatement pstmt = conn.prepareStatement(SQL);
         pstmt.setInt(1, getNext() - (pageNumber -1) * 8);
         rs = pstmt.executeQuery();
         
         while (rs.next()) {
        	 ContactDTO dto = new ContactDTO();
				dto.setGb_Num(rs.getInt(1));
				dto.setGb_Title(rs.getString(2));
				dto.setGb_Content(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setGb_Date(rs.getString(5));
				dto.setGb_Period(rs.getString(6));
				dto.setGb_Views(rs.getInt(7));
				
				list.add(dto);
         
//         while (rs.next()) {
//            AdDTO dto = new AdDTO();
//            
//            dto.setAd_Num(rs.getInt(1));
//            
//            // Convert AD_DATE to String
//                java.sql.Date adDate = rs.getDate("AD_DATE");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//                String dateString = dateFormat.format(adDate);
//                dto.setAd_Date(dateString);
//
//            dto.setAd_Title(rs.getString(2));
//            dto.setAd_Content(rs.getString(3));
//            dto.setNickname(rs.getString(4));
//
//            dto.setAd_Period(rs.getString(6));
//            dto.setAd_Views(rs.getInt(7)); 
//            list.add(dto);
         }         
      } catch(Exception e) {
         e.printStackTrace();
      }
      return list;
   }
   
   
   
   
   
   //다음페이지 넘어가는 기능
   public boolean nextPage(int pageNumber) {
      String SQL = "SELECT * FROM GROUPBUY WHERE GB_NUM < ? ";
      
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
      public ContactDTO getContactDTO(int gb_Num) {
         getConnection();
         String SQL ="SELECT GB_PERIOD, GB_NUM, GB_TITLE, NICKNAME, GB_DATE, GB_VIEWS, GB_CONTENT, ID, ROLE FROM GROUPBUY WHERE GB_NUM=?";
         ContactDTO dto = new ContactDTO();
         try {
            psmt=conn.prepareStatement(SQL);
            psmt.setInt(1, gb_Num);
            rs = psmt.executeQuery();
            
            if (rs.next()) {
               dto.setGb_Period(rs.getString(1));
               dto.setGb_Num(rs.getInt(2));
               dto.setGb_Title(rs.getString(3));
               dto.setNickname(rs.getString(4));
               dto.setGb_Date(rs.getString(5));
               dto.setGb_Views(rs.getInt(6));
               dto.setGb_Content(rs.getString(7));
               dto.setId(rs.getString(8));
               dto.setRole(rs.getString(9));
               return dto;
               
            }         
         } catch(Exception e) {
            e.printStackTrace();
         }
         return null;
      }
      
      
      public int update(UsersDTO info, String gb_Title, String gb_Content, int gb_Num) {
         getConnection();
         String SQL = "UPDATE GROUPBUY SET GB_TITLE = ? , GB_CONTENT =? WHERE ID=? AND GB_NUM = ? ";
         try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, gb_Title);
            pstmt.setString(2, gb_Content);
            pstmt.setString(3, info.getId());
           // pstmt.setString(4, gb_Date);
            pstmt.setInt(4, gb_Num);
            
            return pstmt.executeUpdate(); 
         } catch(Exception e) {
            e.printStackTrace();
         }
         return -1; // 데이터베이스 오류
      }
      
      
      
      public int delete(int gb_Num, String gb_Title) {
         getConnection();
         String SQL = "DELETE FROM GROUPBUY WHERE GB_NUM=? AND GB_TITLE=?";
         try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, gb_Num);
            pstmt.setString(2,gb_Title);
         
            return pstmt.executeUpdate(); 
         } catch(Exception e) {
            e.printStackTrace();
         }
         return -1; // 데이터베이스 오류
      }
      
      
      public int getCount() {
         String SQL = "SELECT COUNT(*) FROM GROUPBUY";
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
      
      
   // ad 파일 이미지 db업로드 용입니다
   		public void fileupload(String selectFile, int gb_num) {
   			// TODO Auto-generated method stub
   			
   			getConnection();
   			String sql = "INSERT INTO gb_img VALUES(GB_IMG_NUM_SEQ.NEXTVAL, ?,"+gb_num+")";
   			
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
   	
   		// promotion페이지 파일 url 받아오기용 입니다
   		public String gb_img(int gb_num) {
   			String url = null;
   			
   			getConnection();
   			
   			String sql = "SELECT gb_img_url FROM GB_IMG where gb_num=?";
   			
   			try {
   				psmt = conn.prepareStatement(sql);
   				
   				psmt.setInt(1, gb_num);
   				
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
//       제목+내용 으로 검색!

     public ArrayList<ContactDTO> getList_Search_Title_Content(String sentence, int pageNumber) {
        getConnection();
        String SQL = "SELECT ROWNUM, GB_NUM, GB_TITLE, NICKNAME, GB_DATE, GB_VIEWS FROM GROUPBUY WHERE ROWNUM<= 8 AND (GB_TITLE  LIKE ? OR GB_CONTENT LIKE ?) ";
        ArrayList<ContactDTO> list_Search = new ArrayList<ContactDTO>();
        System.out.println(sentence);
        try {
           PreparedStatement pstmt = conn.prepareStatement(SQL);
           pstmt.setString(1, "%" + sentence + "%");
           rs = pstmt.executeQuery();
           while (rs.next()) {
        	  ContactDTO dto = new ContactDTO();
              dto.setGb_Num(rs.getInt(2));
              dto.setGb_Title(rs.getString(3));
              dto.setNickname(rs.getString(4));
              dto.setGb_Date(rs.getString(5));
              dto.setGb_Views(rs.getInt(6));
              list_Search.add(dto);
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return list_Search;
     }

//     (전체 글 보기)    제목으로 검색!      
     public ArrayList<ContactDTO> getList_Search_Title(String sentence, int pageNumber) {
        getConnection();
        String SQL = "SELECT ROWNUM, GB_NUM, GB_TITLE, NICKNAME, GB_DATE, GB_VIEWS FROM GROUPBUY WHERE ROWNUM<= 10 AND (GB_TITLE LIKE ?) ";
        ArrayList<ContactDTO> list_Search = new ArrayList<ContactDTO>();
        System.out.println(sentence);
        try {
           PreparedStatement pstmt = conn.prepareStatement(SQL);
           pstmt.setString(1, "%" + sentence + "%");
           rs = pstmt.executeQuery();
           while (rs.next()) {
        	   ContactDTO dto = new ContactDTO();
              dto.setGb_Num(rs.getInt(2));
              dto.setGb_Title(rs.getString(3));
              dto.setNickname(rs.getString(4));
              dto.setGb_Date(rs.getString(5));
              dto.setGb_Views(rs.getInt(6));
              list_Search.add(dto);
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return list_Search;
     }

  //(전체 글 보기)내용 으로 검색!   
     public ArrayList<ContactDTO> getList_Search_Content(String sentence, int pageNumber) {
        getConnection();
        String SQL = "SELECT ROWNUM, GB_NUM, GB_TITLE, NICKNAME, GB_DATE, GB_VIEWS FROM GROUPBUY WHERE ROWNUM<= 8 AND (GB_CONTENT  LIKE ?) ";
        ArrayList<ContactDTO> list_Search = new ArrayList<ContactDTO>();
        System.out.println(sentence);
        try {
           PreparedStatement pstmt = conn.prepareStatement(SQL);
           pstmt.setString(1, "%" + sentence + "%");
           rs = pstmt.executeQuery();
           while (rs.next()) {
        	  ContactDTO dto = new ContactDTO();
              dto.setGb_Num(rs.getInt(2));
              dto.setGb_Title(rs.getString(3));
              dto.setNickname(rs.getString(4));
              dto.setGb_Date(rs.getString(5));
              dto.setGb_Views(rs.getInt(6));
              list_Search.add(dto);
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return list_Search;
     }

  //(전체 글 보기) 작성자 로 검색!
     public ArrayList<ContactDTO> getList_Search_Writer(String sentence, int pageNumber) {
        getConnection();
        String SQL = "SELECT ROWNUM, GB_NUM, GB_TITLE, NICKNAME, GB_DATE, GB_VIEWS FROM GROUPBUY WHERE ROWNUM<= 8 AND (NICKNAME LIKE ?) ";
        // (NICKNAME LIKE '%?%')
        ArrayList<ContactDTO> list_Search = new ArrayList<ContactDTO>();
        System.out.println(sentence);
        try {
           PreparedStatement pstmt = conn.prepareStatement(SQL);
           pstmt.setString(1, "%" + sentence + "%");
           rs = pstmt.executeQuery();
           while (rs.next()) {
        	  ContactDTO dto = new ContactDTO();
              dto.setGb_Num(rs.getInt(2));
              dto.setGb_Title(rs.getString(3));
              dto.setNickname(rs.getString(4));
              dto.setGb_Date(rs.getString(5));
              dto.setGb_Views(rs.getInt(6));
              list_Search.add(dto);
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return list_Search;
           
     }
     
     
     
      
  
      

}