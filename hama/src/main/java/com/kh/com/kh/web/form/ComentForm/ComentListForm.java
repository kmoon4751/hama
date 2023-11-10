package com.kh.com.kh.web.form.ComentForm;

import lombok.Data;

@Data
public class ComentListForm {
  private Long coment_id;
  private Long member_id;
  private String coment_text;
  private String nickname;
  private String coment_date;
}
