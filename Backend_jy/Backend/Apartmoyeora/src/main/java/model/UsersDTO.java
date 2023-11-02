package model;

public class UsersDTO {
   
   private String id;
   private String pw;
   private String nickname;
   private String name;
   private String phone;
   private String email;
   private String birthdate;
   private String gender;
   private String addr;
   private String role;
   private int u_point;
   private String approval;
   private String dongho;
   private String joindate;
   
   //생성자
   // 로그인용 DTO
   public UsersDTO(String id, String pw) {

      
      this.id = id;
      this.pw = pw;
   }
   
   
   



    // 세션에 남기는 DTO
   public UsersDTO(String id, String nickname, String role ,String addr) {
      this.id = id;
      this.nickname = nickname;
      this.role = role;
      this.addr= addr;
   }



    // 유저 정보 수정용 DTO
   public UsersDTO(String id, String pw, String nickname, String phone, String email) {
      this.pw=pw;
      this.nickname=nickname;
      this.phone= phone;
      this.email= email;
      this.id= id;
   }

   




   
   // 회원가입 승인여부 확인용 DTO
   public UsersDTO(String id, String nickname, String name, String addr, String dongho,String approval ) {
      this.id =id;
      this.nickname = nickname;
      this.name=name;
      this.addr = addr;
      this.dongho=dongho;
      this.approval=approval;
      
   }

   // 회원가입용 DTO(관리자)
   public UsersDTO(String id, String pw, String nickname, String name, String phone, String email, String birthdate,
         String gender, String addr ) {
      this.id = id;
      this.pw = pw;
      this.nickname = nickname;
      this.name = name;
      this.phone = phone;
      this.email = email;
      this.birthdate = birthdate;
      this.gender = gender;
      this.addr = addr;
      
   }





   





   // 회원가입용DTO 일반 유저
   public UsersDTO(String id, String pw, String nickname, String name, String phone, String email, String birthdate,
         String gender, String addr,String dongho ) {

      this.id = id;
      this.pw = pw;
      this.nickname = nickname;
      this.name = name;
      this.phone = phone;
      this.email = email;
      this.birthdate = birthdate;
      this.gender = gender;
      this.addr = addr;
      this.dongho=dongho;
      
   }





   // 전체 생성자
   public UsersDTO(String id, String pw, String nickname, String name, String phone, String email, String birthdate,
         String gender, String addr, int u_point, String approval, String dongho, String role, String joindate) {
      this.id = id;
      this.pw = pw;
      this.nickname = nickname;
      this.name = name;
      this.phone = phone;
      this.email = email;
      this.birthdate = birthdate;
      this.gender = gender;
      this.addr = addr;
      this.u_point = u_point;
      this.approval = approval;
      this.dongho = dongho;
      this.role = role;
      this.joindate = joindate;
   }





   // Getters
   public String getJoindate() {
      return joindate;
   }
   public String getId() {
      return id;
   }
   public String getPw() {
      return pw;
   }
   public String getNickname() {
      return nickname;
   }
   public String getName() {
      return name;
   }
   public String getPhone() {
      return phone;
   }
   public String getEmail() {
      return email;
   }
   public String getBirthdate() {
      return birthdate;
   }
   public String getRole() {
      return role;
   }
   public String getGender() {
      return gender;
   }
   public String getAddr() {
      return addr;
   }
   public int getU_point() {
      return u_point;
   }
   public String getApproval() {
      return approval;
   }
   public String getDongho() {
      return dongho;
   }
   
   
   
   
}
   
   
   