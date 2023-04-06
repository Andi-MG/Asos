package es.andim.asos.infrastructure.out.persistence;

import es.andim.asos.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberJpaEntity domainToEntity(Member member, String id){
        return MemberJpaEntity.builder()
                .id(id)
                .alias(member.getAlias())
                .dni(member.getDni())
                .build();
    }

    public Member entityToDomain(MemberJpaEntity memberJpaEntity){
        return Member.builder()
                .id(memberJpaEntity.getId())
                .alias(memberJpaEntity.getAlias())
                .dni(memberJpaEntity.getDni())
                .build();
    }
}
