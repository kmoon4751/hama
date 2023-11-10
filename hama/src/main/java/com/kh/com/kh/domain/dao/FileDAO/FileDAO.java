package com.kh.com.kh.domain.dao.FileDAO;

import com.kh.com.kh.web.form.FileForm;

import java.util.Optional;

public interface FileDAO {
  Long addFile(FileForm fileForm);

  Optional<FileForm> findFile(Long uploadFile_id);
}
