package com.kh.com.kh.domain.svc.MyPageSVC;


import com.kh.com.kh.domain.dao.MyPageDAO.MyPageDAO;
import com.kh.com.kh.web.form.userupdateForm.UpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageSVCIM implements MyPageSVC{

  private final MyPageDAO myPageDAO;

  @Override
  public Long updateCnt(UpdateForm updateForm) {
    return myPageDAO.updateCnt(updateForm);
  }

  @Override
  public Long deletePic(Long member_id) {
    return myPageDAO.deletePic(member_id);
  }
}
