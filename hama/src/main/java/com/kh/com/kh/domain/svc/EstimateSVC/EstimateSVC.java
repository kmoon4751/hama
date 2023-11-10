package com.kh.com.kh.domain.svc.EstimateSVC;

import com.kh.com.kh.domain.dao.entity.Estimate;
import com.kh.com.kh.web.form.estimateForm.EstimateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface EstimateSVC {
  Long estimateInsert(EstimateForm estimateForm);
}
