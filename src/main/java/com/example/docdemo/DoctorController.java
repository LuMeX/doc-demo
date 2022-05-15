package com.example.docdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRepository repository;

    @GetMapping("/doctors")
    ResponseEntity<List<Doctor>> getDoctors(@RequestParam(name = "city", required = false) String city,
                                            @RequestParam(name = "facility", required = false) String facility,
                                            @RequestParam(name = "expertise", required = false) String expertise) {

        Specification<Doctor> specification = Specification
                .where(StringUtils.hasText(city) ? (Specification<Doctor>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city"), city) : null)
                .and(StringUtils.hasText(facility) ? (Specification<Doctor>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("facility"), facility) : null)
                .and(StringUtils.hasText(expertise) ? (Specification<Doctor>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("areaOfExpertise"), expertise) : null);

        return new ResponseEntity<>(repository.findAll(specification), HttpStatus.OK);
    }

    @GetMapping("/doctors/cities")
    ResponseEntity<List<String>> getDistinctCities() {
        return new ResponseEntity<>(repository.findDistinctCity(), HttpStatus.OK);
    }

    @GetMapping("/doctors/facilities")
    ResponseEntity<List<String>> getDistinctFacilities() {
        return new ResponseEntity<>(repository.findDistinctFacility(), HttpStatus.OK);
    }
}
