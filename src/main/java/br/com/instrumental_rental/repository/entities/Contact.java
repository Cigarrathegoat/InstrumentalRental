package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_CONTACTS", schema = "instrumental_rental")
@SequenceGenerator(schema = "instrumental_rental", name = "SEQ_CONTACTS",
        sequenceName = "SEQ_CONTACTS", allocationSize = 1)

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

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column(name = "DS_NAME")
    private String contactName;

    @Column(name = "DS_TYPE")
    private String contactType;

    @Column(name = "DS_CONTENT")
    private String contactContent;

}
