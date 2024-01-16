package es.andim.asos.application.in;

import java.util.List;

import es.andim.asos.application.NewMember;
import es.andim.asos.application.SimpleMember;
import es.andim.asos.domain.model.Member;
import es.andim.asos.application.MemberAlreadyExistsException;

public interface MembersUseCase {

    List<SimpleMember> getActiveMembersSummary();

    Member addNewMember(NewMember newMember) throws MemberAlreadyExistsException;

}
