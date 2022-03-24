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
import uz.akfa.community.models.GenderType;
import uz.akfa.community.models.Person;
import uz.akfa.community.models.Status;
import uz.akfa.community.repos.CommunityRepository;
import uz.akfa.community.repos.PersonRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceTest {

    @MockBean
    PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("should find all people")
    void shouldFindAllMainlands() {
        when(personRepository.findAll()).thenReturn(Stream.of(
                new Person("John"),
                new Person("Alex"),
                new Person("Simon")).collect(Collectors.toList()));
        assertEquals(3, personService.findAll().size());
    }

    @Test
    @DisplayName("should save new person")
    void shouldSaveNewCommunityTest() {
        Person person = new Person("Alex");
        person.setStatus(Status.GRADUATED);
        person.setGender(GenderType.MALE);
        when(personRepository.save(person)).thenReturn(person);
        assertEquals(person, personService.save(new Person("Alex"), person.getStatus().getCode(),
                                                        person.getGender().getCode()));
    }

    @Test
    @DisplayName("should get person by its id")
    void shouldGetPersonByIdTest() {
        long id = 2;
        Person person = new Person("Alex");
        person.setId(id);
        when(personRepository.existsById(id)).thenReturn(true);
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        assertEquals(person, personService.getById(id));
    }

    @Test
    @DisplayName("should check existence of person by its id")
    void shouldCheckExistenceByIdTest() {
        long id = 23;
        when(personRepository.existsById(id)).thenReturn(false);
        assertFalse(personService.existsById(id));
    }

    @Test
    @DisplayName("should delete person")
    void shouldDeletePersonTest() {
        Person person = new Person("Alex");
        person.setId(1L);
        when(personRepository.existsById(person.getId())).thenReturn(true);
        when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        personService.delete(person.getId());
        verify(personRepository, times(1)).delete(person);
    }

}