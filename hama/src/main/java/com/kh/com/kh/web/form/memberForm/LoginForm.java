package com.kh.com.kh.web.form.memberForm;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
  @NotEmpty(message = "이메일을 입력해주세요")
  private String email;
  @NotEmpty(message = "비밀번호를 입력해주세요")
  private String passwd;
}
