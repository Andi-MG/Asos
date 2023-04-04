package es.andim.asos.infrastructure.in;

import java.net.URI;
import java.util.List;

import es.andim.asos.domain.MemberAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.andim.asos.application.NewMember;
import es.andim.asos.application.SimpleMember;
import es.andim.asos.application.in.MembersUseCase;
import es.andim.asos.domain.Member;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Validated
@RestController
@RequestMapping("members")
@AllArgsConstructor
public class MemberController {

    @Autowired
    private MembersUseCase membersUseCase;

    @GetMapping(produces = "application/json")
    public List<SimpleMember> getAllMembers() {
        return membersUseCase.getSimpleMembers();
    }

    
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addMember(@RequestBody @Valid NewMember newMember) throws MemberAlreadyExistsException {
        Member addedMember = membersUseCase.addNewMember(newMember);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedMember.getId()).toUri();  
        return ResponseEntity.created(location).build();
    }  
}
