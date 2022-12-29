
package enterprise.repositories;

import enterprise.models.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByUsername(String username);

    @Modifying
    @Query("delete from Person p where p.username = :username")
    void deleteByUsername(@Param("username") String username);
}
