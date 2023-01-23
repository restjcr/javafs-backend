package com.mitocode.service;

import com.mitocode.model.Patient;

import java.util.List;

public interface IPatientService {

    Patient save(Patient patient);
    Patient update(Patient patient);
    List<Patient> findAll();
    Patient findById(Integer id);
    void delete(Integer id);
}
