package com.kh.com.kh.web.form.requestForm;

import lombok.Data;

@Data
public class RequestForm {
  private Long board_Id;
  private Long Member_Id;
  private String nickname;
  private String category;
  private String area;
  private String hope_date;
  private String hope_text;
}
