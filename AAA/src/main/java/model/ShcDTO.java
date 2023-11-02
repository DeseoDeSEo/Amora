package model;

import java.sql.Date;

public class ShcDTO {
   private String ShcContent;
   private String id;
   private String nickname;
   private int shNum;
   private int ShcReport;
   private String role;
   private int dadat;
   private int shcNum;
   private int pshcNum;
   private Date shcDate;
   
   // 댓긍을 쓰면 DB에 추가하는 메서드용 생성자
   // 사용자가 댓글에 쓰는거는 실제로는 콘텐츠밖에 없음
   // 나머지는 DB 또는 세션에서 가져오기
   // 세션에 있는 데이터는 ID, NICKNAME,ADDR, ROLE
   //
   // INSERT문 SQL
   /*                                           
    * 1. comcContent(form, getParameter)
    * (2,3) . ID, NICKNAME(session , info)
    * 4. comment에 있는 db, COM_NUM과 NICKNAME 조회
    * 5. session에 있는 role 조회
    * 6. 변수 dadat 
    String sql1 = "insert into community_c values(chc_num_seq.nextval(),?,?,?,sysdate,?,0,?,?)";
    
    2) 수정
    1. comContent(form, getParameter)
    2. nickname(session , info)
    3. com_num (db, com_num)
    4. DADAT 본인 DB 조회 
    String sq12 = "update COMMUNITY_C SET COMC_CONTENT=? WHERE NICKNAME = ? AND COM_NUM=? AND DADAT =? "; 
    
    3) 댓글 신고
    1. nickname(session, info)
    2. com_num(db , com_num)
    3. dadat 본인 db조회
    String sql3 = "update COmmunity_c set report = report +1  where nickname =? AND COM_NUM =? AND DADAT = ?"
   */

   

   

   // 수정용 dto nickname과 comcNum 같은걸 where절로 줘서 삭제 가능하게 
   public ShcDTO(String shcContent,int shcNum ) {
      this.ShcContent=shcContent;
      this.shcNum =shcNum;
   }
   
   // session에서 가져오기용
   public ShcDTO(String id, String role, String nickname) {
      this.id = id;
      this.role = role;
      this.nickname = nickname;
   }
   
   // 댓글 보기용 DTO
   public ShcDTO(int shcNum,String ShcContent, String nickname, Date shcDate, int dadat) {
	   this.shcNum =shcNum;
	   this.ShcContent=ShcContent;
	   this.nickname=nickname;
	   this.shcDate =shcDate;
	   this.dadat =dadat;
   }

   
   
   //답글쓰기용 dto
   
   public ShcDTO( String shcContent, String id,String nickname, int shNum, String role, int pshcNum) {
      this.ShcContent = shcContent;
      this.id = id;
      this.nickname= nickname;
      this.shNum=shNum;
      this.role = role;
      this.pshcNum =pshcNum;
   
   }

   
   
   public ShcDTO(String ShcContent, String nickname, Date shcDate) {
      this.ShcContent =ShcContent;
      this.nickname =nickname;
      this.shcDate =shcDate;
   }

// 댓글 쓰기용 dto
   public ShcDTO(String ShcContent, String id, String nickname, int shNum, String role) {
	   this.ShcContent=ShcContent;
	   this.id =id;
	   this.nickname = nickname;
	   this.shNum = shNum;
	   this.role = role;
}


//  getter메소드
   public String getShcContent() {
	   return ShcContent;
   }
   
   public String getId() {
	   return id;
   }
   
   public String getNickname() {
	   return nickname;
   }
   
   public int getShNum() {
	   return shNum;
   }
   
   public int getShcReport() {
	   return ShcReport;
   }
   
   public String getRole() {
	   return role;
   }
   
   public int getDadat() {
	   return dadat;
   }
   
   public int getShcNum() {
	   return shcNum;
   }
   
   public int getPshcNum() {
	   return pshcNum;
   }
   
   public Date getShcDate() {
	   return shcDate;
   }


   
   
   


   
}