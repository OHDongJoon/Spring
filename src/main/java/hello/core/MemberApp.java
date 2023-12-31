package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
         * 스프링을 이용한 스프링 빈 등록 - 2023.11.13
         * ApplicationContent 를 스프링 컨테이너라 한다.
         * 기존에는 개발자가 AppConfig 를 사용해서 직접 객체를 생성하고 DI를 했지만 이제부터는 스프링 컨테이너를 통해서 사용한다.
         * 스프링 컨테이너는 @Configuration 이 붙은 AppConfig 를 설정(구성) 정보로 사용한다. 여기서 @Bean 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.
         * 스프링 받은 @Bean 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다 (memberService, orderService)
         * 이전에는 개발자가 필요한 객체를 AppConfig 를 사용해서 직접 조회 했지만 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야 한다.
         * 스프링 빈은 applicationContext.getBean() 메서드를 사용해서 찾을 수 있다. 이 메서드는 이름과 타입으로 조회할 수 있다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 스프링을 이용한 스프링 빈 등록
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }

}
