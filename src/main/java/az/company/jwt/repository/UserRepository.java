package az.company.jwt.repository;

import az.company.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPassportId(String passportId);

    @Transactional
    @Modifying
    @Query("update User u set u.lastLoginDate = ?1 where u.username = ?2")
    void setUserInfoById(LocalDateTime lastLoginDate, String userName);
}