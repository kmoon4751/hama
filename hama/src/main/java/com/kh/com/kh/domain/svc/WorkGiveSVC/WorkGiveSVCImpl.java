package com.kh.com.kh.domain.svc.WorkGiveSVC;

import com.kh.com.kh.domain.dao.WorkGiveDAO.WorkGiveDAO;
import com.kh.com.kh.web.form.workgiveForm.GiveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkGiveSVCImpl implements WorkGiveSVC{
  private final WorkGiveDAO workGiveDAO;
  @Override
  public Long giveInsert(GiveForm giveForm) {
    return workGiveDAO.giveInsert(giveForm);
  }
}
