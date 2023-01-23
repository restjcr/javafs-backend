package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.IPatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService{

    //@Autowired
    private final IPatientRepo repo;// = new PatientRepo();

    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        /*Optional<Patient> op = repo.findById(id);
        return op.isPresent() ? op.get() : new Patient();*/

        return repo.findById(id).orElse(new Patient());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }



    /*public PatientService(PatientRepo repo){
        this.repo = repo;
    }*/

    /*public String sayHello(Patient p){
        return repo.sayHello(p);
    }*/
}
