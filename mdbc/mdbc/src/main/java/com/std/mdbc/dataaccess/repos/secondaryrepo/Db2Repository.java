package com.std.mdbc.dataaccess.repos.secondaryrepo;
import com.std.mdbc.models.secondaries.UserDb2;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface Db2Repository extends JpaRepository<UserDb2, Long> {

}
