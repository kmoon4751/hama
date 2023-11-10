package com.kh.com.kh.web.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/mapApi")
public class MapApiController {

  @GetMapping
  public ModelAndView mapApi(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/mapApi/mapApi");
    return mv;
  }

  @GetMapping("/aed")
  public ModelAndView aedApi(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/mapApi/aed");
    return mv;
  }
}
