package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.svc.ComentSVC.ComentSVC;
import com.kh.com.kh.web.form.ComentForm.ComentAddForm;
import com.kh.com.kh.web.form.ComentForm.ComentForm;
import com.kh.com.kh.web.form.ComentForm.ComentListForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

  private final ComentSVC comentSVC;

  @GetMapping("/{eid}")
  public ModelAndView chat(
      @PathVariable("eid") Long estimate_id,
      ComentAddForm comentAddForm
  ){
    ModelAndView mv = new ModelAndView();

    // 교류창 견적서 정보
    ComentForm comentForm = comentSVC.comentInfo(estimate_id);
    log.info("comentForm={}",comentForm);
    // addForm 모델생성
    mv.addObject("comentAddForm",comentAddForm);
    mv.addObject("eid",estimate_id);
    // 견적서 내용 모델생성
    mv.addObject("estiForm",comentForm);

    // 댓글 목록
    List<ComentListForm> comentListForms = comentSVC.comentAll(estimate_id);
    // 댓글리스트 모델생성
    mv.addObject("comentList",comentListForms);
    log.info("comentAll={}",comentListForms);
    mv.setViewName("webPage/chatting/coment");
    return mv;
  }

  @PostMapping("/{eid}")
  public ModelAndView chatInsert(
      @PathVariable("eid") Long estimate_id,
      ComentAddForm comentAddForm,
      RedirectAttributes redirectAttributes
  ){
    log.info("coment estimate_id={}",estimate_id);
    ModelAndView mv = new ModelAndView();
    comentAddForm.setEstimate_id(estimate_id);
    Long result = comentSVC.comnentAdd(comentAddForm);
    redirectAttributes.addAttribute("eid",estimate_id);
    mv.setViewName("redirect:/chat/{eid}");
    return mv;
  }

  @PostMapping("/delete/{eid}")
  public ModelAndView deleteComent(
      @PathVariable("eid") Long eid,
      RedirectAttributes redirectAttributes,
      ComentListForm comentListForm
  ){
    ModelAndView mv = new ModelAndView();
    log.info("deleid_id={}",eid);
    log.info("delcoment_id={}",comentListForm.getComent_id());
    Long result = comentSVC.comnentDel(comentListForm.getComent_id());
    redirectAttributes.addAttribute("eid", eid);
    mv.setViewName("redirect:/chat/{eid}");
    return mv;
  }
}
