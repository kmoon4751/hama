package com.kh.com.kh.web.form.ComentForm;

import lombok.Data;

@Data
public class ComentAddForm {
  private Long estimate_id;
  private Long member_id;
  private String coment_text;
}
