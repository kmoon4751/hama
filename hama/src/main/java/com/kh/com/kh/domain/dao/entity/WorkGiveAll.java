package com.kh.com.kh.domain.dao.entity;

import lombok.Data;

@Data
public class WorkGiveAll {
  private Long board_id;
  private Long member_id;
  private String nickname;
  private String category;
  private String area;
  private String hope_date;
  private String hope_text;
  private Long pic;
}
