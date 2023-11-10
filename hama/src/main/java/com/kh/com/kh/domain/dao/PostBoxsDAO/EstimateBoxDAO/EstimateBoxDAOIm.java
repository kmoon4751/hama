package com.kh.com.kh.domain.dao.PostBoxsDAO.EstimateBoxDAO;

import com.kh.com.kh.domain.dao.entity.WorkGiveNick;
import com.kh.com.kh.web.form.estimateForm.EstimateAllForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EstimateBoxDAOIm implements EstimateBoxDAO{

  private final NamedParameterJdbcTemplate template;

  @Override
  public List<EstimateAllForm> findAllPostEs(Long member_id) {
    StringBuffer sql = new StringBuffer();
    sql.append("select e1.estimate_id, e1.board_id , e1.member_id, e1.work_member_id, e1.esti_gubun, r1.category, r1.hope_date, ");
    sql.append("e1.esti_price, e1.esti_text, e1.esti_file, m1.nickname, r1.area,to_char(e1.cdate,'YY/MM/DD')\"cdate\" ");
    sql.append("from estimate e1, requestBoard r1, member m1 ");
    sql.append("where e1.member_id = m1.member_id ");
    sql.append("and e1.board_id = r1.board_id ");
    sql.append("and e1.work_member_id = :member_id ");
    sql.append("order by e1.cdate");
    Map<String,Long> param = Map.of("member_id",member_id);
    List<EstimateAllForm> query = template.query(sql.toString(), param, BeanPropertyRowMapper.newInstance(EstimateAllForm.class));

    log.info("EstiPost={}",query);
    return query;
  }

  @Override
  public List<EstimateAllForm> findAllGetEs(Long member_id) {
    log.info("member_id={}",member_id);
    StringBuffer sql = new StringBuffer();
    sql.append("select e1.estimate_id, e1.board_id , e1.member_id, e1.work_member_id, e1.esti_gubun, r1.category, r1.hope_date, ");
    sql.append("e1.esti_price, e1.esti_text, e1.esti_file, m1.nickname, r1.area,to_char(e1.cdate,'YY/MM/DD')\"cdate\" ");
    sql.append("from estimate e1, requestBoard r1, member m1 ");
    sql.append("where e1.work_member_id = m1.member_id ");
    sql.append("and e1.board_id = r1.board_id ");
    sql.append("and e1.member_id = :member_id ");
    sql.append("order by e1.cdate");
    Map<String,Long> param = Map.of("member_id",member_id);
    List<EstimateAllForm> query = template.query(sql.toString(), param, BeanPropertyRowMapper.newInstance(EstimateAllForm.class));

    log.info("EstiGet={}",query);
    return query;
  }

  @Override
  public Long estiDel(Long estimate_id) {
    String sqlComent = ("delete from coment where estimate_id = :estimate_id");
    String sqlEstimate = ("delete from estimate where estimate_id = :estimate_id");
    Map<String,Long> param = Map.of("estimate_id",estimate_id);
    int update1 = template.update(sqlComent, param);
    Long update = (long)template.update(sqlEstimate, param);

    return update;
  }
}
