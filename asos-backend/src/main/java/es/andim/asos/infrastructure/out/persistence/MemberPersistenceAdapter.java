package es.andim.asos.infrastructure.out.persistence;

import java.util.List;
import java.util.UUID;

import es.andim.asos.domain.MemberAlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.andim.asos.domain.model.Member;
import es.andim.asos.domain.ports.MemberRepository;

@Repository
@AllArgsConstructor
public class MemberPersistenceAdapter implements MemberRepository {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private SpringDataMemberRepository memberRepository;

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
