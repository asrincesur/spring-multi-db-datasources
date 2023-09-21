package com.std.mdbc.business.concretes;


import com.std.mdbc.business.abstracts.UserService;
import com.std.mdbc.dataaccess.repos.primaryrepo.H2Repository;
import com.std.mdbc.dataaccess.repos.secondaryrepo.Db2Repository;
import com.std.mdbc.models.IBaseEntity;
import com.std.mdbc.models.primaries.UserH2;
import com.std.mdbc.models.secondaries.UserDb2;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserManager implements UserService{


    private final H2Repository h2Repository;
    private  final Db2Repository db2Repository;
    public UserManager(H2Repository h2Repository, Db2Repository db2Repository) {
        this.h2Repository = h2Repository;
        this.db2Repository = db2Repository;
    }


    @Override
    public LinkedList<IBaseEntity> getAll() {
        List<UserH2> userH2s = h2Repository.findAll();
        List<UserDb2> userDb2s = db2Repository.findAll();
        userH2s.stream().forEach(System.out::println);
        userDb2s.stream().forEach(System.out::println);
        LinkedList<IBaseEntity> list = new LinkedList<>();
        userDb2s.stream().forEach(x -> System.out.println(x.getUserPositions()));
        list.addAll(userH2s);
        list.addAll(userDb2s);
        return list;
    }
}
