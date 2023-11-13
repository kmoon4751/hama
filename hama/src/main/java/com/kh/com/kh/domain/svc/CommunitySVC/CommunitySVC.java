package com.kh.com.kh.domain.svc.CommunitySVC;

import com.kh.com.kh.domain.dao.entity.Community;

import java.util.List;
import java.util.Optional;

public interface CommunitySVC {

  //게시글 등록
  Long savePost(Community community);

  //궁금해요 게시글 목록
  List<Community> questionAll();

  //얼마예요 게시글 목록
  List<Community> howMuchAll();

  //모여봐요 게시글 목록
  List<Community> getheringAll();

  //게시글 상세 조회
  Optional<Community> viewById(Long comu_post_id);

  int deleteById(Long comu_post_id);

}
