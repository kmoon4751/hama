package com.kh.com.kh.web.form.requestForm;

import lombok.Data;

import java.util.Date;

@Data
public class RequestAllForm {
  private Long board_Id;
  private Long Member_Id;
  private String category;
  private String area;
  private String hope_date;
  private String hope_text;
  private String cdate;
}
