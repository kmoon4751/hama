package com.kh.com.kh.domain.dao.CommunityDAO;

import com.kh.com.kh.domain.dao.entity.Community;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommunityDAOImpl implements CommunityDAO {

  private final NamedParameterJdbcTemplate template;

  //궁금해요 게시글 등록
  @Override
  public Long savePost(Community community) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert  into community(comu_post_id,comu_gubun,title,content,member_id,area) ");
    sql.append(" values(community_comu_post_id_seq.nextval,:comu_gubun, :title, :content, :member_id, :area) ");

    Map<String,Object> param = new HashMap<>();
    param.put("member_id", community.getMember_id());
    param.put("area", community.getArea());
    param.put("comu_gubun", community.getComu_gubun());
    param.put("title", community.getTitle());
    param.put("content", community.getContent());

    template.update(sql.toString(),param);

    Long comu_post_id = template.queryForObject("SELECT community_comu_post_id_seq.currval FROM dual", param, Long.class);

    return comu_post_id;
  }


  private RowMapper<Community> productRowMapper(){
    return (rs,rowNum)->{
      Community community = new Community();
      community.setComu_post_id(rs.getLong("comu_post_id"));
      community.setComu_gubun(rs.getString("comu_gubun"));
      community.setTitle(rs.getString("title"));
      community.setContent(rs.getString("content"));
      community.setMember_id(rs.getLong("member_id"));
      community.setArea(rs.getString("area"));
      return community;
    };
  }

  //궁금해요 게시글 목록
  @Override
  public List<Community> questionAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select comu_post_id,member_id, title, content, udate ");
    sql.append(" from community ");
    sql.append(" where comu_gubun = '궁금해요' ");
    sql.append(" order by udate desc ");

    List<Community> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Community.class));
    return list;
  }

  //얼마예요 게시글 목록
  @Override
  public List<Community> howMuchAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select comu_post_id,member_id, title, content, udate ");
    sql.append(" from community ");
    sql.append(" where comu_gubun = '얼마예요' ");
    sql.append(" order by udate desc ");

    List<Community> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Community.class));
    return list;
  }

  //모여봐요 게시글 목록
  @Override
  public List<Community> getheringAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select comu_post_id,member_id, title, content, udate ");
    sql.append(" from community ");
    sql.append(" where comu_gubun = '모여봐요' ");
    sql.append(" order by udate desc ");

    List<Community> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Community.class));
    return list;
  }

  //게시글 수정
  @Override
  public int updateById(Long member_id, Community community) {
    return 0;
  }

  //게시글 삭제
  @Override
  public int deleteById(Long comu_post_id) {
    String sql = "delete from community where comu_post_id = :comu_post_id ";

    int deleteRowCnt = template.update(sql, Map.of("comu_post_id", comu_post_id));
    log.info("delcnt={}",deleteRowCnt);
    return deleteRowCnt;
  }

  //게시글 검색
  @Override
  public Optional<Community> findByTitle(String title) {
    return Optional.empty();
  }
}

//RowMapper = 다수의 객체를 List 형식으로 담아준다.
