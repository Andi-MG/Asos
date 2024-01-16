package es.andim.asos.infrastructure.out.persistence;

import es.andim.asos.domain.model.Member;
import es.andim.asos.application.MemberAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberPersistenceAdapterTest {

    @Autowired
    private MemberPersistenceAdapter memberPersistenceAdapter;
    @Autowired
    private SpringDataMemberRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void findAll_shouldReturnEmptyList_whenThereAreNoMembersInTheAssociation(){
        List<Member> actualMemberList = memberPersistenceAdapter.findAllActiveMembers();

        assertThat(actualMemberList).isEmpty();
    }

    @Test
    void addMember_shouldReturnMemberWithId_whenAddingMembersToTheAssociation() throws MemberAlreadyExistsException {
        Member member = Member.builder().alias("Borja").dni("dni").build();

        Member actualAddedMember = memberPersistenceAdapter.addMember(member);

        assertThat(actualAddedMember.getId()).isNotNull();
    }

    @Test
    void findAll_shouldReturnListWithMembers_whenMembersWereAddedToTheAssociation() throws MemberAlreadyExistsException {
        Member member = Member.builder().alias("Borja").dni("dni").build();

        memberPersistenceAdapter.addMember(member);
        List<Member> actualMemberList = memberPersistenceAdapter.findAllActiveMembers();

        assertThat(actualMemberList).isNotEmpty();
    }

    @Test
    void shouldThrowException_whenAddingAnAlreadyExistingMember() throws MemberAlreadyExistsException {
        Member givenNewMember = Member.builder().dni("dni").alias("Borja").build();
        memberPersistenceAdapter.addMember(givenNewMember);

        assertThrows(MemberAlreadyExistsException.class, () -> memberPersistenceAdapter.addMember(givenNewMember));
    }
    
}
