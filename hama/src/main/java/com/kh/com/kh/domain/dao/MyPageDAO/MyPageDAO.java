package com.kh.com.kh.domain.dao.MyPageDAO;

import com.kh.com.kh.web.form.userupdateForm.UpdateForm;

public interface MyPageDAO {
  Long updateCnt(UpdateForm updateForm);

  Long deletePic(Long member_id);
}
