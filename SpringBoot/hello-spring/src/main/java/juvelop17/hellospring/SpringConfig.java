package juvelop17.hellospring;

import juvelop17.hellospring.repository.MemberRepository;
import juvelop17.hellospring.repository.MemoryMemberRepository;
import juvelop17.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
