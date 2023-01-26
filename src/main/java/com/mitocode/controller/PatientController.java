package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.exception.NewModelNotFoundException;
import com.mitocode.model.Patient;
import com.mitocode.service.impl.PatientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    //@Autowired
    private final PatientServiceImpl service;// = new PatientService();
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll(){
        List<PatientDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    /*
    @GetMapping
    public ResponseEntity<List<PatientRecord>> findAll(){
        List<PatientRecord> list = service.findAll().stream().map(e -> {
            return new PatientRecord(e.getIdPatient(), e.getFirstName());
        }).collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }
     */

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
            //throw new NewModelNotFoundException("ID NOT FOUND " + id);
        }

        return new ResponseEntity<>(this.convertToDto(obj), OK);
    }

    /*@PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
        Patient obj = service.save(patient);
        return new ResponseEntity<>(obj, CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping//(consumes = "", produces = "")
    public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);

        if(obj == null){
            //throw new ModelNotFoundException("ID NOT FOUND " + id);
            throw new NewModelNotFoundException("ID NOT FOUND " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private PatientDTO convertToDto(Patient obj){
        return mapper.map(obj, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO dto){
        return mapper.map(dto, Patient.class);
    }

    /*@GetMapping
    public String sayHello(){
        Patient p = new Patient();
        p.setIdPatient(1);
        p.setName("Mito");
        return service.sayHello(p);
    }*/

}
