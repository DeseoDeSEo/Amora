
package model;

public class ShReportDTO {

   private int shReportNum;
   private String id;
   private int shNum;

   // 테이블 행 삭제 생성자
   public ShReportDTO(int shReportNum) {
      this.shReportNum = shReportNum;
   }

   // 테이블 행 추가 생성자
   public ShReportDTO(String id, int shNum) {
      this.id = id;
      this.shNum = shNum;
   }

   // 전체 생성자
   public ShReportDTO(int shReportNum, String id, int shNum) {
      this.shReportNum = shReportNum;
      this.id = id;
      this.shNum = shNum;
   }
   //getter
   public int getShReportNum() {
      return shReportNum;
   }

   public String getId() {
      return id;
   }

   public int getShNum() {
      return shNum;
   }

}