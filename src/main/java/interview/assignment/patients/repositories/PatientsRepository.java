package interview.assignment.patients.repositories;

import interview.assignment.patients.entities.PatientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<PatientsEntity,String> {
}
