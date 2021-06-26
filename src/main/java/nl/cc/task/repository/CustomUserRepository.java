package nl.cc.task.repository;

import nl.cc.task.entity.CustomUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {

    Optional<CustomUser> findByUserName(String userName);

}
