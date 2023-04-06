package es.andim.asos.domain.model;

import java.util.List;

import es.andim.asos.domain.MemberAlreadyExistsException;
import es.andim.asos.domain.ports.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Association {

    private MemberRepository memberRepository;

    public List<Member> getAllActiveMembers() {
        return memberRepository.findAllActiveMembers();
    }

    public Member addNewMember(Member member) throws MemberAlreadyExistsException {
        return memberRepository.addMember(member);
    }
}
