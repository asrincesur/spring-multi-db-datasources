package com.std.mdbc.models.secondaries;

import com.std.mdbc.models.IBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//    @Column(name="isactive")
//    private Boolean isActive;

@Entity
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

    public UserDb2(String name, Boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }
// getter-setter ve diğer alanlar
}