package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_CONTACTS")
@SequenceGenerator(name = "SEQ_CONTACTS")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Contact {

    @Id
    @Column(name = "ID_CONTACTS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACTS")
    private Long contactId;

    @Column(name = "ID_PERSON")
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column(name = "DS_TYPE")
    private String contactType;

    @Column(name = "DS_CONTENT")
    private String contactContent;

}
