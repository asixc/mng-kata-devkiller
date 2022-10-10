package com.mango.customer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "LAST_NAME")
	private String lastName;

	private String address;

	private String city;

	private String email;

	@OneToMany(mappedBy = "user")
	private List<Slogan> slogans = new ArrayList<>();
}
