package uz.akfa.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.akfa.community.models.Person;
import uz.akfa.community.services.PersonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/people")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/people/{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        return ResponseEntity.ok(personService.getById(id));
    }

    @PostMapping("/people")
    public ResponseEntity save1(@RequestBody Person person,
                               @RequestParam(name = "status_code") String status_code,
                               @RequestParam(name = "gender_code") String gender_code){
        return ResponseEntity.ok(personService.save(person, status_code, gender_code));
    }

    @PostMapping("/people/{status_code}/{gender_code}")
    public ResponseEntity save2(@RequestBody Person person,
                               @PathVariable String status_code,
                               @PathVariable String gender_code){
        return ResponseEntity.ok(personService.save(person, status_code, gender_code));
    }
}
