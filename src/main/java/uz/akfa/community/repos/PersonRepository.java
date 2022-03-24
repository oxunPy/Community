package uz.akfa.community.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.akfa.community.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
