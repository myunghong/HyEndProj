package HyEnd.Proj.Repository;

import HyEnd.Proj.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);
    boolean existsByUserId(String userId);

}
