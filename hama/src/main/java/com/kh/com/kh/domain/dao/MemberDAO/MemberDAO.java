package com.kh.com.kh.domain.dao.MemberDAO;

import com.kh.com.kh.domain.dao.entity.Member;

import java.util.Optional;

public interface MemberDAO {
  Optional<Member> findByInfo (String email);

  Boolean nickExist (String nickname);

  Member insert(Member member);
}
