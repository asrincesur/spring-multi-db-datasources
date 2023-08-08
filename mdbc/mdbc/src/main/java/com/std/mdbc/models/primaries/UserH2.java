package com.std.mdbc.models.primaries;

import com.std.mdbc.models.IBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name = "p_user")
@SequenceGenerator(name = "seq1")
@NoArgsConstructor
public class UserH2 implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public UserH2(String name) {
        this.name = name;
    }
}