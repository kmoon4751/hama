package com.kh.com.kh.web.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FileForm {
  private Long member_id;
  private Long estimate_id;

  private Long uploadfile_id;
  private String table_info;
  private Long table_id;
  private String store_filename;
  private String upload_filename;
  private String fsize;
  private String ftype;

  private MultipartFile attachFiles;    //설명 파일
  private MultipartFile imageFiles;    //이미지 첨부
}
