package es.andim.asos.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.andim.asos.application.SimpleMember;
import es.andim.asos.application.in.MembersUseCase;
import es.andim.asos.domain.Association;
import es.andim.asos.domain.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements MembersUseCase{

    @Autowired
    private final Association association;
    
    @Override
    public List<SimpleMember> getSimpleMembers() {
        List<Member> memberList = association.getAllMembers();
        List<SimpleMember> simpleMemberList = memberList.stream()
            .map(member -> SimpleMember.builder()
                            .alias(member.getAlias())
                            .build())
            .collect(Collectors.toList());
        return simpleMemberList;
    }
    
}
