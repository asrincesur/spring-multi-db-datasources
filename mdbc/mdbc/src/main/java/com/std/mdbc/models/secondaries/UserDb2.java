package com.std.mdbc.models.secondaries;

import com.std.mdbc.models.IBaseEntity;
import com.std.mdbc.models.UserPositions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.service.annotation.GetExchange;

//    @Column(name="isactive")
//    private Boolean isActive;
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "p_user2")
@SequenceGenerator(name = "seq2")
public class UserDb2 implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Transient
    public UserPositions getUserPositions() {
        return UserPositions.fromID(departmentId);
    }


    public UserDb2(String name, Boolean isActive, Long departmentId) {
        this.name = name;
        this.isActive = isActive;
        this.departmentId = departmentId;
    }

}