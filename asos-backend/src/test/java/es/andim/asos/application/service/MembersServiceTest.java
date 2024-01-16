package es.andim.asos.application.service;

import java.util.List;

import es.andim.asos.application.NewMember;
import es.andim.asos.application.MemberAlreadyExistsException;
import es.andim.asos.application.out.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.andim.asos.application.SimpleMember;
import es.andim.asos.domain.model.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembersServiceTest {

    @Mock
    private MemberRepository repository;

    @InjectMocks
    private MemberService membersService;

    @Test
    void getAllMembers_shouldReturnEmptyList_whenThereAreNoMembers(){
        when(repository.findAllActiveMembers()).thenReturn(List.of());

        List<SimpleMember> actualMembers = membersService.getActiveMembersSummary();

        assertThat(actualMembers).isEmpty();
    }

    @Test
    void getAllMembers_shouldReturnSimpleMembers_whenThereAreMembers(){
        when(repository.findAllActiveMembers()).thenReturn(List.of(Member.builder().alias("Borja").build()));

        List<SimpleMember> actualMembers = membersService.getActiveMembersSummary();

        assertThat(actualMembers).containsExactly(SimpleMember.builder().alias("Borja").build());
    }

    @Test
    void addNewMember_shouldReturnNewMember_whenAddedCorrectly() throws MemberAlreadyExistsException {
        NewMember givenNewMember = NewMember.builder().alias("Borja").dni("dni").build();
        Member member = Member.builder().alias("Borja").dni("dni").build();

        String givenId = "647e9d17-3ddf-4f6e-ac0c-1a9cfe1bf00d";
        Member givenMember = Member.builder()
                .alias("Borja")
                .dni("dni")
                .id(givenId)
                .build();
        when(repository.addMember(member)).thenReturn(givenMember);

        Member savedMember = membersService.addNewMember(givenNewMember);

        assertThat(savedMember.getId()).isEqualTo(givenId);
    }
}
