package com.kh.com.kh.domain.svc.FileSVC;

import com.kh.com.kh.web.form.FileForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileSVC {
  // 단건 파일 추가
  Long addFile(FileForm fileForm);
  // fileForm 요소 추가
  FileForm convert(MultipartFile mf, String table_info);

  Optional<FileForm> findFile(Long uploadFile_id);

  String getStoreFilename(String table_info,String storeFilename);
}
