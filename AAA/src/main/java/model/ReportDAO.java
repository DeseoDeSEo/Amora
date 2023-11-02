package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {
   private Connection conn=null;
   private PreparedStatement psmt=null;
   private ResultSet rs= null;
   int cnt=0;
   String un="";
   
   //커뮤니티 신고 번호 조회 메서드
   public int getComReport_num(ComReportDTO dto) {
      getConnection();
      cnt = 0;
      String sql = "SELECT COMREPORT_NUM FROM COMReport WHERE COM_NUM =? AND ID =? ";
      try {
         psmt=conn.prepareStatement(sql);
         psmt.setInt(1, dto.getComNum());
         psmt.setString(2, dto.getId());
         rs =psmt.executeQuery();
         if (rs.next()) {
            cnt = rs.getInt(1);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close();
      }
      return cnt;
      
   }
 //(중고거래/나눔)  신고 번호 조회 메서드
   public int getShReport_num(ShReportDTO dto) {
	      getConnection();
	      cnt = 0;
	      String sql = "SELECT SHREPORT_NUM FROM SHReport WHERE COM_NUM =? AND ID =? ";
	      try {
	         psmt=conn.prepareStatement(sql);
	         psmt.setInt(1, dto.getShNum());
	         psmt.setString(2, dto.getId());
	         rs =psmt.executeQuery();
	         if (rs.next()) {
	            cnt = rs.getInt(1);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         close();
	      }
	      return cnt;
	      
	   }
   
   
   // 커뮤니티 신고 행 삭제 메서드
   public int ComUnReport(int comReportNum) {
      getConnection();
      String sql = "DELETE FROM COMREPORT WHERE COMREPORT_NUM=?";
      try {
         psmt= conn.prepareStatement(sql);
         psmt.setInt(1, comReportNum);
         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close();
      }
      return cnt;
   }
   // 신고 버튼 누르면  커뮤니티 신고 행 삽입 시켜주는 메서드
   public int ComReport(ComReportDTO cldto) {
      getConnection();
      String sql = "INSERT INTO COMREPORT VALUES(COMREPORT_SEQ.NEXTVAL, ?,?)";
      try {
         psmt= conn.prepareStatement(sql);
         psmt.setString(1, cldto.getId());
         psmt.setInt(2, cldto.getComNum());
         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close();
      }
      return cnt;
   }

   // 공동구매 신고 행 삭제 메서드
//   public int GbUnReport(int gbReportNum) {
//      getConnection();
//      String sql = "DELETE FROM GBREPORT WHERE GBREPORT_NUM=?";
//      try {
//         psmt= conn.prepareStatement(sql);
//         psmt.setInt(1, gbReportNum);
//         cnt = psmt.executeUpdate();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }finally {
//         close();
//      }
//      return cnt;
//   }
//   
//   // 공동구매 신고 행 삽입 메서드
//   public int GbReport(GbReportDTO grdto) {
//      getConnection();
//      String sql = "INSERT INTO GBReport VALUES(GBReport_SEQ.NEXTVAL, ?,?)";
//      try {
//         psmt= conn.prepareStatement(sql);
//         psmt.setString(1, grdto.getId());
//         psmt.setInt(2, grdto.getGbNum());
//         cnt = psmt.executeUpdate();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }finally {
//         close();
//      }
//      return cnt;
//   }
   
   // 중고거래 신고 행 삭제 메서드
   public int shUnReport(int shReportNum) {
      getConnection();
      String sql = "DELETE FROM SHREPORT WHERE SHREPORT_NUM=?";
      try {
         psmt= conn.prepareStatement(sql);
         psmt.setInt(1, shReportNum);
         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close();
      }
      return cnt;
   }
   
   // 중고거래 신고 행 삭제 메서드
   public int ShReport(ShReportDTO srdto) {
      getConnection();
      String sql = "INSERT INTO SHREPORT VALUES(GBReport_SEQ.NEXTVAL, ?,?)";
      try {
         psmt= conn.prepareStatement(sql);
         psmt.setString(1, srdto.getId());
         psmt.setInt(2, srdto.getShNum());
         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close();
      }
      return cnt;
   }
   
   
   
   
   // 댓글 작성이나 특정 행동후 실시간 업데이트를 위한 신고 개수 가져오기  
   public int getReports(int comNum) {
      getConnection();
      int comReport = 0;
      String sql = "SELECT COM_REPORT FROM COMMUNITY WHERE COM_NUM = ?";
      try {
         psmt= conn.prepareStatement(sql);
         psmt.setInt(1, comNum);
         rs= psmt.executeQuery();
         if(rs.next()) {
            comReport =rs.getInt(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close();
      }
      return comReport;
   }
   // 중고거래에서 신고 개수 가져오기
   public int getShReports(int shNum) {
	      getConnection();
	      int shReport = 0;
	      String sql = "SELECT SH_REPORT FROM SECONDHAND WHERE SH_NUM = ?";
	      try {
	         psmt= conn.prepareStatement(sql);
	         psmt.setInt(1, shNum);
	         rs= psmt.executeQuery();
	         if(rs.next()) {
	            shReport =rs.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close();
	      }
	      return shReport;
	   }
   
   // 커뮤니티에서 신고 눌럿는지 확인하는 메서드 CHAT GPT피셜
   public boolean isComUserReported(String userId, int comNum) {
       getConnection();
       boolean isReported = false;
       String sql = "SELECT * FROM COMREPORT WHERE COM_NUM = ? AND ID = ?";
       try {
           psmt = conn.prepareStatement(sql);
           psmt.setInt(1, comNum);
           psmt.setString(2, userId);
           rs = psmt.executeQuery();
           if (rs.next()) {
               isReported = true;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           close();
       }
       return isReported;
   }
   // 중고거래에서 신고 여부 확인
   public boolean isShUserReported(String userId, int comNum) {
       getConnection();
       boolean isReported = false;
       String sql = "SELECT * FROM SHreport WHERE COM_NUM = ? AND ID = ?";
       try {
           psmt = conn.prepareStatement(sql);
           psmt.setInt(1, comNum);
           psmt.setString(2, userId);
           rs = psmt.executeQuery();
           if (rs.next()) {
               isReported = true;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           close();
       }
       return isReported;
   }

   
    // 데이터 베이스 연결
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