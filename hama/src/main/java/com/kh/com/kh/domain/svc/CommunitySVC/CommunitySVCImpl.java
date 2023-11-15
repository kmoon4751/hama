package com.kh.com.kh.domain.svc.CommunitySVC;

import com.kh.com.kh.domain.dao.CommunityDAO.CommunityDAO;
import com.kh.com.kh.domain.dao.entity.Community;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunitySVCImpl implements CommunitySVC {

  private final CommunityDAO communityDAO;

  @Autowired
  private final NamedParameterJdbcTemplate template;

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
  public Long updateById(Long comu_post_id, Community community) {
    StringBuilder sql = new StringBuilder();
    sql.append("update community ");
    sql.append("   set title = :title, content = :content, comu_gubun = :comu_gubun ");
    sql.append(" where comu_post_id = :comu_post_id ");

    MapSqlParameterSource param = new MapSqlParameterSource()
        .addValue("title", community.getTitle())
        .addValue("content", community.getContent())
        .addValue("comu_gubun", community.getComu_gubun())
        .addValue("comu_post_id", community.getComu_post_id());
    template.update(sql.toString(), param);

    return comu_post_id;
  }

  //글 삭제
  @Override
  public Community deleteById(Long comu_post_id) {
    return communityDAO.deleteById(comu_post_id);
  }
}
