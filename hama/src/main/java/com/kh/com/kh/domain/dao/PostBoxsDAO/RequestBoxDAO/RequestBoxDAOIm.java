package com.kh.com.kh.domain.dao.PostBoxsDAO.RequestBoxDAO;

import com.kh.com.kh.web.form.requestForm.RequestAllForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestBoxDAOIm implements RequestBoxDAO{

  private final NamedParameterJdbcTemplate template;

  @Override
  public List<RequestAllForm> findAllRq(Long member_id) {
    StringBuffer sql = new StringBuffer();
    sql.append("select board_id,member_id,category,area,hope_date,hope_text,to_char(cdate,'YY/MM/DD')\"cdate\" ");
    sql.append("from requestboard ");
    sql.append("where member_id = :member_id ");
    sql.append("order by cdate");

    Map<String,Long> param = Map.of("member_id",member_id);
    List<RequestAllForm> query = template.query(sql.toString(), param, BeanPropertyRowMapper.newInstance(RequestAllForm.class));
    log.info("RQ={}",query);
    return query;
  }

  @Override
  public Long delRQ(Long requestBoard_id) {
    String sql = ("delete from requestBoard where Board_id = :rq_id");
    Map<String,Long> param = Map.of("rq_id",requestBoard_id);
    Long update = (long)template.update(sql, param);
    return update;
  }
}
