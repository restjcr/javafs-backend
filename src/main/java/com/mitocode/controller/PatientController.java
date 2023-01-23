package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    //@Autowired
    private final PatientServiceImpl service;// = new PatientService();

    @GetMapping
    public ResponseEntity<List<Patient>> findAll(){
        List<Patient> list = service.findAll();
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);
        return new ResponseEntity<>(obj, OK);
    }

    /*@PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
        Patient obj = service.save(patient);
        return new ResponseEntity<>(obj, CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Patient patient){
        Patient obj = service.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping//(consumes = "", produces = "")
    public ResponseEntity<Patient> update(@RequestBody Patient patient){
        Patient obj = service.update(patient);
        return new ResponseEntity<>(obj, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    /*@GetMapping
    public String sayHello(){
        Patient p = new Patient();
        p.setIdPatient(1);
        p.setName("Mito");
        return service.sayHello(p);
    }*/

}
