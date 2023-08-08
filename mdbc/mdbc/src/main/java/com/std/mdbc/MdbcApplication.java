package com.std.mdbc;

import com.std.mdbc.configs.repoconfig.Db2Conf;
import com.std.mdbc.dataaccess.repos.primaryrepo.H2Repository;
import com.std.mdbc.dataaccess.repos.secondaryrepo.Db2Repository;
import com.std.mdbc.models.primaries.UserH2;
import com.std.mdbc.models.secondaries.UserDb2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MdbcApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(MdbcApplication.class, args);
		H2Repository h2Repository = context.getBean(H2Repository.class);
		Db2Repository db2Repository = context.getBean(Db2Repository.class);


		UserH2 userH2 = new UserH2("Jean");
		UserDb2 userDb2 = new UserDb2("Ulug",true);

		db2Repository.save(userDb2);
		h2Repository.save(userH2);
	}

}
