package com.kh.com.kh.web.Controller.postbox;


import com.kh.com.kh.domain.svc.PostBoxsSVC.RequestBoxSVC.RequestBoxSVC;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import com.kh.com.kh.web.form.requestForm.RequestAllForm;
import com.kh.com.kh.web.form.requestForm.RequestForm;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class RequestBoxController {

  private final RequestBoxSVC requestBoxSVC;

  @GetMapping("/postRq")
  public ModelAndView postRq(
      HttpSession session,
      RequestForm requestForm
  ){
    ModelAndView mv = new ModelAndView();
    SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
    List<RequestAllForm> allRq = requestBoxSVC.findAllRq(sessionForm.getMember_id());
    log.info("allRq={}",allRq);

    mv.addObject("requestForm",requestForm);
    mv.addObject("allRq",allRq);
    mv.setViewName("webPage/postboxs/request/requestPost");
    return mv;
  }

  @PostMapping("/postRq")
  public ModelAndView postDelRq(
      RequestForm requestForm
  ){
    ModelAndView mv = new ModelAndView();
    Long result = requestBoxSVC.delRQ(requestForm.getBoard_Id());

    mv.setViewName("redirect:/postRq");
    return mv;
  }

  @GetMapping("/getRq")
  public ModelAndView getRq(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/postboxs/request/requestGet");
    return mv;
  }
}
