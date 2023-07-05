package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 x
        Optional<Member> result = memberRepository.findByName(member.getName());
        // ifPresent : Null이 아니라 어떤 같이 있으면 동작한다. Optional이여서 가능.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        // 이런식으로도 작성할 수 있음
        // memberRepository.findByName(member.getName())
        //     .ifPresent(m -> {
        //         throw new IllegalStateException("이미 존재하는 회원입니다.");
        //     });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
