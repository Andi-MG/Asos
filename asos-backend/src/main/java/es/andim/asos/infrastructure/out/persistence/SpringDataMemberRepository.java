package es.andim.asos.infrastructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringDataMemberRepository extends JpaRepository<MemberJpaEntity, String> {
}
