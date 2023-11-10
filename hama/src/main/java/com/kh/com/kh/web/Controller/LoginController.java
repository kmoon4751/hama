package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.entity.Member;
import com.kh.com.kh.domain.svc.MemberSVC.MemberSVC;
import com.kh.com.kh.web.form.memberForm.LoginForm;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
// MemberSVC 참조
  private final MemberSVC memberSVC;

// 로그인 창 불러오기
  @GetMapping("login")
  public ModelAndView login(LoginForm loginForm){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/Login/login");
    mv.addObject("loginForm",loginForm);
    return mv;
  }

  // 1. RequestParam 으로 Post 매개변수를 redirectURL에 담음
  @PostMapping("login")
  public ModelAndView loginForm(
//      @RequestParam(value = "redirectUrl", required = false, defaultValue = "/") String redirectUrl,
      @Valid
      @ModelAttribute
      HttpSession session,
      LoginForm loginForm,
      BindingResult bindingResult,
      HttpServletRequest request
  ){
    ModelAndView mv = new ModelAndView();
    Optional<Member> optionalMember = memberSVC.findByInfo(loginForm.getEmail());
    Member member = null;

    if(bindingResult.hasErrors()){
      mv.setViewName("webPage/Login/login");
      return mv;
    }

    if(optionalMember.isEmpty()){
      bindingResult.reject("invalidMember",null);
    }else{
      member = optionalMember.get();
      if(!member.getPasswd().equals(loginForm.getPasswd())){
        bindingResult.reject("invalidMember",null);
      }
    }

    if(bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      mv.setViewName("webPage/Login/login");
      return mv;
    }

    HttpSession httpSession = request.getSession(true);
    Long memberId = (Long) member.getMember_id();
    SessionForm sessionForm = new SessionForm(
        member.getMember_id(), member.getEmail(), member.getNickname(),member.getGubun(),
        member.getTel(),member.getPic(),member.getPasswd()
    );
    httpSession.setAttribute("sessionForm",sessionForm);
    httpSession.setAttribute("s_memberid",member.getMember_id());
    mv.setViewName("redirect:/");
    mv.addObject("loginForm",loginForm);
    return mv;
  }

  @GetMapping("/logout")
  public ModelAndView logout(HttpServletRequest request){
    ModelAndView mv = new ModelAndView();
    // 세션갑 체크
    HttpSession httpSession = request.getSession(false);
    // session 삭제
    httpSession.invalidate();
    mv.setViewName("webPage/indexs/index");
    return mv;
  }
}
