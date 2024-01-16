package es.andim.asos.infrastructure.in;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import es.andim.asos.application.MemberAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.andim.asos.application.NewMember;
import es.andim.asos.application.SimpleMember;
import es.andim.asos.application.in.MembersUseCase;
import es.andim.asos.domain.model.Member;

@SpringBootTest
@AutoConfigureMockMvc
class MembersControllerTest {

    @Autowired
	private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MembersUseCase membersUseCase;

    @Test
    void shouldReturnEmptyList_whenThereAreNoMembers() throws Exception{
        MvcResult result = mockMvc.perform(get("/members")).andExpect(status().isOk())
                        .andReturn();
        List<SimpleMember> actualMemberList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<SimpleMember>>(){});
        
        assertThat(actualMemberList).isEmpty();
    }

    @Test
    void shouldReturnMembersWithAlias_whenThereAreMembers() throws Exception{
        List<SimpleMember> givenMemberList = List.of(SimpleMember.builder().alias("Borja").build());
        
        when(membersUseCase.getActiveMembersSummary()).thenReturn(givenMemberList);

        MvcResult result = mockMvc.perform(get("/members")).andExpect(status().isOk())
        .andReturn();
        List<SimpleMember> actualMemberList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<SimpleMember>>(){});

        assertThat(actualMemberList).containsAll(givenMemberList);
    }

    @Test
    void shouldReturnLocationURI_whenMemberIsCreated() throws Exception{
        NewMember newMember = NewMember.builder().alias("Borja").dni("dni").build();
        String givenId = "647e9d17-3ddf-4f6e-ac0c-1a9cfe1bf00d";
        Member addedMember = Member.builder().id(givenId).alias("Borja").build();

        when(membersUseCase.addNewMember(any(NewMember.class))).thenReturn(addedMember);

        mockMvc.perform(post("/members").content(objectMapper.writeValueAsString(newMember)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/members/"+givenId));
    }

    @Test
    void shouldReturnError_whenGivenMemberDoesNotHaveRequiredFields() throws Exception{
        NewMember newMember = NewMember.builder().build();

        mockMvc.perform(post("/members").content(objectMapper.writeValueAsString(newMember)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors", hasSize(2))); // dni & alias
    }

    @Test
    void shouldReturnSpecificError_whenAnExceptionOccursWhenAddingNewMember() throws Exception {
        NewMember alreadyExistingNewMember = NewMember.builder().alias("Borja").dni("idAlreadyInDb").build();
        MemberAlreadyExistsException exception = new MemberAlreadyExistsException();

        when(membersUseCase.addNewMember(any(NewMember.class))).thenThrow(exception);

        mockMvc.perform(post("/members").content(objectMapper.writeValueAsString(alreadyExistingNewMember)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict());
    }
}
