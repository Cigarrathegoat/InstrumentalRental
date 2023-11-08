package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_ATTENDANT")
@SequenceGenerator(name = "SEQ_ATTENDANT")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Attendant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATTENDANT")
    @Column(name = "ID_ATTENDANT")
    private String attendantId;

    @Column(name = "DS_NAME")
    private String name;
}
