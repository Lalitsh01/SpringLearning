//EmployeeController.java
package dev.lalit.RESTAPIEmployee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
 @Autowired
 private EmployeeService employeeService;

 @GetMapping
 public List<Employee> getAllEmployees() {
     return employeeService.getAllEmployees();
 }

 @GetMapping("/{id}")
 public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
     Optional<Employee> employee = employeeService.getEmployeeById(id);
     return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
 }

 @PostMapping
 public Employee createEmployee(@RequestBody Employee employee) {
     return employeeService.saveEmployee(employee);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
     Optional<Employee> employee = employeeService.getEmployeeById(id);
     if (employee.isPresent()) {
         Employee updatedEmployee = employee.get();
         updatedEmployee.setName(employeeDetails.getName());
         updatedEmployee.setRole(employeeDetails.getRole());
         return ResponseEntity.ok(employeeService.saveEmployee(updatedEmployee));
     } else {
         return ResponseEntity.notFound().build();
     }
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
     employeeService.deleteEmployee(id);
     return ResponseEntity.noContent().build();
 }
}