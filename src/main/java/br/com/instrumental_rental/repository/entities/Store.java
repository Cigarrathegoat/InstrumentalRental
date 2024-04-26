package br.com.instrumental_rental.repository.entities;


/*
@Entity
@Table(name = "TB_THEADDRESS", schema = "instrumental_rental")
@SequenceGenerator(name = "SEQ_THEADDRESS")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
 */

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_STORE", schema = "instrumental_rental")
@SequenceGenerator(name = "SEQ_STORE")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Store {
}
