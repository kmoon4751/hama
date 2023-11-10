package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.entity.Member;
import com.kh.com.kh.domain.svc.FileSVC.FileSVC;
import com.kh.com.kh.domain.svc.MemberSVC.MemberSVC;
import com.kh.com.kh.domain.svc.MyPageSVC.MyPageSVC;
import com.kh.com.kh.web.form.FileForm;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import com.kh.com.kh.web.form.userupdateForm.UpdateCKForm;
import com.kh.com.kh.web.form.userupdateForm.UpdateForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/update")
@RequiredArgsConstructor
public class UserUpdateConteroller {

private final MyPageSVC myPageSVC;
private final FileSVC fileSVC;

  @GetMapping
  public ModelAndView update(
    UpdateCKForm updateCKForm,
    FileForm fileForm,
    HttpSession session
  ){
    SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
    Long pic = sessionForm.getPic();
    log.info("pic={}",pic);
    ModelAndView mv = new ModelAndView();
    mv.addObject("fileForm", fileForm);
    mv.addObject("updateCKForm",updateCKForm);
    mv.setViewName("webPage/myPage/cus_update");
    return mv;
  }
  // 회원 정보 수정
  @PostMapping
  public ModelAndView updateForm(
      UpdateCKForm updateCKForm,
      HttpSession session
  ){
    ModelAndView mv = new ModelAndView();
    SessionForm sessionInfo = (SessionForm) session.getAttribute("sessionForm");
    log.info("sessionMember",sessionInfo.getMember_id());
    log.info("updateCK={}",updateCKForm);

    UpdateForm updateForm = new UpdateForm();
    updateForm.setMember_id(sessionInfo.getMember_id());
    updateForm.setNickname(sessionInfo.getNickname());
    updateForm.setPasswd(sessionInfo.getPasswd());
    updateForm.setTel(sessionInfo.getTel());

    if(updateCKForm.getNickname() != null){
      updateForm.setNickname(updateCKForm.getNickname());
    } else if(updateCKForm.getTel() != null){
      updateForm.setTel(updateCKForm.getTel());
    } else {
      updateForm.setPasswd(updateCKForm.getPasswd());
    }

    log.info("updateFormLAST={}",updateForm);
    Long updateCnt = myPageSVC.updateCnt(updateForm);
    log.info("updateFormLAST={}",updateCnt);

    SessionForm sessionForm = new SessionForm(
        updateForm.getMember_id(), sessionInfo.getEmail(), updateForm.getNickname(),sessionInfo.getGubun(),
        updateForm.getTel(),updateForm.getPic(),updateForm.getPasswd()
    );
    session.setAttribute("sessionForm",sessionForm);
    mv.addObject("updateCKForm",sessionForm);
    mv.setViewName("redirect:/update");
    return mv;
  }


// 회원 사진관련
// private Long member_id;
//  private Long estimate_id;
//
//  private Long uploadfileId;
//  private String table_info;
//  private Long table_id;
//  private String store_filename;
//  private String upload_filename;
//  private String fsize;
//  private String ftype;
//
//  private List<MultipartFile> attachFiles;    //설명 파일
//  private List<MultipartFile> imageFiles;    //이미지 첨부

  @PostMapping("/pic/add")
  public ModelAndView addPicForm(
      FileForm fileForm,
      HttpSession session
  ){
    ModelAndView mv = new ModelAndView();
    log.info("updateCK={}",fileForm);
    fileForm.setTable_id(fileForm.getMember_id());
    Long result = fileSVC.addFile(fileForm);
    SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
    sessionForm.setPic(result);
    session.setAttribute("sessionForm",sessionForm);
    log.info("update/delete={}",result);
    mv.setViewName("webPage/myPage/cus_update");
    return mv;
  }

  @PostMapping("/pic")
  public ModelAndView updatePicForm(
      FileForm fileForm
  ){
    ModelAndView mv = new ModelAndView();
    log.info("updateCK={}",fileForm);
    log.info("update/delete={}","호출됨!");
    mv.setViewName("webPage/myPage/cus_update");
    return mv;
  }
  @PostMapping("/pic/delete")
  public ModelAndView deletePicForm(
      FileForm fileForm,
      HttpSession session
  ){
    ModelAndView mv = new ModelAndView();
    Long result = myPageSVC.deletePic(fileForm.getMember_id());
      log.info("={}","삭제 업데이트 성공");
      SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
      sessionForm.setPic(null);
      session.setAttribute("sessionForm",sessionForm);
    log.info("updateCK={}",fileForm);
    log.info("update/delete={}","호출됨!");
    mv.setViewName("webPage/myPage/cus_update");
    return mv;
  }
}
