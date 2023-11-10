package com.kh.com.kh.domain.dao.EstimateDAO;

import com.kh.com.kh.domain.dao.entity.Estimate;
import com.kh.com.kh.web.form.estimateForm.EstimateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EstimateDAOIM implements EstimateDAO{

  private final NamedParameterJdbcTemplate template;


//  insert into estimate (estimate_id, board_id ,member_id, work_member_id, esti_gubun, esti_price, esti_text)
//  values(estimate_estimate_id_seq.nextval, 1, 1, 1,'총 비용', '3,000', '잘해드리겠습니다!');
  @Override
  public Long estimateInsert(EstimateForm estimateForm) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into estimate (estimate_id, board_id ,member_id, work_member_id, esti_gubun, esti_price, esti_text, esti_file) ");
    sql.append("values (estimate_estimate_id_seq.nextval, :board_id, :member_id, :work_member_id, :esti_gubun, :esti_price, :esti_text, :esti_file) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(estimateForm);
    Long update = (long)template.update(sql.toString(), param);
    return update;
  }
}
