package model;

public class ComReportDTO {
   private int comReportNum;
   private String id;
   private int comNum;
   
   // 테이블 행 삭제 생성자
   public ComReportDTO(int comReportNum) {
      this.comReportNum = comReportNum;
   }
   
   // 테이블 행 추가 생성자 , 조회 생성자
   public ComReportDTO(String id, int comNum) {
      this.id = id;
      this.comNum = comNum;
   }
   
   // 전체 생성자
   public ComReportDTO(int comReportNum, String id, int comNum) {
      this.comReportNum = comReportNum;
      this.id = id;
      this.comNum = comNum;
   }
   
   public int getComReportNum() {
      return comReportNum;
   }
   public String getId() {
      return id;
   }
   public int getComNum() {
      return comNum;
   }
   
   
   
}