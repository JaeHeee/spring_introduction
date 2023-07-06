package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    /// 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap 사용 고려
    private static Map<Long, Member> store = new HashMap<>();
    /// 0, 1, 2... 키값을 생성해준다. 실무에서는 동시성 문제를 고려해서 AtomicLong 사용 고려
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        /// 루프를 돌린다. findAny는 하나라도 찾는것. 없으면 Optional에 null이 포함되어서 반환됨.
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
