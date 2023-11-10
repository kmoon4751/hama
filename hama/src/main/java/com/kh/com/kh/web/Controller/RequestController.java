package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.entity.WorkGive;
import com.kh.com.kh.domain.dao.entity.WorkGiveAll;
import com.kh.com.kh.domain.svc.RequestBoardSVC.RequestBoardSVC;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/requestBoard")
@RequiredArgsConstructor
public class RequestController {

  private final RequestBoardSVC requestBoardSVC;

  // 요청게시판
  @GetMapping
  public ModelAndView request(
      HttpServletRequest request
  ){
    ModelAndView mv = new ModelAndView();
    HttpSession session = request.getSession();
    Object useSession = session.getAttribute("sessionForm");
    SessionForm sessionForm = (SessionForm) useSession;
    Long member_id = sessionForm.getMember_id();

    HttpSession loginCheck = request.getSession(false);
    log.info("all실행여부={}","잘됨");
    List<WorkGiveAll> rqBoard = requestBoardSVC.findRQBoardAll();
    for (int i = 0; i < rqBoard.size(); i++) {
      if (rqBoard.get(i).getMember_id().equals(sessionForm.getMember_id())){
        rqBoard.remove(i);
      };
    }
    log.info("rqBoard={}",rqBoard);
    mv.addObject("rqboard",rqBoard);
    mv.addObject("loginCheck",loginCheck);
    mv.addObject("member_id",member_id);

    mv.setViewName("webPage/requestBoard/requestBoard");
    return mv;
  }

  // 요청게시판 정보전달
//  @GetMapping("/all")
//  @ResponseBody
//  public List<WorkGiveAll> requests(
//      HttpServletRequest request
//  ){
//    HttpSession session = request.getSession();
//    Object useSession = session.getAttribute("sessionForm");
//    SessionForm sessionForm = (SessionForm) useSession;
//    Long member_id = sessionForm.getMember_id();
//    log.info("all실행여부={}","잘됨");
//    List<WorkGiveAll> rqBoard = requestBoardSVC.findRQBoardAll();
//    for (int i = 0; i < rqBoard.size(); i++) {
//      if (rqBoard.get(i).getMember_id().equals(sessionForm.getMember_id())){
//        Long result = (long)0;
//        rqBoard.get(i).setMember_id(result);
//        log.info("result={}",rqBoard.get(i).getMember_id());
//      };
//
//    }
//    log.info("rqBoard={}",rqBoard);
//    return rqBoard;
//  }

//  @PostMapping("/{pid}")
//  @ResponseBody
//  public ModelAndView moveCreate(
//      @PathVariable("pid") Long pid
//  ){
//    ModelAndView mv = new ModelAndView();
//    WorkGive rqBoard = requestBoardSVC.findRQBoard(pid);
//    mv.addObject("WorkGive",rqBoard);
//    mv.setViewName("redirect:/createRQ");
//    return mv;
//  }
}

