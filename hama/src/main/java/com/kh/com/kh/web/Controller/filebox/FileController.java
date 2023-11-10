package com.kh.com.kh.web.Controller.filebox;

import com.kh.com.kh.domain.dao.FileDAO.FileDAOIm;
import com.kh.com.kh.domain.svc.FileSVC.FileSVC;
import com.kh.com.kh.web.exception.BizException;
import com.kh.com.kh.web.form.FileForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileController {

  private final FileSVC fileSVC;

  @GetMapping("file")
  public ModelAndView testFile(
      FileForm fileTest
  ){
    ModelAndView mv = new ModelAndView();
    mv.addObject("fileTest",fileTest);
    mv.setViewName("filetest/filetest");
    return mv;
  }

  //이미지 뷰
  @GetMapping("/view/{fid}")   //  http://localhost:9080/attach/view/231321
  public ResponseEntity<Resource> viewAttach(
      @PathVariable("fid") Long uploadfileId
  )throws MalformedURLException  {
    Optional<FileForm> file = fileSVC.findFile(uploadfileId);
    if (file.isEmpty())new BizException("첨부파일을 찾을 수 없습니다.");
    FileForm fileForm = new FileForm();
    fileForm = file.get();
    String storeFilename = fileSVC.getStoreFilename(file.get().getTable_info(),file.get().getStore_filename());
    Resource resource = new UrlResource("file:"+storeFilename);
    log.info("resource={}",resource);
    //한글파일명 깨짐 방지를위한 인코딩
    String encodeUploadFileName = UriUtils.encode(file.get().getUpload_filename(), StandardCharsets.UTF_8);
    //Http응답 메세지 헤더에 첨부파일이 있음을 알림
    String contentDisposition = "attachment; inline="+ encodeUploadFileName;

    return ResponseEntity.ok()  //응답코드 200
        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
        .body(resource);
  }
}