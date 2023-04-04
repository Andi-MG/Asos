package es.andim.asos.domain;

import java.util.List;

public interface MemberRepository {

    List<Member> findAll();

    Member addMember(Member newMember) throws MemberAlreadyExistsException;
}
