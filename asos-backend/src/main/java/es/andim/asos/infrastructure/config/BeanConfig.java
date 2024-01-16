package es.andim.asos.infrastructure.config;

import es.andim.asos.application.out.MemberRepository;
import es.andim.asos.application.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {


    @Bean
    MemberService memberService(MemberRepository repository){
        return new MemberService(repository);
    }
}
