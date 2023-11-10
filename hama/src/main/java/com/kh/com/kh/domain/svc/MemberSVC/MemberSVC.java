package com.kh.com.kh.domain.svc.MemberSVC;

import com.kh.com.kh.domain.dao.entity.Member;

import java.util.Optional;

public interface MemberSVC {
  Optional <Member> findByInfo(String email);

  Boolean nickExist (String nickname);

  Member insert(Member member);

}
