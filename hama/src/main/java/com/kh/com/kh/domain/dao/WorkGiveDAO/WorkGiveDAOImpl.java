package com.kh.com.kh.domain.dao.WorkGiveDAO;

import com.kh.com.kh.web.form.workgiveForm.GiveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WorkGiveDAOImpl implements WorkGiveDAO{

  private final NamedParameterJdbcTemplate template;

  @Override
  public Long giveInsert(GiveForm giveForm) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text) ");
    sql.append("values(requestB_requestB_id_seq.nextval, :member_id, ");
    sql.append(":category, :area, :hope_date, :hope_text) ");
    SqlParameterSource param = new BeanPropertySqlParameterSource(giveForm);
    Long insert = (long)template.update(sql.toString(), param);
    return insert;
  }
}
