package com.kh.com.kh.domain.dao.entity;

import lombok.Data;

@Data
public class Estimate {
  private Long board_id;      // 해당폼
  private Long member_id;     // 해당폼
  private Long work_give_id;  // 세션값
  private String esti_gubun;  // input
  private String esti_price;    // input
  private String esti_text;   // input
  private byte[] esti_file;   // input
}
