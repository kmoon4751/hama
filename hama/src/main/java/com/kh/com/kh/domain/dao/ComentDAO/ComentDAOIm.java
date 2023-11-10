package com.kh.com.kh.domain.dao.ComentDAO;

import com.kh.com.kh.web.form.ComentForm.ComentAddForm;
import com.kh.com.kh.web.form.ComentForm.ComentForm;
import com.kh.com.kh.web.form.ComentForm.ComentListForm;
import com.kh.com.kh.web.form.estimateForm.EstimateAllForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ComentDAOIm implements ComentDAO{

  private final NamedParameterJdbcTemplate template;

  @Override
  public ComentForm comentInfo(Long estimate_id) {
    StringBuffer sql = new StringBuffer();
    sql.append("select e1.work_member_id,m1.pic,m1.nickname,esti_gubun,esti_price ,esti_text,r1.hope_date,r1.area, to_char(e1.cdate,'YY/MM/DD')\"esti_cdate\", ");
    sql.append("pic ");
    sql.append("from estimate e1, requestBoard r1, member m1 ");
    sql.append("where e1.board_id = r1.board_id ");
    sql.append("and e1.work_member_id = m1.member_id ");
    sql.append("and estimate_id = :estimate_id ");

    Map<String,Long> param = Map.of("estimate_id",estimate_id);
    ComentForm comentForm = template.queryForObject(sql.toString(), param, BeanPropertyRowMapper.newInstance(ComentForm.class));

    return comentForm;
  }

  @Override
  public List<ComentListForm> comentAll(Long estimate_id) {
    StringBuffer sql = new StringBuffer();
    sql.append("select coment_id,c1.member_id,m1.nickname,coment_text,to_char(c1.cdate,'YY/MM/DD')\"coment_date\" ");
    sql.append("from coment c1, member m1 ");
    sql.append("where estimate_id = :estimate_id ");
    sql.append("and c1.member_id = m1.member_id ");
    sql.append("order by c1.cdate ");

    Map<String, Long> param = Map.of("estimate_id",estimate_id);
    List<ComentListForm> query = template.query(sql.toString(), param, BeanPropertyRowMapper.newInstance(ComentListForm.class));

    return query;
  }

  @Override
  public Long comnentAdd(ComentAddForm comentAddForm) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into coment (coment_id,estimate_id, member_id, coment_text) ");
    sql.append("values(coment_coment_id_seq.nextval, :estimate_id, :member_id, :coment_text) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(comentAddForm);
    Long update = (long)template.update(sql.toString(), param);
    return update;
  }

  @Override
  public Long comnentDel(Long coment_id) {
    StringBuffer sql = new StringBuffer();
    sql.append("Delete from coment where coment_id = :coment_id ");
    Map<String,Long> param = Map.of("coment_id", coment_id);
    Long update = (long)template.update(sql.toString(), param);
    return update;
  }
}
