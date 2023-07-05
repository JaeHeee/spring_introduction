package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    /// Optional은 find했을 때 값이 없는 경우에 Null 대신 사용함
    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();
}