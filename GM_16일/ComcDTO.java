package model;

import java.sql.Date;

public class ComcDTO {
   private String comcContent;
   private String id;
   private String nickname;
   private int comNum;
   private int comcReport;
   private String role;
   private int dadat;
   private int comcNum;
   private int pcomcNum;
   private Date comcDate;
   
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
   public ComcDTO(String comcContent,int comcNum ) {
      this.comcContent=comcContent;
      this.comcNum =comcNum;
   }
   
   // session에서 가져오기용
   public ComcDTO(String id, String role, String nickname) {
      this.id = id;
      this.role = role;
      this.nickname = nickname;
   }
   
   // 댓글 보기용 DTO
   public ComcDTO(int comcNum,String comcContent, String nickname, Date comcDate, int dadat) {
	   this.comcNum =comcNum;
	   this.comcContent=comcContent;
	   this.nickname=nickname;
	   this.comcDate =comcDate;
	   this.dadat =dadat;
   }

   
   
   //답글쓰기용 dto
   
   public ComcDTO( String comcContent, String id,String nickname, int comNum, String role, int pcomcNum) {
      this.comcContent = comcContent;
      this.id = id;
      this.nickname= nickname;
      this.comNum=comNum;
      this.role = role;
      this.pcomcNum =pcomcNum;
   
   }

   
   
   public ComcDTO(String comcContent, String nickname, Date comcDate) {
      this.comcContent =comcContent;
      this.nickname =nickname;
      this.comcDate =comcDate;
   }

// 댓글 쓰기용 dto
   public ComcDTO(String comcContent, String id, String nickname, int comNum, String role) {
	   this.comcContent=comcContent;
	   this.id =id;
	   this.nickname = nickname;
	   this.comNum = comNum;
	   this.role = role;
}

//  getter메소드
   public Date getComcDate() {
      return comcDate;
   }


   public String getComcContent() {
      return comcContent;
   }
   
   public String getId() {
      return id;
   }
   
   public String getNickname() {
      return nickname;
   }
   
   public int getComNum() {
      return comNum;
   }
   
   public int getComcReport() {
      return comcReport;
   }
   
   public String getRole() {
      return role;
   }
   
   public int getDadat() {
      return dadat;
   }
   
   public int getComcNum() {
      return comcNum;
   }
   
   public int getPcomcNum() {
      return pcomcNum;
   }

@Override
public String toString() {
	return "ComcDTO [comcContent=" + comcContent + ", id=" + id + ", nickname=" + nickname + ", comNum=" + comNum
			+ ", comcReport=" + comcReport + ", role=" + role + ", dadat=" + dadat + ", comcNum=" + comcNum
			+ ", pcomcNum=" + pcomcNum + ", comcDate=" + comcDate + "]";
}
   
   
   


   
}