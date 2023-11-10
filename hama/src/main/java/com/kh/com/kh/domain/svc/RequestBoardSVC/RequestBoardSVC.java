package com.kh.com.kh.domain.svc.RequestBoardSVC;

import com.kh.com.kh.domain.dao.entity.WorkGive;
import com.kh.com.kh.domain.dao.entity.WorkGiveAll;

import java.util.List;

public interface RequestBoardSVC {
  List<WorkGiveAll> findRQBoardAll();

  WorkGive findRQBoard(Long pid);

}
