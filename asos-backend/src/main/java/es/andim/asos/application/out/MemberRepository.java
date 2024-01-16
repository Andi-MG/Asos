package es.andim.asos.application.out;

import es.andim.asos.application.MemberAlreadyExistsException;
import es.andim.asos.domain.model.Member;

import java.util.List;

public interface MemberRepository {

    List<Member> findAllActiveMembers();

    Member addMember(Member newMember) throws MemberAlreadyExistsException;
}
