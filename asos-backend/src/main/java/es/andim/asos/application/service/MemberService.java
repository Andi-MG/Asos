package es.andim.asos.application.service;

import java.util.List;
import java.util.stream.Collectors;

import es.andim.asos.application.MemberAlreadyExistsException;

import es.andim.asos.application.NewMember;
import es.andim.asos.application.SimpleMember;
import es.andim.asos.application.in.MembersUseCase;
import es.andim.asos.application.out.MemberRepository;
import es.andim.asos.domain.model.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberService implements MembersUseCase {

    private final MemberRepository repository;
    
    @Override
    public List<SimpleMember> getActiveMembersSummary() {
        List<Member> memberList = repository.findAllActiveMembers();
        return memberList.stream()
            .map(member -> SimpleMember.builder()
                            .alias(member.getAlias())
                            .build())
            .toList();
    }

    @Override
    public Member addNewMember(NewMember newMember) throws MemberAlreadyExistsException {
        Member member = mapNewMemberToMember(newMember);
        return repository.addMember(member);
    }

    private Member mapNewMemberToMember(NewMember newMember) {
        return Member.builder()
                .dni(newMember.getDni())
                .alias(newMember.getAlias())
                .build();
    }
}
