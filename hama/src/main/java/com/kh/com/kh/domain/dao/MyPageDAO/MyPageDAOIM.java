package com.kh.com.kh.domain.dao.MyPageDAO;


import com.kh.com.kh.web.form.userupdateForm.UpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyPageDAOIM implements MyPageDAO{

  private final NamedParameterJdbcTemplate template;


  @Override
  public Long updateCnt(UpdateForm updateForm) {
    StringBuffer sql = new StringBuffer();
    sql.append("update member set nickname = :nickname, tel = :tel, pic = :pic, passwd = :passwd where member_id = :member_id ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(updateForm);
    Long update = (long)template.update(sql.toString(), param);
    return update;
  }

  @Override
  public Long deletePic(Long member_id) {
    String sql = ("delete from uploadfile where table_id = :member_id ");
    String sql2 = ("update member set pic = null where member_id = :member_id ");

    Map<String,Long> param = Map.of("member_id",member_id);
    int update1 = template.update(sql, param);
    int update = template.update(sql2, param);

    return (long)update + update1;
  }
}
