package com.kh.com.kh.web.Controller;

import com.kh.com.kh.domain.dao.entity.Community;
import com.kh.com.kh.domain.svc.ApiResponse;
import com.kh.com.kh.domain.svc.CommunitySVC.CommunitySVC;
import com.kh.com.kh.domain.svc.MemberSVC.MemberSVC;
import com.kh.com.kh.web.form.communityForm.PostForm;
import com.kh.com.kh.web.form.memberForm.SessionForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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
    model.addAttribute("postForm", new PostForm());
    return "webPage/community/community_posting";
  }

  //글 submit
  @PostMapping("/add/post")
  public ModelAndView questionPost(
      PostForm postForm,
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
  public ApiResponse<List<Community>> questionAll(){
    List<Community> communities = communitySVC.questionAll();
    ApiResponse<List<Community>> result = null;
    if(communities.isEmpty()){
      result = ApiResponse.createApiResponse("01","실패",null);
    }else{
      result = ApiResponse.createApiResponse("00", "성공", communities);
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
  public ApiResponse<List<Community>> howMuchAll(){
    List<Community> communities = communitySVC.howMuchAll();
    ApiResponse<List<Community>> result = null;
    if(communities.isEmpty()){
      result = ApiResponse.createApiResponse("01","실패",null);
    }else{
      result = ApiResponse.createApiResponse("00", "성공", communities);
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
  public ApiResponse<List<Community>> getheringAll(){
    List<Community> communities = communitySVC.getheringAll();
    ApiResponse<List<Community>> result = null;
    if(communities.isEmpty()){
      result = ApiResponse.createApiResponse("01","실패",null);
    }else{
      result = ApiResponse.createApiResponse("00", "성공", communities);
    }
    return result;
  }

    //게시글 상세조회
    @GetMapping("/view/{comu_post_id}")
    public String viewById( Model model, @PathVariable Long comu_post_id){
      Optional<Community> community = communitySVC.viewById(comu_post_id);
      if (community.isPresent()) {
        model.addAttribute("community", community.get());
        model.addAttribute("comu_post_id",comu_post_id);
        return "webPage/community/community_detail";
      } else {
        return "error/not_found";
      }
    }

    //수정폼 호출
    //글수정 폼 불러오기
    @GetMapping("modify/{comu_post_id}")
    public String communityModify(@PathVariable("comu_post_id") Long comu_post_id, Model model){
      Optional<Community> communityOptional = communitySVC.viewById(comu_post_id);

      if (communityOptional.isPresent()) {
        model.addAttribute("community", communityOptional.get());
        return "webPage/community/community_modify";
      } else {
        return "error/not_found";
      }
    }

  @PostMapping("/modify/{comu_post_id}")
  public ModelAndView updateById(
      @PathVariable("comu_post_id") Long comu_post_id,
      @Valid @ModelAttribute PostForm postForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ){
    ModelAndView mv = new ModelAndView();

    redirectAttributes.addAttribute("comu_post_id",comu_post_id);

    if(bindingResult.hasErrors()){
      mv.setViewName("redirect:/community/view/{comu_post_id}");
      return mv;
    }

    //DB에서 데이터 가져오기
    Optional<Community> communityOptional = communitySVC.viewById(comu_post_id);

    if(communityOptional.isPresent()){
      Community community = communityOptional.get();

      // 수정된 내용 저장
      community.setTitle(postForm.getTitle());
      community.setContent(postForm.getContent());
      community.setComu_gubun(postForm.getComu_gubun());

      communitySVC.updateById(comu_post_id, community);

      log.info("수정내용={}", postForm);
    }else {
      mv.setViewName("redirect:/error/not_found");
      return mv;
    }

    log.info("수정내용={}",postForm);
    mv.setViewName("redirect:/community/view/{comu_post_id}");
    return mv;
  }


  //삭제
  @PostMapping("/view/{comu_post_id}")
  public ModelAndView deleteById(@PathVariable Long comu_post_id){
    ModelAndView mv = new ModelAndView();
    Community community = communitySVC.deleteById(comu_post_id);

    String comuGubun = community.getComu_gubun();

    if( "궁금해요".equals(comuGubun) ){
      mv.setViewName("redirect:/community/question");
      return mv;
    }else if( "얼마예요".equals(comuGubun) ){
      mv.setViewName("redirect:/community/howmuch");
      return mv;
    }else{
      mv.setViewName("redirect:/community/gethering");
      return mv;
    }
  }


  //  글 삭제
//  @ResponseBody
//  @DeleteMapping("/delete/{comu_post_id}")
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


//  public  String viewById(
//    @PathVariable("comu_post_id") Long comu_post_id,
//    Model model){
//    //
//    Optional<Community> optionalCommunity = communitySVC.viewById(comu_post_id);
//    Community community = optionalCommunity.orElseThrow(); //값있으면 가져오고 없으면 말고
//
//    comu_postDetailForm comu_postDetailForm = new comu_postDetailForm();

//    comu_postDetailForm.setComu_post_id(community.getComu_post_id());
//    comu_postDetailForm.setTitle(community.getTitle());
//    comu_postDetailForm.setContent(community.getContent());
//
//    model.addAttribute("comu_postDetailForm", comu_postDetailForm);
//    return "webPage/community/community_detail";
//  }


//  @ResponseBody
//  @GetMapping("/{comu_post_id}")
//  public com.kh.com.kh.domain.svc.AedSVC.ApiResponse<Community> viewById(@PathVariable("comu_post_id") Long comu_post_id){
//    com.kh.com.kh.domain.svc.AedSVC.ApiResponse<Community> response = null;
//    Optional<Community> optionalCommunity = communitySVC.viewById(comu_post_id);
//
//    Community findIdforView = null;
//    if(optionalCommunity.isPresent()){
//      findIdforView = optionalCommunity.get();
//      response = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("00", "성공", findIdforView);
//    }else{
//      response = com.kh.com.kh.domain.svc.AedSVC.ApiResponse.createApiResponse("01", "실패", null);
//    }
//    return response;
//  }


}
