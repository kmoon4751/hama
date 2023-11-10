package com.kh.com.kh.domain.dao.RequestBoardDAO;

import com.kh.com.kh.domain.dao.entity.WorkGive;
import com.kh.com.kh.domain.dao.entity.WorkGiveAll;

import java.util.List;

public interface RequestBoardDAO {
  List<WorkGiveAll> findRQBoardAll();

  WorkGive findRQBoard(Long pid);
}
