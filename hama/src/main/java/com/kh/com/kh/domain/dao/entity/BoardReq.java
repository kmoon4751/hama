package com.kh.com.kh.domain.dao.entity;

import lombok.Data;

@Data
public class BoardReq {
  private Long board_Id;
  private Long Member_Id;
  private String nickname;
  private String category;
  private String area;
  private String hope_date;
  private String hope_test;
}
