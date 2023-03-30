package es.andim.asos.domain;

import java.util.List;

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
    void shouldReturnEmptyList_whenThereAreNoMembersInTheAssociation(){
        when(memberRepository.findAll()).thenReturn(List.of());

        List<Member> actualMemberList = association.getAllMembers();

        assertThat(actualMemberList).isEmpty();
    }

    @Test
    void shouldReturnMembers_whenThereAreMembersInTheAssociation(){
        when(memberRepository.findAll()).thenReturn(List.of(Member.builder().alias("Borja").build()));

        List<Member> actualMemberList = association.getAllMembers();

        assertThat(actualMemberList).isNotEmpty();
    }
}
