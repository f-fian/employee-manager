package tech.getarrays.employeemanager.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.Exeption.UserNotFoundExeption;
import tech.getarrays.employeemanager.Model.Employee;
import tech.getarrays.employeemanager.Model.EmployeeDTO;
import tech.getarrays.employeemanager.Repo.EmployeeRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(()-> new UserNotFoundExeption("user is not found"));
    }
    public List<Employee> findAllEmployee(){
        return employeeRepository.findAll();
    }
    public List<EmployeeDTO> findAllEmployeeDto(){
        return employeeRepository.findAll().stream()
                .map(person->new EmployeeDTO(
                        person.getName(),
                        person.getEmail()+" "+person.getImageUrl(),
                        person.getJobTitle()))
                .collect(Collectors.toList());
    }
    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }
}
