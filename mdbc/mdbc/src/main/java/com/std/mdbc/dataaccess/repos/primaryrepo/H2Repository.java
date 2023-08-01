package com.std.mdbc.dataaccess.repos.primaryrepo;

import com.std.mdbc.models.primaries.UserH2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2Repository extends JpaRepository<UserH2, Long> {

}
