package es.andim.asos.infrastructure.out.persistence;

import es.andim.asos.domain.model.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class MemberMapperTest {

    private final MemberMapper memberMapper = new MemberMapper();

    @Test
    void shouldReturnEntityWithId_whenGivenDomainMemberAndAnId() {
        Member givenMember = Member.builder().alias("Borja").dni("dni").build();
        String givenId = "647e9d17-3ddf-4f6e-ac0c-1a9cfe1bf00d";

        MemberJpaEntity memberJpaEntity = memberMapper.domainToEntity(givenMember, givenId);

        assertThat(memberJpaEntity.getId()).isEqualTo(givenId);
        assertThat(memberJpaEntity.getAlias()).isEqualTo(givenMember.getAlias());
        assertThat(memberJpaEntity.getDni()).isEqualTo(givenMember.getDni());
    }

    @Test
    void shouldReturnDomainMember_whenGivenEntity() {
        MemberJpaEntity givenMemberEntity = MemberJpaEntity.builder()
                .id("647e9d17-3ddf-4f6e-ac0c-1a9cfe1bf00d")
                .alias("Borja")
                .dni("dni")
                .build();

        Member memberDomain = memberMapper.entityToDomain(givenMemberEntity);

        assertThat(memberDomain.getId()).isEqualTo(givenMemberEntity.getId());
        assertThat(memberDomain.getAlias()).isEqualTo(givenMemberEntity.getAlias());
        assertThat(memberDomain.getDni()).isEqualTo(givenMemberEntity.getDni());
    }
}