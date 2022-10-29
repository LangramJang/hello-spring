package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@SpringBootTest // 스프링 부트와 연결시켜주는 어노테이션
@Transactional  // 한번 돌리고 나서 중복 제거해주는 테스트
//@Rollback
class MemberServiceIntegrationTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	// Test 코드는 given-when-then 으로 대부분 이루어짐
	@Test
	public void 회원가입(){ // 테스트코드는 한글로 이름 붙여도 괜찮음, 실제 코드에 포함되지않음
		System.out.println(memberService.findMembers());
		
		// given
		Member member = new Member();
		member.setName("spring");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외처리(){
		System.out.println(memberService.findMembers());
		// given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		Member member3 = new Member();
		member3.setName("xxxxxx");
		
		// when
		memberService.join(member1);
		memberService.join(member3);

		// then
		// Case 1
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // success case
//		assertThat(e.getMessage()).isEqualTo("Fail"); // fail case

/*
		// Case 2
		try {
			memberService.join(member2); // Exception!!!
			fail("예외가 발생해야합니다!!!");
		} catch(IllegalStateException e) {
			// Success (예외 발생이 맞는 케이스이므로)
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // success case
//			assertThat(e.getMessage()).isEqualTo("Fail"); // fail case
		}  
*/	
	}
}
