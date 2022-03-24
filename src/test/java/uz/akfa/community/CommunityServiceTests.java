package uz.akfa.community;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import uz.akfa.community.models.Community;
import uz.akfa.community.services.CommunityService;

import java.util.List;


@DataJpaTest
public class CommunityServiceTests {

    private final CommunityService communityService;

    public CommunityServiceTests(CommunityService communityService) {
        this.communityService = communityService;
    }

//    @Test
//    public List<Community> getAll(){
//
//    }
}