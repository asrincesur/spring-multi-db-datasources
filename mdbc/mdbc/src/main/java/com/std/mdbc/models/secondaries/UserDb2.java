package com.std.mdbc.models.secondaries;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//    @Column(name="isactive")
//    private Boolean isActive;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="p_user2")
@SequenceGenerator(name ="seq2")
public class UserDb2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="isactive")
    private Boolean isActive;

    // getter-setter ve diğer alanlar
}