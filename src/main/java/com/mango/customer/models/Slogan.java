package com.mango.customer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SLOGANS")
public class Slogan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String slogan;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;

	@PrePersist
	private void prePersistFunction(){
		log.info("PrePersist method called");
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdateFunction(){
		log.info("PreUpdate method called");
		this.updatedAt = LocalDateTime.now();
	}
}
