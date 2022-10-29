package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;

// 서비스의 경우 비즈니스 롤에 맞춰 네이밍 해주는 것이 좋음

//@Service
@Transactional // JPA 사용할 땐 @Transactional 사용해야함
public class MemberService {
//	private final MemberRepository memberRepository = new MemoryMemberRepository(); // 내장객체에서만 repository 사용됨 
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) { 
		this.memberRepository = memberRepository;
	}
	
	/**
	 * 회원 가입 서비스
	 * @param member
	 * @return
	 */
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	/* 중복회원 검증 */
	private void validateDuplicateMember(Member member) {
		// 같은 이름이 들어간 회원인 경우 실패 처리
		memberRepository.findByName(member.getName())
			.ifPresent(m -> { // 현재 이미 있는 경우 throw
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	/**
	 * 전체 회원 조회 서비스
	 * @return
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	/**
	 * 회원 조회 서비스
	 * @param memberId
	 * @return
	 */
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
