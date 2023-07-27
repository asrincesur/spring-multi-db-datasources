package com.std.mdbc.models.primaries;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="p_user")
@SequenceGenerator(name ="seq1")
public class UserH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    // getter-setter ve diğer alanlar
}