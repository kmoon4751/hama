package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.entity.Community;
import com.kh.com.kh.domain.svc.CommunitySVC.CommunitySVC;
import com.kh.com.kh.domain.svc.MemberSVC.MemberSVC;
import com.kh.com.kh.web.form.communityForm.postForm;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

  private final CommunitySVC communitySVC;
  private final MemberSVC memberSVC;

  //글 등록 양식 호출
  @GetMapping("/add/post")
  public String questionSave(Model model){
    log.info("postForm 호출");
    model.addAttribute("postForm", new postForm());
    return "webPage/community/community_posting";
  }

  //글 submit
  @PostMapping("/add/post")
  public ModelAndView questionPost(
      postForm postForm,
      HttpSession session
      ){
    ModelAndView mv = new ModelAndView();

    log.info("postForm 호출={}", postForm);

    SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");


    Community community = new Community();
    community.setTitle(postForm.getTitle());
    community.setContent(postForm.getContent());
    community.setComu_gubun(postForm.getComu_gubun());
//    community.setMember_id(sessionForm.getMember_id());


    session.setAttribute("sessionForm", sessionForm);

    Long comu_post_id = communitySVC.savePost(community);

    log.info("id={}", community.getComu_post_id());
    log.info("title={}", postForm.getTitle());

    if( "궁금해요".equals(postForm.getComu_gubun()) ){
      mv.setViewName("webPage/community/community_question");
    }else if( "얼마예요".equals(postForm.getComu_gubun()) ){
      mv.setViewName("webPage/community/community_howMuch");
    }else{
      mv.setViewName("webPage/community/community_gethering");
    };
    return mv;
  }

  //궁금해요 초기화면
  @GetMapping("/question")
  public ModelAndView question(){
      ModelAndView mv = new ModelAndView();
      mv.setViewName("webPage/community/community_question");
      return mv;
  }
  //모든 게시글 조회
  @GetMapping("/question/all")
  @ResponseBody
  public com.kh.com.kh.domain.svc.AedSVC.ApiResponse<List<Community>> questionAll(){
    List<Community> communities = communitySVC.questionAll();
    com.kh.com.kh.domain.svc.AedSVC.ApiResponse<List<Community>> result = null;
    if(communities.isEmpty()){
      result = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("01","실패",null);
    }else{
      result = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("00", "성공", communities);
    }
    return result;
  }

  //얼마예요 초기화면
  @GetMapping("/howmuch")
  public ModelAndView howMuch(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/community/community_howMuch");
    return mv;
  }
  @GetMapping("/howmuch/all")
  @ResponseBody
  public com.kh.com.kh.domain.svc.AedSVC.ApiResponse<List<Community>> howMuchAll(){
    List<Community> communities = communitySVC.howMuchAll();
    com.kh.com.kh.domain.svc.AedSVC.ApiResponse<List<Community>> result = null;
    if(communities.isEmpty()){
      result = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("01","실패",null);
    }else{
      result = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("00", "성공", communities);
    }
    return result;
  }

  //모여봐요 초기화면
  @GetMapping("/gethering")
  public ModelAndView gethering(){
    ModelAndView mv = new ModelAndView();
    mv.setViewName("webPage/community/community_gethering");
    return mv;
  }
  @GetMapping("/gethering/all")
  @ResponseBody
  public com.kh.com.kh.domain.svc.AedSVC.ApiResponse<List<Community>> getheringAll(){
    List<Community> communities = communitySVC.getheringAll();
    com.kh.com.kh.domain.svc.AedSVC.ApiResponse<List<Community>> result = null;
    if(communities.isEmpty()){
      result = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("01","실패",null);
    }else{
      result = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("00", "성공", communities);
    }
    return result;
  }

  //글 삭제
//  @ResponseBody
//  @DeleteMapping("/gethering/{comu_post_id}")
//  public ApiResponse<String> delete(@PathVariable("comu_post_id") Long comu_post_id){
//    ApiResponse<String> result = null;
//
//    int row = communitySVC.deleteById(comu_post_id);
//    if(row == 1){
//      result = ApiResponse.createApiResponse("00", "성공", null);
//    }else {
//      result = ApiResponse.createApiResponse("01","실패", null);
//    }
//    return result;
//  }
}
