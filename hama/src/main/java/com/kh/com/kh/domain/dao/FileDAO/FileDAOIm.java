package com.kh.com.kh.domain.dao.FileDAO;

import com.kh.com.kh.web.form.FileForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FileDAOIm implements FileDAO{

  private final NamedParameterJdbcTemplate template;

  @Override
  public Long addFile(FileForm fileForm) {

    StringBuffer sql = new StringBuffer();
    sql.append("insert into uploadfile (uploadfile_id, table_info, table_id, ");
    sql.append("store_filename, upload_filename, fsize, ftype) ");
    sql.append("values (uploadfile_uploadfile_id_seq.nextval, :table_info, :table_id, ");
    sql.append(":store_filename, :upload_filename, :fsize, :ftype) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(fileForm);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    Long update = (long)template.update(sql.toString(), param, keyHolder,new String[]{"uploadfile_id"});
    Long uploadfile_id = keyHolder.getKey().longValue();

    log.info("uploadfile_id={}",uploadfile_id);
    String msql = ("update member set pic = :uploadfile_id where member_id = :member_id");
    MapSqlParameterSource param2 = new MapSqlParameterSource();
    param2.addValue("uploadfile_id",uploadfile_id);
    param2.addValue("member_id",fileForm.getTable_id());
    int update1 = template.update(msql, param2);

    return uploadfile_id;
  }

  @Override
  public Optional<FileForm> findFile(Long uploadFile_id) {
    String sql = ("select table_info,store_filename from uploadfile where uploadFile_id = :uploadFile_id");

    try {
      Map<String, Long> param = Map.of("uploadFile_id", uploadFile_id);
      FileForm fileForm = template.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(FileForm.class));
      return Optional.of(fileForm);
    }catch (EmptyResultDataAccessException e){
      return Optional.empty();
    }
  }
}
