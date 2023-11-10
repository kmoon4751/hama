package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.entity.WorkGive;
import com.kh.com.kh.domain.svc.EstimateSVC.EstimateSVC;
import com.kh.com.kh.domain.svc.RequestBoardSVC.RequestBoardSVC;
import com.kh.com.kh.web.form.estimateForm.EstimateForm;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/createRQ")
public class EstimateController {

  private final RequestBoardSVC requestBoardSVC;
  private final EstimateSVC estimateSVC;

  @GetMapping("/{pid}")
  public ModelAndView createRq(
      @PathVariable("pid") Long pid,
      EstimateForm estimateForm
  ){
    ModelAndView mv = new ModelAndView();
    WorkGive createRQ = requestBoardSVC.findRQBoard(pid);
    log.info("createRQ={}",createRQ);
    mv.setViewName("webPage/requestBoard/estimate");
    mv.addObject("workgive",createRQ);
    mv.addObject("estimateForm",estimateForm);
    return mv;
  }



  @PostMapping("/{pid}")
  public ModelAndView estimate(
    @PathVariable("pid") Long pid,
    EstimateForm estimateForm,
    HttpServletRequest request
  ){
    ModelAndView mv = new ModelAndView();
    HttpSession session = request.getSession();
    Object useSession = session.getAttribute("sessionForm");
    SessionForm sessionForm = (SessionForm) useSession;

    estimateForm.setWork_member_id(sessionForm.getMember_id());
    estimateForm.setBoard_id(pid);

    Long result = estimateSVC.estimateInsert(estimateForm);
    log.info("estimateForm={}",estimateForm);
    mv.setViewName("redirect:/postEs");
    return mv;
  }
}
