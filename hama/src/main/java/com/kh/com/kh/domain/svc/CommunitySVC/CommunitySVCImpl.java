package com.kh.com.kh.domain.svc.CommunitySVC;

import com.kh.com.kh.domain.dao.CommunityDAO.CommunityDAO;
import com.kh.com.kh.domain.dao.entity.Community;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  //게시글 상세 조회
  @Override
  public Optional<Community> viewById(Long comu_post_id) {
    return communityDAO.viewById(comu_post_id);
  }

  //게시글 수정
  @Override
  public int updateById(Long comu_post_id, Community community) {
    return communityDAO.updateById(comu_post_id, community);
  }

  //글 삭제
  @Override
  public Community deleteById(Long comu_post_id) {
    return communityDAO.deleteById(comu_post_id);
  }
}
