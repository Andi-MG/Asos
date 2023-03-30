package es.andim.asos.infrastructure.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.andim.asos.application.SimpleMember;
import es.andim.asos.application.in.MembersUseCase;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("members")
@AllArgsConstructor
public class MemberController {

    @Autowired
    private MembersUseCase membersUseCase;

    @GetMapping(value = "/", produces = "application/json")
    public List<SimpleMember> getAllMembers() {
        return membersUseCase.getSimpleMembers();
    }

}
