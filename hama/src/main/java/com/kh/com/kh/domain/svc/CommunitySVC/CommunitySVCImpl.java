package com.kh.com.kh.domain.svc.CommunitySVC;

import com.kh.com.kh.domain.dao.CommunityDAO.CommunityDAO;
import com.kh.com.kh.domain.dao.entity.Community;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunitySVCImpl implements CommunitySVC {

  private final CommunityDAO communityDAO;

  @Override
  public Long savePost(Community community) {
    return communityDAO.savePost(community);
  }

  @Override
  public List<Community> questionAll() {
    return communityDAO.questionAll();
  }

  @Override
  public List<Community> howMuchAll() {
    return communityDAO.howMuchAll();
  }

  @Override
  public List<Community> getheringAll() {
    return communityDAO.getheringAll();
  }

  //글 삭제
  @Override
  public int deleteById(Long comu_post_id) {
    return communityDAO.deleteById(comu_post_id);
  }
}
