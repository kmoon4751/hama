package com.kh.com.kh.domain.dao.RequestBoardDAO;

import com.kh.com.kh.domain.dao.entity.WorkGive;
import com.kh.com.kh.domain.dao.entity.WorkGiveAll;
import com.kh.com.kh.domain.dao.entity.WorkGiveNick;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestBoardDAOImpl implements RequestBoardDAO{
  private final NamedParameterJdbcTemplate template;

  @Override
  public List<WorkGiveAll> findRQBoardAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select board_id,r1.member_id,category,area,hope_date,hope_text ,nickname,pic ");
    sql.append("from requestBoard r1 , member m1 ");
    sql.append("where r1.member_id = m1.member_id ");
    sql.append("order by r1.cdate ");

    List<WorkGiveAll> query = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(WorkGiveAll.class));
    log.info("DAOquery={}",query);
    return query;
  }

  @Override
  public WorkGive findRQBoard(Long pid) {
    // 단건조회
    StringBuffer sql = new StringBuffer();
    sql.append("select board_id,r1.member_id,category,area,hope_date,hope_text,pic ");
    sql.append("from requestBoard r1, member m1 ");
    sql.append("where board_id = :board_id ");
    sql.append("and r1.member_id = m1.member_id ");
    Map<String, Long> param = Map.of("board_id",pid);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    WorkGive workGive = template.queryForObject(sql.toString(), param, BeanPropertyRowMapper.newInstance(WorkGive.class));
    Long memberId = workGive.getMember_id();
    // 해당건 닉네임 조회
    String sql2 =
        ("select member_id, nickname from member where member_id = :memberId");
    Map<String,Long> param2 = Map.of("memberId",memberId);
    WorkGiveNick workGiveNick = template.queryForObject(sql2, param2, BeanPropertyRowMapper.newInstance(WorkGiveNick.class));
    workGive.setNickname(workGiveNick.getNickname());
    return workGive;
  }
}
