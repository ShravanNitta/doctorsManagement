package interview.assignment.patients.repositories;

import interview.assignment.patients.entities.DoctorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<DoctorsEntity,String> {
}
