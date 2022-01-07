package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.EmployeePostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeService es = new EmployeeService();	
	
	@InjectMocks
	private EmployeePostgres ep = new EmployeePostgres();
	
	
	@Test
	public void EmployeeServiceTestValid() throws LoginException {
		Mockito.when(es.empLogin("uname", "pword")).thenReturn(ep.getById(14));
		
		Employee expected = ep.getById(14);
		Employee actual = es.empLogin("uname", "pword");
		assertEquals(expected, actual);
	}
	
	public void AddEmployeeValid() {
		
		Employee expected = es.addEmployee(sc);
		Employee actual = es.addEmployee(null);
		
	}

}
