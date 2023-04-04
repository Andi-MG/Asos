package es.andim.asos.domain;

import java.util.List;
import java.util.UUID;

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
        when(memberRepository.findAll()).thenReturn(List.of());

        List<Member> actualMemberList = association.getAllMembers();

        assertThat(actualMemberList).isEmpty();
    }

    @Test
    void getAllMembers_shouldReturnMembers_whenThereAreMembersInTheAssociation(){
        when(memberRepository.findAll()).thenReturn(List.of(Member.builder().alias("Borja").build()));

        List<Member> actualMemberList = association.getAllMembers();

        assertThat(actualMemberList).isNotEmpty();
    }

    @Test
    void addNewMember_shouldReturnMember_whenAddedCorrectlyToTheAssociation() throws MemberAlreadyExistsException {
        Member givenNewMember = Member.builder().dni("dni").alias("Borja").build();
        UUID givenUUID = UUID.fromString("647e9d17-3ddf-4f6e-ac0c-1a9cfe1bf00d");
        Member addedMember = Member.builder().dni("dni").id(givenUUID).alias("Borja").build();

        when(memberRepository.addMember(givenNewMember)).thenReturn(addedMember);

        Member savedMember = association.addNewMember(givenNewMember);

        assertThat(savedMember.getId()).isEqualTo(givenUUID);
    }
}
