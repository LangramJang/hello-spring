package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;
import hello.hellospring.domain.Member;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id); // Optional(java 8) : null 관련하여 반환을 위해
	Optional<Member> findByName(String name); 
	List<Member> findAll();
//	void clearStore();
}
