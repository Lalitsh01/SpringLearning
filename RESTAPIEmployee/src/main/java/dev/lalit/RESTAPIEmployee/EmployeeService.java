// EmployeeService.java
package dev.lalit.RESTAPIEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @SuppressWarnings("unused")
	@Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> employeeList;

    public EmployeeService() {
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Lalit", "MCA"));
        employeeList.add(new Employee(2, "Vivek", "MCA"));
        employeeList.add(new Employee(3, "XYZ", "MCA"));
    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream()
            .filter(employee -> employee.getId() == id.intValue())
            .findFirst();
    }

    public Employee saveEmployee(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        employeeList.removeIf(employee -> employee.getId() == id.intValue());
    }
}