package site.adithk.usermanagementservice.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import site.adithk.usermanagementservice.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findUserEntityByEmail(String email);

    Optional<UserEntity> findUserEntityByVerificationDataVerificationLink(String verificationLink);


}
