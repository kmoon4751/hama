package com.kh.com.kh.domain.svc.ComentSVC;

import com.kh.com.kh.web.form.ComentForm.ComentAddForm;
import com.kh.com.kh.web.form.ComentForm.ComentForm;
import com.kh.com.kh.web.form.ComentForm.ComentListForm;
import com.kh.com.kh.web.form.estimateForm.EstimateAllForm;

import java.util.List;

public interface ComentSVC {
  ComentForm comentInfo(Long estimate_id);

  List<ComentListForm> comentAll(Long estimate_id);

  Long comnentAdd(ComentAddForm comentAddForm);
  Long comnentDel(Long coment_id);
}
