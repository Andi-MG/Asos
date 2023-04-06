package es.andim.asos.domain.ports;

import es.andim.asos.domain.MemberAlreadyExistsException;
import es.andim.asos.domain.model.Member;

import java.util.List;

public interface MemberRepository {

    List<Member> findAllActiveMembers();

    Member addMember(Member newMember) throws MemberAlreadyExistsException;
}
