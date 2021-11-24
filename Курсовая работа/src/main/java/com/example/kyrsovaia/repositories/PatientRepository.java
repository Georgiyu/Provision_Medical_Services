package com.example.kyrsovaia.repositories;

import com.example.kyrsovaia.models.Patients;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patients, Integer> {
}
