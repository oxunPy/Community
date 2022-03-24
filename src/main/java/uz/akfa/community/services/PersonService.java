package uz.akfa.community.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.akfa.community.models.GenderType;
import uz.akfa.community.models.Person;
import uz.akfa.community.models.Status;
import uz.akfa.community.repos.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;


    public Person save(Person person, String status_code, String gender_code){  // save new person or update existing one.
        person.setStatus(Status.decode(status_code));
        person.setGender(GenderType.decode(gender_code));
        return personRepository.save(person);
    }

    // check if it exists or not by given id
    public boolean existsById(Long id){
        return personRepository.existsById(id);
    }

    // get person by given id.
    public Person getById(Long id){
        if(existsById(id)){
            return personRepository.findById(id).get();
        }
        return null;
    }

    // delete person by given id.
    public boolean delete(Long id){
        if(existsById(id)){
            personRepository.delete(getById(id));
            return true;
        }
        return false;
    }

    // getting all entity objects.
    public List<Person> findAll(){
        return personRepository.findAll();
    }
}
