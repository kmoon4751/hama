package com.kh.com.kh.domain.svc.ComentSVC;

import com.kh.com.kh.domain.dao.ComentDAO.ComentDAO;
import com.kh.com.kh.web.form.ComentForm.ComentAddForm;
import com.kh.com.kh.web.form.ComentForm.ComentForm;
import com.kh.com.kh.web.form.ComentForm.ComentListForm;
import com.kh.com.kh.web.form.estimateForm.EstimateAllForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComentSVCIm implements ComentSVC{

  private final ComentDAO comentDAO;
  @Override
  public ComentForm comentInfo(Long estimate_id) {
    return comentDAO.comentInfo(estimate_id);
  }

  @Override
  public List<ComentListForm> comentAll(Long estimate_id) {
    return comentDAO.comentAll(estimate_id);
  }

  @Override
  public Long comnentAdd(ComentAddForm comentAddForm) {
    return comentDAO.comnentAdd(comentAddForm);
  }

  @Override
  public Long comnentDel(Long coment_id) {
    return comentDAO.comnentDel(coment_id);
  }
}
