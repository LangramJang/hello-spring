package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import hello.hellospring.domain.Member;

public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// PK기반은 find 로 찾으면됨(단일로 출력되므로)
		Member member = em.find(Member.class, id); 
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		// PK 기반이 없는 find query는 JPQL 문법을 사용해야됨
		List<Member> result = em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// JPQL("SELECT m FROM Member m") : 객체를 대상으로 Query를 날려줌
		return em.createQuery("SELECT m FROM Member m", Member.class)
				.getResultList();
		
	}


}
