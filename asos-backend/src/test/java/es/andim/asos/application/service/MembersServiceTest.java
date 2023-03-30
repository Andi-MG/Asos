package es.andim.asos.application.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.andim.asos.application.SimpleMember;
import es.andim.asos.domain.Association;
import es.andim.asos.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembersServiceTest {

    @Mock
    private Association association;

    @InjectMocks
    private MemberService membersService;

    @Test
    void shouldReturnEmptyList_whenThereAreNoMembers(){
        when(association.getAllMembers()).thenReturn(List.of());

        List<SimpleMember> actualMembers = membersService.getSimpleMembers();

        assertThat(actualMembers).isEmpty();
    }

    @Test
    void shouldReturnSimpleMembers_whenThereAreMembers(){
        when(association.getAllMembers()).thenReturn(List.of(Member.builder().alias("Borja").build()));

        List<SimpleMember> actualMembers = membersService.getSimpleMembers();

        assertThat(actualMembers).containsExactly(SimpleMember.builder().alias("Borja").build());
    }
}
