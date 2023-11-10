package com.kh.com.kh.domain.svc.PostBoxsSVC.EstimateBoxSVC;

import com.kh.com.kh.web.form.estimateForm.EstimateAllForm;
import com.kh.com.kh.web.form.requestForm.RequestAllForm;

import java.util.List;

public interface EstimateBoxSVC {
  List<EstimateAllForm> findAllPostEs(Long member_id);

  List<EstimateAllForm> findAllGetEs(Long member_id);

  Long estiDel (Long estimate_id);
}
