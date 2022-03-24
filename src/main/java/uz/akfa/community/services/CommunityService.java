package uz.akfa.community.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.akfa.community.models.Community;
import uz.akfa.community.models.Person;
import uz.akfa.community.repos.CommunityRepository;
import uz.akfa.community.repos.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final PersonRepository personRepository;

    public Community save(Community community){     // save new community or update existing one.
        return communityRepository.save(community);
    }
    // check if it exists or not by given id
    public boolean existsById(Long id){
        return communityRepository.existsById(id);
    }

    // get community by given id.
    public Community getById(Long id){
        if(existsById(id)){
            return communityRepository.findById(id).get();
        }
        return null;
    }

    // delete community by given id.
    public boolean delete(Long id){
        if(existsById(id)){
            communityRepository.delete(getById(id));
            return true;
        }
        return false;
    }

    // getting all entity objects.
    public List<Community> findAll(){
        return communityRepository.findAll();
    }

    // adding person to a community.
    public boolean assignNewPerson(Long community_id, Long person_id){
        // check for validity.
        if(existsById(community_id) && personRepository.existsById(person_id)){
            Community com = communityRepository.getById(community_id);// get community by given id
            Person person = personRepository.getById(person_id);    // get person by given id and associate it.
            person.setPersonCommunity(com);             // set community for a person
            personRepository.save(person);              // update
            return true;
        }
        return false;
    }

    // deleting person from a community.
    public boolean deletePerson(Long community_id, Long person_id){
        // check for validity.
        if(existsById(community_id) && personRepository.existsById(person_id)){
            Community com = communityRepository.getById(community_id);// get community by given id
            Person person = personRepository.getById(person_id);    // get person by given id and associate it.
            person.setPersonCommunity(null);      // remove community from person.
            personRepository.save(person);          // update
            return true;
        }
        return false;
    }



}
