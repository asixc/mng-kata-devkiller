package com.mango.customer.repositories;

import com.mango.customer.models.Slogan;
import com.mango.customer.models.User;
import com.mango.customer.repository.SloganRepository;
import com.mango.customer.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SloganRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SloganRepository sloganRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(sloganRepository).isNotNull();
    }

    @Test
    @DisplayName("Slogan is saved successful")
    void whenSaved_thenFindAll() {
        final var user= User.builder()
			.name("nameTest")
			.lastName("lastNameTest")
			.address("addresTest")
			.city("cityTest")
			.email("email@test.com")
			.build();
        userRepository.save(user);

        sloganRepository.save(Slogan.builder()
                .slogan("Slogan test")
                .user(user).build());

        assertThat(sloganRepository.findAll()).isNotNull();
    }

    @Test
    @DisplayName("When slogan is updated the field updatedAt is informed")
    void whenUpdated_updatedAt_haveInformation() {
        final var user= User.builder()
			.name("nameTest")
			.lastName("lastNameTest")
			.address("addresTest")
			.city("cityTest")
			.email("email@test.com")
			.build();
        final var slogan = Slogan.builder()
			.slogan("Slogan test")
			.user(user).build();
        userRepository.save(user);
        final var sloganSaved = sloganRepository.save(slogan);
        sloganSaved.setSlogan("new slogan test");

        final var sloganUpdated = sloganRepository.save(sloganSaved);

        assertThat(sloganRepository.findAll()).isNotNull();
        assertThat(sloganUpdated.getUpdatedAt()).isNotNull();
    }
}
