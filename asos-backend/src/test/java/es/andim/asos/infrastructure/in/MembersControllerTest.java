package es.andim.asos.infrastructure.in;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.andim.asos.application.SimpleMember;
import es.andim.asos.application.in.MembersUseCase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
        
        when(membersUseCase.getSimpleMembers()).thenReturn(givenMemberList);

        MvcResult result = mockMvc.perform(get("/members")).andExpect(status().isOk())
        .andReturn();
        List<SimpleMember> actualMemberList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<SimpleMember>>(){});

        assertThat(actualMemberList).containsAll(givenMemberList);
    }
}
