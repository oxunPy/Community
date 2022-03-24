package uz.akfa.community.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uz.akfa.community.models.Community;
import uz.akfa.community.models.Person;
import uz.akfa.community.repos.CommunityRepository;
import uz.akfa.community.repos.PersonRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommunityServiceTest {
    @MockBean
    CommunityRepository communityRepository;

    @MockBean
    PersonRepository personRepository;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private PersonService personService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("should find all communities")
    void shouldFindAllMainlands() {
        when(communityRepository.findAll()).thenReturn(Stream.of(
                new Community("Community_A"),
                new Community("Community_B"),
                new Community("Community_C")).collect(Collectors.toList()));
        assertEquals(3, communityService.findAll().size());
    }

    @Test
    @DisplayName("should save new community")
    void shouldSaveNewCommunityTest() {
        Community community = new Community("Community A");
        when(communityRepository.save(community)).thenReturn(community);
        assertEquals(community, communityService.save(community));
    }

    @Test
    @DisplayName("should get community by its id")
    void shouldGetCommunityByIdTest() {
        long id = 2;
        Community community = new Community("Europe");
        when(communityRepository.existsById(id)).thenReturn(true);
        when(communityRepository.findById(id)).thenReturn(Optional.of(community));
        assertEquals(community, communityService.getById(id));
    }

    @Test
    @DisplayName("should check existence of community by its id")
    void shouldCheckExistenceByIdTest() {
        long id = 23;
        when(communityRepository.existsById(id)).thenReturn(false);
        assertFalse(communityService.existsById(id));
    }

    @Test
    @DisplayName("should delete community")
    void shouldDeleteCommunityTest() {
        Community community = new Community("A");
        community.setId(1L);
        when(communityRepository.existsById(community.getId())).thenReturn(true);
        when(communityRepository.findById(community.getId())).thenReturn(Optional.of(community));
        communityService.delete(community.getId());
        verify(communityRepository, times(1)).delete(community);
    }

    @Test
    @DisplayName("should assign new person to communit")
    void shouldAssignNewPersonTest() {
        long community_id = 2, person_id = 4;
        when(communityRepository.existsById(community_id)).thenReturn(true);
        when(personRepository.existsById(person_id)).thenReturn(true);

        Assertions.assertThat(communityService.existsById(community_id)).isEqualTo(true);
        Assertions.assertThat(personService.existsById(person_id)).isEqualTo(true);
    }

    @Test
    @DisplayName("should delete a person from community")
    void shouldDeletePersonTest() {
        long community_id = 2, person_id = 4;

        when(communityRepository.existsById(community_id)).thenReturn(true);
        when(personRepository.existsById(person_id)).thenReturn(true);

        Assertions.assertThat(communityService.existsById(community_id)).isEqualTo(true);
        Assertions.assertThat(personService.existsById(person_id)).isEqualTo(true);

        Person person = new Person("John");
        person.setId(person_id);
        person.setPersonCommunity(new Community("Community_A"));
        when(personRepository.findById(person_id)).thenReturn(Optional.of(person));
        assertEquals(personService.getById(person_id).getPersonCommunity() != null, true);
    }
}