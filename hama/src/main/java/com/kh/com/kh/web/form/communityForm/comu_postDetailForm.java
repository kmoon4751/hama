package com.kh.com.kh.web.form.communityForm;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@Valid
public class comu_postDetailForm {
    private Long comu_post_id;
    private String title;
    private String content;
    private String comu_gubun;
  }
