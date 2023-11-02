package model;

public class ContactDTO {
   

   private int gb_Num;
   private String gb_Title;
   private String gb_Content;
   private String id;
   private String nickname;
   private String gb_Date;
   private int gb_Views;
   private String gb_Period;
   private int buter_Count;
   private String role;
   private int gb_like;
   


   
   //중고거래/나눔 여부를 정해주는 필드
   private String section;
   
   
   public String getSection() {
      return section;
   }

   public void setSection(String section) {
      this.section = section;
   }

   //파일 업로드를위한 추가변수
      private String fileName;
      private String fileRealName;
      
      
   //파일 업로드를 위한 추가 매소드
      public String getFileName() {
         return fileName;
      }

      public void setFileName(String fileName) {
         this.fileName = fileName;
      }


      public String getFileRealName() {
         return fileRealName;
      }

      public void setFileRealName(String fileRealName) {
         this.fileRealName = fileRealName;
      }

      
      
      
      

      public String getGb_Title() {
         return gb_Title;
      }

      public void setGb_Title(String gb_Title) {
         this.gb_Title = gb_Title;
      }

      public String getGb_Content() {
         return gb_Content;
      }

      public void setGb_Content(String gb_Content) {
         this.gb_Content = gb_Content;
      }

      public String getId() {
         return id;
      }

      public void setId(String id) {
         this.id = id;
      }

      public String getNickname() {
         return nickname;
      }

      public void setNickname(String nickname) {
         this.nickname = nickname;
      }

      public String getGb_Date() {
         return gb_Date;
      }

      public void setGb_Date(String gb_Date) {
         this.gb_Date = gb_Date;
      }

      public int getGb_Views() {
         return gb_Views;
      }

      public void setGb_Views(int gb_Views) {
         this.gb_Views = gb_Views;
      }

      public String getGb_Period() {
         return gb_Period;
      }

      public void setGb_Period(String gb_Period) {
         this.gb_Period = gb_Period;
      }

      public int getButer_Count() {
         return buter_Count;
      }

      public void setButer_Count(int buter_Count) {
         this.buter_Count = buter_Count;
      }

      public String getRole() {
         return role;
      }

      public void setRole(String role) {
         this.role = role;
      }

      public int getGb_like() {
         return gb_like;
      }

      public void setGb_like(int gb_like) {
         this.gb_like = gb_like;
      }

      public int getGb_Num() {
         return gb_Num;
      }
      public void setGb_Num(int gb_Num) {
         this.gb_Num = gb_Num;
      }

   
      
      
   
   
   

}   