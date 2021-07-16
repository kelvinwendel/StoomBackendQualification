package com.stoom.application.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Class that represents an address in the business domain.
 */
@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty(message = "The field streetname is required")
    @Length(min = 3, max = 100, message = "The length of streetname should be between 3 to 100 characters")
    private String streetName;

    @NotNull
    private int number;

    private String complement;

    @NotEmpty(message = "The field neighbourhood is required")
    @Length(min = 3, max = 50, message = "The length of neighbourhood should be between 3 to 50 characters")
    private String neighbourhood;

    @NotEmpty(message = "The field city is required")
    @Length(min = 3, max = 50, message = "The length of city should be between 3 to 50 characters")
    private String city;

    @NotEmpty(message = "The field state is required")
    @Length(min = 3, max = 50, message = "The length of state should be between 3 to 50 characters")
    private String state;

    @NotEmpty(message = "The field country is required")
    @Length(min = 3, max = 50, message = "The length of country should be between 3 to 50 characters")
    private String country;

    @NotEmpty(message = "The field zipcode is required")
    private String zipcode;

    private Double latitude;

    private Double longitude;
}