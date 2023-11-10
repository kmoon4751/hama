package com.kh.com.kh.domain.svc.EstimateSVC;

import com.kh.com.kh.domain.dao.EstimateDAO.EstimateDAO;
import com.kh.com.kh.domain.dao.entity.Estimate;
import com.kh.com.kh.web.form.estimateForm.EstimateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstimateSVCIM implements EstimateSVC{
  private final EstimateDAO estimateDAO;

  @Override
  public Long estimateInsert(EstimateForm estimateForm) {
    return estimateDAO.estimateInsert(estimateForm);
  }
}
