package uz.akfa.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.akfa.community.models.Community;
import uz.akfa.community.repos.CommunityRepository;
import uz.akfa.community.services.CommunityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("/communities")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(communityService.findAll());
    }

    @GetMapping("/communities/{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        return ResponseEntity.ok(communityService.getById(id));
    }

    @PostMapping("/communities")
    public ResponseEntity save(@RequestBody Community community){
        return ResponseEntity.ok(communityService.save(community));
    }

    @PostMapping("communities/{community_id}/people/{person_id}")
    public ResponseEntity assignPersonToCommunity(@PathVariable Long community_id, @PathVariable Long person_id){
        // check for validity for both ids, then do something.
        if(communityService.assignNewPerson(community_id, person_id)){
            return ResponseEntity.ok("Added new person to a community.");
        }

        return ResponseEntity.ok("some of entered ids are nonexistent !!!");
    }

    @GetMapping("/msg")
    public String getMessage(){
        return "Hello world!";
    }


    @GetMapping("/communities/{com_id}/people")
    public ResponseEntity getAllPeople(@PathVariable Long com_id){

        return communityService.existsById(com_id) ?
                ResponseEntity.ok(communityService.getById(com_id).getPersonSet()):
                ResponseEntity.ok("Not exists!");
    }

}
