package es.andim.asos.infrastructure.out;

import es.andim.asos.domain.Member;
import es.andim.asos.domain.MemberAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MemberInListRepositoryTest {

    private MemberInListRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemberInListRepository();
    }

    @Test
    void findAll_shouldReturnEmptyList_whenThereAreNoMembersInTheAssociation(){
        List<Member> actualMemberList = repository.findAll();

        assertThat(actualMemberList).isEmpty();
    }

    @Test
    void addMember_shouldReturnMemberWithId_whenAddingMembersToTheAssociation() throws MemberAlreadyExistsException {
        Member member = Member.builder().alias("Borja").dni("dni").build();

        Member actualAddedMember = repository.addMember(member);

        assertThat(actualAddedMember.getId()).isNotNull();
    }

    @Test
    void findAll_shouldReturnListWithMembers_whenMembersWereAddedToTheAssociation() throws MemberAlreadyExistsException {
        Member member = Member.builder().alias("Borja").dni("dni").build();

        repository.addMember(member);
        List<Member> actualMemberList = repository.findAll();

        assertThat(actualMemberList).isNotEmpty();
    }

    @Test
    void shouldThrowException_whenAddingAnAlreadyExistingMember() throws MemberAlreadyExistsException {
        Member givenNewMember = Member.builder().dni("dni").alias("Borja").build();
        repository.addMember(givenNewMember);

        assertThrows(MemberAlreadyExistsException.class, () -> repository.addMember(givenNewMember));
    }
    
}
