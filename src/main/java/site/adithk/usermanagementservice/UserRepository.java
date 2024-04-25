package site.adithk.usermanagementservice;

import org.springframework.data.jpa.repository.JpaRepository;
import site.adithk.usermanagementservice.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
