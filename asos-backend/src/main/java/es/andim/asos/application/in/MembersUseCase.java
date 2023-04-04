package es.andim.asos.application.in;

import java.util.List;

import es.andim.asos.application.NewMember;
import es.andim.asos.application.SimpleMember;
import es.andim.asos.domain.Member;
import es.andim.asos.domain.MemberAlreadyExistsException;

public interface MembersUseCase {

    List<SimpleMember> getSimpleMembers();

    Member addNewMember(NewMember alreadyExistingNewMember) throws MemberAlreadyExistsException;

}
