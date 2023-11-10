package com.kh.com.kh.web.Controller;


import com.kh.com.kh.domain.dao.entity.Member;
import com.kh.com.kh.domain.svc.MemberSVC.MemberSVC;
import com.kh.com.kh.web.form.memberForm.SignupForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignupController {

  private final MemberSVC memberSVC;

  @GetMapping("/signup")
  public ModelAndView signUp(SignupForm signupForm){
    ModelAndView mv = new ModelAndView();
    mv.addObject("SignupForm",signupForm);
    mv.setViewName("webPage/Login/signup");
    return mv;
  }
  @PostMapping("/signup")
  public ModelAndView signUpForm(
      @Valid
      @ModelAttribute
      SignupForm signupForm,
      BindingResult bindingResult
  ){
    ModelAndView mv = new ModelAndView();

    if(bindingResult.hasErrors()){
      mv.setViewName("webPage/Login/signup");
      return mv;
    }

    Optional<Member> byMember = memberSVC.findByInfo(signupForm.getEmail());
    Boolean nicknameCNT = memberSVC.nickExist(signupForm.getNickname());

    if(!byMember.isEmpty()){
      bindingResult.rejectValue("email","equal",null);
      mv.setViewName("webPage/Login/signup");
      return mv;
    }
    //nicknameCNT가 1일시 참이다 1이면 존재함으로 오류 발생
    if(nicknameCNT){
      bindingResult.rejectValue("nickname","equal",null);
      mv.setViewName("webPage/Login/signup");
      return mv;
    }

    Member member = new Member();
    member.setEmail(signupForm.getEmail());
    member.setPasswd(signupForm.getPasswd());
    member.setNickname(signupForm.getNickname());
    Member insert = memberSVC.insert(member);

    mv.setViewName("webPage/Login/success");
    return mv;
  }

  @GetMapping("/success")
  public ModelAndView success(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/Login/success");
    return mv;
  }
}
