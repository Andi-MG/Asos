package es.andim.asos.domain;

import java.util.List;

import es.andim.asos.domain.model.Association;
import es.andim.asos.domain.model.Member;
import es.andim.asos.domain.ports.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociationTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private Association association;

    @Test
    void getAllMembers_shouldReturnEmptyList_whenThereAreNoMembersInTheAssociation(){
        when(memberRepository.findAllActiveMembers()).thenReturn(List.of());

        List<Member> actualMemberList = association.getAllActiveMembers();

        assertThat(actualMemberList).isEmpty();
    }

    @Test
    void getAllMembers_shouldReturnMembers_whenThereAreMembersInTheAssociation(){
        when(memberRepository.findAllActiveMembers()).thenReturn(List.of(Member.builder().alias("Borja").build()));

        List<Member> actualMemberList = association.getAllActiveMembers();

        assertThat(actualMemberList).isNotEmpty();
    }

    @Test
    void addNewMember_shouldReturnMember_whenAddedCorrectlyToTheAssociation() throws MemberAlreadyExistsException {
        Member givenNewMember = Member.builder().dni("dni").alias("Borja").build();
        String givenId = "647e9d17-3ddf-4f6e-ac0c-1a9cfe1bf00d";
        Member addedMember = Member.builder().dni("dni").id(givenId).alias("Borja").build();

        when(memberRepository.addMember(givenNewMember)).thenReturn(addedMember);

        Member savedMember = association.addNewMember(givenNewMember);

        assertThat(savedMember.getId()).isEqualTo(givenId);
    }
}
