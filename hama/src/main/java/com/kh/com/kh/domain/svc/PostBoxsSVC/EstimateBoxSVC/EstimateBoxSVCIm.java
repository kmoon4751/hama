package com.kh.com.kh.domain.svc.PostBoxsSVC.EstimateBoxSVC;

import com.kh.com.kh.domain.dao.PostBoxsDAO.EstimateBoxDAO.EstimateBoxDAO;
import com.kh.com.kh.web.form.estimateForm.EstimateAllForm;
import com.kh.com.kh.web.form.requestForm.RequestAllForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstimateBoxSVCIm implements EstimateBoxSVC{

  private final EstimateBoxDAO estimateBoxDAO;

  @Override
  public List<EstimateAllForm> findAllPostEs(Long member_id) {
    return estimateBoxDAO.findAllPostEs(member_id);
  }

  @Override
  public List<EstimateAllForm> findAllGetEs(Long member_id) {
    return estimateBoxDAO.findAllGetEs(member_id);
  }

  @Override
  public Long estiDel(Long estimate_id) {
    return estimateBoxDAO.estiDel(estimate_id);
  }
}
