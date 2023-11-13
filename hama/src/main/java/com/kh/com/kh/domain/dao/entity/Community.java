package com.kh.com.kh.domain.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Community {
  private Long comu_post_id ;   // 게시글 아이디 PK
  private String comu_gubun;    // '궁금해요' / '얼마예요' / '모여봐요'
  private String title;        // 글 제목
  private String content;       // 글 본문
  private Long member_id;       // 글을 작성한 멤버의 ID / fk
  private String area;          // 지역
  private byte[] board_pic;     // 본문에 첨부된 사진
  private LocalDateTime cdate;  // 글 생성 일시
  private LocalDateTime udate;  // 글 수정 일시  -> 필요한가요?
}
