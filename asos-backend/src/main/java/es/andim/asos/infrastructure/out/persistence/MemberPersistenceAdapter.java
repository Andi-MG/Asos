package es.andim.asos.infrastructure.out.persistence;

import es.andim.asos.application.MemberAlreadyExistsException;
import es.andim.asos.application.out.MemberRepository;
import es.andim.asos.domain.model.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberRepository {

    private final MemberMapper memberMapper;
    private final SpringDataMemberRepository memberRepository;

    @Override
    public List<Member> findAllActiveMembers() {
        List<MemberJpaEntity> memberJpaEntityList = memberRepository.findAll();
        return memberJpaEntityList.stream().map(member -> memberMapper.entityToDomain(member)).toList();
    }

    @Override
    @Transactional
    public Member addMember(Member newMember) throws MemberAlreadyExistsException {
        boolean isAlreadyMember = memberRepository.findAll().stream()
                .anyMatch(member -> StringUtils.equals(newMember.getDni(), member.getDni()));
        if(isAlreadyMember) {
            throw new MemberAlreadyExistsException();
        }
        return addToRepository(newMember);
    }

    private Member addToRepository(Member newMember) {
        UUID id = UUID.randomUUID();
        MemberJpaEntity memberJpaEntity = memberMapper.domainToEntity(newMember, id.toString());
        MemberJpaEntity savedEntity = memberRepository.save(memberJpaEntity);
        return memberMapper.entityToDomain(savedEntity);
    }

}
