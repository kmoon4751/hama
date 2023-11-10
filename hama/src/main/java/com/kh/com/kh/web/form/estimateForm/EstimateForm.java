package com.kh.com.kh.web.form.estimateForm;

import lombok.Data;

@Data
public class EstimateForm {
  private Long board_id;      // 해당폼
  private Long member_id;     // 해당폼
  private Long work_member_id;  // 세션값
  private String esti_gubun;  // input
  private String esti_price;    // input
  private String esti_text;   // input
  private Long esti_file;   // input
}
