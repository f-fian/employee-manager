package tech.getarrays.employeemanager.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.Model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findEmployeeById(Long id);
    void deleteEmployeeById(Long id);
}
