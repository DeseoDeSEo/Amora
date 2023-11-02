package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShcDAO {

   private Connection conn=null;
   private PreparedStatement psmt=null;
   private ResultSet rs= null;
   int cnt=0;
   String un="";
   
   //댓글 쓰기 
   public int writeReply(ShcDTO dto) {
      getConnection();
      String sql = "INSERT INTO SECONDHAND_C VALUES(sh_num_seq.nextval,?,?,?,SYSDATE,?,0,?,0,NULL)";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, dto.getShcContent()); //댓글 내용
         psmt.setString(2, dto.getId()); // 댓글 작성자 아이디
         psmt.setString(3, dto.getNickname()); // 댓글 작성자 닉네임
         psmt.setInt(4, dto.getShNum()); // 게시글 번호
         psmt.setString(5,dto.getRole()); // 누가썻는가
         cnt =psmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close();
      }
      return cnt;
   }
   // 대댓글 쓰기
   public int writeRereply(ShcDTO dto) {
      getConnection();
      String sql = "INSERT INTO SECONDHAND_C VALUES(comc_num_seq.nextval,?,?,?,SYSDATE,?,0,?,1,?)";
      try {
         psmt= conn.prepareStatement(sql);
         psmt.setString(1, dto.getShcContent()); //댓글 내용
         psmt.setString(2, dto.getId()); // 댓글 작성자 아이디
         psmt.setString(3, dto.getNickname()); // 댓글 작성자 닉네임
         psmt.setInt(4, dto.getShNum()); // 게시글 번호
         psmt.setString(5,dto.getRole()); // 누가썻는가
         psmt.setInt(6, dto.getShcNum()); //부모 댓글
         cnt=psmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close();
      }
      
      return cnt;
   }
   // 커뮤니티 댓글 보여주기
   public ArrayList<ShcDTO> vogiReply(int shNum){
      getConnection();
      ArrayList<ShcDTO> list = new ArrayList<>();
      String sql ="SELECT SHC_NUM,SHC_CONTENT, NICKNAME, SHC_DATE,DADAT FROM COMMUNITY_C WHERE COM_NUM = ? " + // com_num 조건 추가
                  "START WITH PSHC_NUM IS NULL CONNECT BY PRIOR SHC_NUM = PSHC_NUM ORDER SIBLINGS BY SHC_DATE";
      try {
         psmt= conn.prepareStatement(sql);
         psmt.setInt(1, shNum);
         rs=psmt.executeQuery();
         while(rs.next()) {
        	int shcNum=rs.getInt("COMC_NUM");
            String shcContent =rs.getString(2);
            String nickname = rs.getString(3);
            Date shc_date =rs.getDate(4);
            int dadat =rs.getInt(5);
            ShcDTO dto = new ShcDTO(shcNum,shcContent, nickname,shc_date,dadat);
            list.add(dto);
      }
      }   catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close();
      }
      return list;
   }
   
   // 커뮤니티 댓글 수정
      public int shcModify(ShcDTO dto) {
    	 getConnection();
         String sql = "UPDATE SECONDHAND_C SET SHC_CONTENT=? WHERE SHC_NUM=?";
         try {
            psmt= conn.prepareStatement(sql);
            psmt.setString(1, dto.getShcContent());
            psmt.setInt(2, dto.getShcNum());
            cnt=psmt.executeUpdate();
            
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1; //sql 오류 발생
         }finally {
        	 close();
         }
         
         return 1; // sql 오류 없음 완료
      }
   // 커뮤니티 댓글 삭제 기능
      public int shcDelete(int shcNum) {
         getConnection();
    	  String sql = "UPDATE SECONDHAND_C SET SHC_CONTENT= '삭제된 댓글 입니다.' WHERE SHC_NUM =?";
         
         try {
            psmt= conn.prepareStatement(sql);
            psmt.setInt(1, shcNum);
            cnt=psmt.executeUpdate();
            
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1; //sql 오류 발생
         }finally {
        	 close();
         }
         
         return 1; // sql 오류 없음 완료
      }
      
   // 댓글 신고 기능
      public int comcReport (ComcDTO dto) {
         String sql = "UPDATE SECONDHAND_C SET REPORT = REPORT + 1 WHERE NICKNAME = ? AND SH_NUM =? AND DADAT=? ";
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