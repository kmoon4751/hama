package com.kh.com.kh.domain.svc.CommunitySVC;

import com.kh.com.kh.domain.dao.entity.Community;

import java.util.List;

public interface CommunitySVC {

  //게시글 등록
  Long savePost(Community community);

  //궁금해요 게시글 목록
  List<Community> questionAll();

  //얼마예요 게시글 목록
  List<Community> howMuchAll();

  //모여봐요 게시글 목록
  List<Community> getheringAll();

  int deleteById(Long comu_post_id);

}
