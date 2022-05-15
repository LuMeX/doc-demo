package com.example.docdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {

    @Query(value = "SELECT DISTINCT d.city FROM Doctor d ORDER BY d.city")
    List<String> findDistinctCity();

    @Query(value = "SELECT DISTINCT d.facility FROM Doctor d ORDER BY d.facility")
    List<String> findDistinctFacility();

}
