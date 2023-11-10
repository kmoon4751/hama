package com.kh.com.kh.domain.svc.MyPageSVC;

import com.kh.com.kh.web.form.userupdateForm.UpdateForm;

public interface MyPageSVC {
  Long updateCnt(UpdateForm updateForm);
  Long deletePic(Long member_id);
}
