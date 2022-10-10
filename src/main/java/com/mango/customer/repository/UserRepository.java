package com.mango.customer.repository;

import com.mango.customer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByNameAndLastNameAndAddressAndCityAndEmail(String name, String lastName, String Address, String city, String email);
}
