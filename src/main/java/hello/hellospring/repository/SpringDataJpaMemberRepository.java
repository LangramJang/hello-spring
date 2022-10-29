package hello.hellospring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import hello.hellospring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
	
	@Override // Spring JPA > 구현체를 자동으로 생성(bean), 구현 필요없이 자동으로 생성됨(가져다쓰기만 하면 됨)
	Optional<Member> findByName(String name); // JPQL 로 자동으로 짜줌
	
}
