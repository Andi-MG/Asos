package es.andim.asos.infrastructure.config;

import es.andim.asos.application.service.MemberService;
import es.andim.asos.domain.model.Association;
import es.andim.asos.domain.ports.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    Association association(MemberRepository memberRepository){
        return new Association(memberRepository);
    }

    @Bean
    MemberService memberService(Association association){
        return new MemberService(association);
    }
}
