package com.kh.com.kh.web.form.memberForm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Valid
public class SignupForm {
  @NotEmpty(message = "이메일을 입력해주세요")
  private String email;
  @NotEmpty(message = "비밀번호를 입력해주세요")
  private String passwd;
  @NotEmpty(message = "별명을 입력해주세요")
  private String nickname;
}
