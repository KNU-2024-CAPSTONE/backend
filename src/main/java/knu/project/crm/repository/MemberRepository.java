package knu.project.crm.repository;

import knu.project.crm.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);  // 이메일로 회원 조회
}
