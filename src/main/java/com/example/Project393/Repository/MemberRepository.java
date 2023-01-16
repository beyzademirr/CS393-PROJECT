package com.example.Project393.Repository;

import com.example.Project393.Model.Member;
import org.springframework.data.jpa.repository.*;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("select m from Member m where m.id=?1")
    Member findByID(int memberId);
}
