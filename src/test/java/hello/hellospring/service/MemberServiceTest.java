package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {
	// Test 할 때는 같은 Repository(DB)를 사용해야함
//	MemberService memberService = new MemberService();
//	MemberRepository memberRepository = new MemoryMemberRepository(); 
	
	MemberService memberService;
	MemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		MemberRepository memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
//	@AfterEach
//	public void afterEach() {
//		memberRepository.clearStore();
//	}
	
	// Test 코드는 given-when-then 으로 대부분 이루어짐
	@Test
	void 회원가입() { // 테스트코드는 한글로 이름 붙여도 괜찮음, 실제 코드에 포함되지않음
		// given
		Member member = new Member();
		member.setName("except");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외처리() {
		// given
		Member member1 = new Member();
		member1.setName("except");
		
		Member member2 = new Member();
		member2.setName("except");
		
		// when
		memberService.join(member1);
		// Case 1
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // success case
//		assertThat(e.getMessage()).isEqualTo("Fail"); // fail case
		
		// Case 2
/*		try {
			memberService.join(member2); // Exception!!!
			fail("예외가 발생해야합니다!!!");
		} catch(IllegalStateException e) {
			// Success (예외 발생이 맞는 케이스이므로)
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // success case
//			assertThat(e.getMessage()).isEqualTo("Fail"); // fail case
		}  */
		
		// than
		
	}
}
