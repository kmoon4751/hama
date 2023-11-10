package com.kh.com.kh.domain.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
  private Long member_id;
  private String email;
  private String passwd;
  private String nickname;
  private String hobby;
  private String gubun;
  private String tel;
  private Long pic;
  private LocalDateTime cdate;
  private LocalDateTime udate;
}
