package com.kh.com.kh.domain.dao.CommunityDAO;

import com.kh.com.kh.domain.dao.entity.Community;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommunityRowMapper implements RowMapper<Community> {

  @Override
  public Community mapRow(ResultSet rs, int rowNum) throws SQLException {
    Community community = new Community();
    community.setComu_post_id(rs.getLong("comu_post_id"));
    community.setComu_gubun(rs.getString("comu_gubun"));
    community.setTitle(rs.getString("title"));
    community.setContent(rs.getString("content"));
    community.setMember_id(rs.getLong("member_id"));
    community.setArea(rs.getString("area"));
    return community;
  }
}
