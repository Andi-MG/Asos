package es.andim.asos.infrastructure.out;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.andim.asos.domain.Member;
import es.andim.asos.domain.MemberRepository;

@Repository
public class MemberInListRepository implements MemberRepository{

    @Override
    public List<Member> findAll() {
        return List.of(Member.builder().alias("Placeholder").build()); // TODO: Replace placeholder implementation
    }
    
}
