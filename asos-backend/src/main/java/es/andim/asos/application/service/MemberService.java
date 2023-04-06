package es.andim.asos.application.service;

import java.util.List;
import java.util.stream.Collectors;

import es.andim.asos.domain.MemberAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.andim.asos.application.NewMember;
import es.andim.asos.application.SimpleMember;
import es.andim.asos.application.in.MembersUseCase;
import es.andim.asos.domain.model.Association;
import es.andim.asos.domain.model.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements MembersUseCase {

    @Autowired
    private final Association association;
    
    @Override
    public List<SimpleMember> getActiveMembersSummary() {
        List<Member> memberList = association.getAllActiveMembers();
        return memberList.stream()
            .map(member -> SimpleMember.builder()
                            .alias(member.getAlias())
                            .build())
            .collect(Collectors.toList());
    }

    @Override
    public Member addNewMember(NewMember newMember) throws MemberAlreadyExistsException {
        Member member = mapNewMemberToMember(newMember);
        return association.addNewMember(member);
    }

    private Member mapNewMemberToMember(NewMember newMember) {
        return Member.builder()
                .dni(newMember.getDni())
                .alias(newMember.getAlias())
                .build();
    }
}
