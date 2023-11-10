package com.kh.com.kh.web.Controller;


import com.kh.com.kh.domain.svc.WorkGiveSVC.WorkGiveSVC;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import com.kh.com.kh.web.form.workgiveForm.FetchForm;
import com.kh.com.kh.web.form.workgiveForm.GiveForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class WorkGiveController {

  private final WorkGiveSVC workGiveSVC;

  @GetMapping("/give")
  public ModelAndView give(
      HttpServletRequest request
  ){
    HttpSession loginCheck = request.getSession(false);
    ModelAndView mv = new ModelAndView();
    mv.addObject("loginCheck",loginCheck);
    mv.setViewName("webPage/workgive/workgive");
    return mv;
  }

  @GetMapping("/give/detail")
  public ModelAndView giveDetail(
    HttpServletRequest request,
    FetchForm fetchForm
  ){
    ModelAndView mv = new ModelAndView();
    HttpSession loginCheck = request.getSession(false);
    mv.addObject("loginCheck",loginCheck);
    mv.addObject("fetchForm",fetchForm);
    mv.setViewName("webPage/workgive/workgive_detail2");
    return mv;
  }

//  @PostMapping("/detail")
//  public ModelAndView giveDetailForm(
//      @RequestBody FetchForm fetchForm,
//      HttpServletRequest request
//      ){
//    ModelAndView mv = new ModelAndView();
//    // 세션을 검색해서 Object로 변환 후 사용
//    HttpSession session = request.getSession();
//    Object useSession = session.getAttribute("sessionForm");
//    SessionForm sessionForm = (SessionForm) useSession;
//
//    GiveForm giveForm = new GiveForm();
//    giveForm.setHope_date(fetchForm.getDate().replaceAll("[\\n\\s]",""));
//    giveForm.setArea(fetchForm.getLocation());
//    giveForm.setCategory(fetchForm.getCategory());
//    giveForm.setHope_text(fetchForm.getText());
//    giveForm.setMember_id(sessionForm.getMember_id());
//    giveForm.setNickname(sessionForm.getNickname());
//    log.info("GiveForm={}",giveForm);
//    log.info("session={}",useSession);
//
////    Long insert = workGiveSVC.giveInsert(giveForm);
////    log.info("insert={}",insert);
//
//    mv.setViewName("webPage/workgive/wg_success");
//    return mv;
//  }

  @PostMapping("/give/detail")
  public ModelAndView giveDetailForm(
      FetchForm fetchForm,
      HttpServletRequest request
  ){
    ModelAndView mv = new ModelAndView();
    // 세션을 검색해서 Object로 변환 후 사용
    HttpSession session = request.getSession();
    Object useSession = session.getAttribute("sessionForm");
    SessionForm sessionForm = (SessionForm) useSession;


    GiveForm giveForm = new GiveForm();
    giveForm.setHope_date(fetchForm.getDate().replaceAll("[\\n\\s]",""));
    giveForm.setArea(fetchForm.getLocation());
    giveForm.setCategory(fetchForm.getCategory());
    giveForm.setHope_text(fetchForm.getText());
    giveForm.setMember_id(sessionForm.getMember_id());
    giveForm.setNickname(sessionForm.getNickname());
    log.info("GiveForm={}",giveForm);
    log.info("session={}",useSession);

    Long insert = workGiveSVC.giveInsert(giveForm);
    log.info("insert={}",insert);

    mv.setViewName("redirect:/postRq");
    return mv;
  }

  @GetMapping("/detail/result")
  public ModelAndView giveDetailsuccess(
    HttpServletRequest request
  ){
    ModelAndView mv = new ModelAndView();
    HttpSession loginCheck = request.getSession(false);
    mv.addObject("loginCheck",loginCheck);
    mv.setViewName("webPage/workgive/wg_success");
    return mv;
  }
}
