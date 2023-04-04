package es.andim.asos.infrastructure.out;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import es.andim.asos.domain.MemberAlreadyExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import es.andim.asos.domain.Member;
import es.andim.asos.domain.MemberRepository;

@Repository //TODO: Replace with actual implementation
public class MemberInListRepository implements MemberRepository {

    List<Member> memberList = new ArrayList<>();

    @Override
    public List<Member> findAll() {
        return memberList;
    }

    @Override
    public Member addMember(Member newMember) throws MemberAlreadyExistsException {
        boolean isAlreadyMember = memberList.stream().anyMatch(member -> StringUtils.equals(newMember.getDni(), member.getDni()));
        if(isAlreadyMember) {
            throw new MemberAlreadyExistsException();
        }
        return addToRepository(newMember);
    }

    private Member addToRepository(Member newMember) {
        UUID id = UUID.randomUUID();
        Member member = Member.builder().id(id).alias(newMember.getAlias()).dni(newMember.getDni()).build();
        memberList.add(member);
        return member;
    }

}
