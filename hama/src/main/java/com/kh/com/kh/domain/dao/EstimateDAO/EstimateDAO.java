package com.kh.com.kh.domain.dao.EstimateDAO;

import com.kh.com.kh.domain.dao.entity.Estimate;
import com.kh.com.kh.web.form.estimateForm.EstimateForm;

public interface EstimateDAO {
  Long estimateInsert(EstimateForm estimateForm);
}
