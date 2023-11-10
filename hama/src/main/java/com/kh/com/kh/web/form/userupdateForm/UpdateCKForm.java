package com.kh.com.kh.web.form.userupdateForm;

import lombok.Data;

@Data
public class UpdateCKForm {
  private String nickname;
  private String tel;
  private String passwd;
  private byte[] pic;
  private Long member_id;
}
