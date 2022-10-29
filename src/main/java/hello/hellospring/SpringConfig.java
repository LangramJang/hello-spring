package hello.hellospring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

// 직접 Java code를 사용하여 Spring bean 등록하는 방법 
@Configuration
public class SpringConfig {
	// Spring JPA 사용 시
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// JPA 사용 시
//	private EntityManager em;
//	
//	@Autowired
//	public SpringConfig(EntityManager em) {
//		this.em = em;
//	}
	
	// JDBC 사용 시 
//	private DataSource dataSource;
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
//	@Bean // AOP의 경우 직접적으로 등록해주는 것을 추천
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}
	
//	@Bean // Spring JPA 사용 시 별도의 Repository Bean 등록 필요없음, 별도로 Injection 처리해줌
//	public MemberRepository memberRepository() {
////		return new MemoryMemberRepository(); // Local Storage
////		return new JdbcMemberRepository(dataSource); // Spring
////		return new JdbcTemplateMemberRepository(dataSource); // JDBC
//		return new JpaMemberRepository(em); // JPA
//	}
}