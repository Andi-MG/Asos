package es.andim.asos.domain.ports;

import es.andim.asos.domain.MemberAlreadyExistsException;
import es.andim.asos.domain.model.Member;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MemberRepository {

    List<Member> findAllActiveMembers();

    @Transactional
    Member addMember(Member newMember) throws MemberAlreadyExistsException;
}
