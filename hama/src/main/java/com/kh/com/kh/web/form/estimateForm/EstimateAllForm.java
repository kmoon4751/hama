package com.kh.com.kh.web.form.estimateForm;

import lombok.Data;

@Data
public class EstimateAllForm {
  private Long estimate_id;
  private Long board_id;      // 해당폼
  private Long member_id;     // 해당폼
  private String nickname;
  private Long work_member_id;  // 세션값
  private String esti_gubun;  // input
  private String esti_price;    // input
  private String esti_text;   // input
  private Long esti_file;   // input
  private String cdate;
  private String area;
  private String category;
  private String hope_date;
}
