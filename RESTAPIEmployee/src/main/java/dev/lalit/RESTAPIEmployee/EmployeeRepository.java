//EmployeeRepository.java

// EmployeeRepository.java
package dev.lalit.RESTAPIEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository<Int> extends JpaRepository<Employee,Int> {
	
}