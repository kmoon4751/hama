package com.kh.com.kh.domain.dao.CommunityDAO;

import com.kh.com.kh.domain.dao.entity.Community;

import java.util.List;
import java.util.Optional;

public interface CommunityDAO {

  //게시글 등록
  Long savePost(Community community);

  //궁금해요 게시글 목록
  List<Community> questionAll();

  //얼마예요 게시글 목록
  List<Community> howMuchAll();

  //모여봐요 게시글 목록
  List<Community> getheringAll();

  //게시글 수정
  int updateById(Long member_id, Community community);

  //게시글 삭제
  int deleteById(Long comu_post_id);

  //게시글 검색
  Optional<Community> findByTitle(String title);

}
