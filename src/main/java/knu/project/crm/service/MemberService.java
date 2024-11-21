package knu.project.crm.service;

import knu.project.crm.entity.Member;
import knu.project.crm.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);  // 이메일로 회원 조회
    }
}
