package es.andim.asos.domain.model;

import java.util.List;

import es.andim.asos.domain.MemberAlreadyExistsException;
import es.andim.asos.domain.ports.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Association {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllActiveMembers() {
        return memberRepository.findAllActiveMembers();
    }

    public Member addNewMember(Member member) throws MemberAlreadyExistsException {
        return memberRepository.addMember(member);
    }
}
