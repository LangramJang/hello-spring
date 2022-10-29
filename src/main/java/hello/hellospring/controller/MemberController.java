package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller // 해당 Annotation 있으면, Spring Container에서 실행할 때 bean을 자동으로 등록해 줌
public class MemberController {
	

	// CASE1: 생성자 주입 (권장)
	private final MemberService memberService;
	
	@Autowired // Service와 Controller를 자동으로 연결시켜준다 / Dependency Injection, 서로 의존성을 주입(bean 등록)해준다
	public MemberController(MemberService memberService) { 
		this.memberService = memberService;
	}
	
	// CASE2: 필드 주입 (비추천) : 중간에 바꿀 수 있는 방법이 없음
//	@Autowired private final MemberService memberService; 
	
	// CASE3 : Setter Injection (단점, 항상 public으로 열어둬야하여 노출/수정 위험이 있음)
//	private MemberService memberService;
//	@Autowired
//	public void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		
		return "members/memberList";
	}
}
