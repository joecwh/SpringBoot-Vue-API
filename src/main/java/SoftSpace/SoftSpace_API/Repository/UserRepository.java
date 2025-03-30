package SoftSpace.SoftSpace_API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import SoftSpace.SoftSpace_API.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
