package com.kh.com.kh.domain.svc.FileSVC;

import com.kh.com.kh.domain.dao.FileDAO.FileDAO;
import com.kh.com.kh.web.form.FileForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileSVCIm implements FileSVC{

  @Value("${files.root_dir}") // d:/fileBox/
  private String ROOT_DIR;

  private final FileDAO fileDAO;

  @Override
  public Long addFile(FileForm fileForm) {
    FileForm fileFormEnd = convert(fileForm.getImageFiles(),"회원사진");
    fileFormEnd.setTable_id(fileForm.getTable_id());

    return fileDAO.addFile(fileFormEnd);
  }

  @Override
  public Optional<FileForm> findFile(Long uploadFile_id) {
    return fileDAO.findFile(uploadFile_id);
  }

  // ------ FileForm 에 담길 요소들 정리
  @Override
  public FileForm convert(MultipartFile mf, String table_info) {
    if (mf.isEmpty())return null;
    FileForm fileForm = new FileForm();
    fileForm.setTable_info(table_info);
    fileForm.setUpload_filename(mf.getOriginalFilename()); // 올린 파일의 이름
    // 참조용 고유의 파일명?
    String storeFileName = createStoreFilename(mf.getOriginalFilename());
    fileForm.setStore_filename(storeFileName);
    fileForm.setFsize(String.valueOf(mf.getSize()));
    fileForm.setFtype(mf.getContentType());

    storeFile(mf,table_info,storeFileName);
    return fileForm;
  }

  //임의파일명 생성
  private String createStoreFilename(String originalFile) {
    StringBuffer storeFileName = new StringBuffer();
    storeFileName.append(UUID.randomUUID().toString()) // xxx-yyy-zzz-ttt..
        .append(".")
        .append(extractExt(originalFile)); // png,jpg
    return storeFileName.toString();
  }

  //확장자 추출
  private String extractExt(String originalFile) {
    int posOfExt =originalFile.lastIndexOf(".");
    String ext = originalFile.substring(posOfExt + 1);
    return ext;
  }
// 파일 내 경로에 저장
  public void storeFile(MultipartFile mf, String table_info ,String storeFilename) {
    String fullPath = getFullPath(table_info);
    Path path = Paths.get(fullPath, storeFilename);

    try {
      mf.transferTo(path);  // 디렉토리에 저장
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 경로 생성
  public String getFullPath(String table_info) {
    StringBuffer path = new StringBuffer();
    path = path.append(ROOT_DIR).append(table_info).append("/"); // d:/attach/분류코드/

    //경로가 없으면 생성성
    createFolder(path.toString());
    return path.toString();
  }

  @Override
  public String getStoreFilename(String table_info,String storeFilename) {
    StringBuffer storedFileFullPath = new StringBuffer();
    storedFileFullPath
        //  d:/attach/분류코드/
        .append(getFullPath(table_info))
        //  d:/attach/분류코드/xxx-yyy-xxx-uuu.png
        .append(storeFilename);
    log.info("storedFileFullPath={}",storedFileFullPath.toString());
    return storedFileFullPath.toString();
  }


  //분류코드 폴더 생성
  private void createFolder(String folder) {

    Path path = Paths.get(folder);

    if (!Files.exists(path)) {
      try {
        Files.createDirectories(path);
        log.info("폴더생성={}",path.toString());
      } catch (IOException e) {
        log.info("폴더생성실패={}", e.getMessage());
      }
    }
  }
}
