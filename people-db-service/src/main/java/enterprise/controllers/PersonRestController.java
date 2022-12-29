
package enterprise.controllers;

import enterprise.dto.PersonDto;
import enterprise.models.Person;
import enterprise.services.PersonService;
import enterprise.utils.BCryptEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people-db-service")
public class PersonRestController {

    private PersonService personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    // http://localhost:8080/people-db-service/people
    @GetMapping("/people")
    public List<PersonDto> findAll() {
        return personService.findAll().stream().map(this::convertToPersonDto).collect(Collectors.toList());
    }

    // http://localhost:8080/people-db-service/people/username
    @GetMapping("/people/{username}")
    public PersonDto findByUsername(@PathVariable("username") String username) {

        if (personService.findByUsername(username) == null) {

            return null;

        } else {

            return convertToPersonDto(personService.findByUsername(username));
        }
    }

    // http://localhost:8080/people-db-service/people
    @PostMapping("/people")
    public void save(@RequestBody PersonDto personDto) {

        BCryptEncoder encoder = new BCryptEncoder();

        String password = personDto.getPassword();

        String encodedPassword = encoder.encodePassword(password);

        personDto.setPassword(encodedPassword);

        Person personForSaving = convertToPerson(personDto);

        if (personService.findByUsername(personForSaving.getUsername()) == null) {

            personService.save(personForSaving);
        }
    }

    // http://localhost:8080/people-db-service/people/username
    @DeleteMapping("/people/{username}")
    public void deleteByUsername(@PathVariable("username") String username) {
        personService.deleteByUsername(username);
    }

    private Person convertToPerson(PersonDto personDto) {
        return new ModelMapper().map(personDto, Person.class);
    }

    private PersonDto convertToPersonDto(Person person) {
        return new ModelMapper().map(person, PersonDto.class);
    }
}
