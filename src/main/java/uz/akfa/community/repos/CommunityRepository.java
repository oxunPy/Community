package uz.akfa.community.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.akfa.community.models.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

}
