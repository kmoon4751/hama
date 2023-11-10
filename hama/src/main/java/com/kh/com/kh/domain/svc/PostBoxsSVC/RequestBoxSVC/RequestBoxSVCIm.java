package com.kh.com.kh.domain.svc.PostBoxsSVC.RequestBoxSVC;

import com.kh.com.kh.domain.dao.PostBoxsDAO.RequestBoxDAO.RequestBoxDAO;
import com.kh.com.kh.web.form.requestForm.RequestAllForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestBoxSVCIm implements RequestBoxSVC{
  private final RequestBoxDAO requestBoxDAO;
  @Override
  public List<RequestAllForm> findAllRq(Long member_id) {
    return requestBoxDAO.findAllRq(member_id);
  }

  @Override
  public Long delRQ(Long requestBoard_id) {
    return requestBoxDAO.delRQ(requestBoard_id);
  }
}
