package com.kh.com.kh.domain.dao.PostBoxsDAO.RequestBoxDAO;

import com.kh.com.kh.web.form.requestForm.RequestAllForm;

import java.util.List;
import java.util.Optional;

public interface RequestBoxDAO {
  List<RequestAllForm> findAllRq(Long member_id);

  Long delRQ(Long requestBoard_id);
}
