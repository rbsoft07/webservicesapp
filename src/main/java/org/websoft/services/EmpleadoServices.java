/**
 * 
 */
package org.websoft.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.websoft.entity.Empleado;

/**
 * 
 */
public class EmpleadoServices {

	public Empleado consultarEmpleadoId(String id) {
		
		List<Empleado> lstEmpResult = this.lstEmpleados();
		
		for (Empleado emplado : lstEmpResult) {
			if (emplado.getNumeroEmpleado().equals(id)) {
				return emplado;
			}
			
		}
		
		return null;
	}
	
	
	
	public Empleado consultarEmpleado() {
		
		Empleado emp = new Empleado();
		emp.setNumeroEmpleado("F-56713");
		emp.setNombre("Robert");
		emp.setPrimerApellido("Baez");
		emp.setSegundoApellido("Asuncion");
		emp.setEdad(34);
		emp.setFechaCreacion(LocalDateTime.now());
		
		return emp;
	}
	

	
	public List<Empleado> lstEmpleados(){
		List<Empleado> lstEmpleadoResult = new ArrayList();
		
		Empleado emp = new Empleado();
		emp.setNumeroEmpleado("F-56713");
		emp.setNombre("Robert");
		emp.setPrimerApellido("Baez");
		emp.setSegundoApellido("Asuncion");
		emp.setEdad(34);
		emp.setFechaCreacion(LocalDateTime.now());
		
		
		Empleado emp2 = new Empleado();
		emp2.setNumeroEmpleado("F-785");
		emp2.setNombre("Rafael");
		emp2.setPrimerApellido("Mateo");
		emp2.setSegundoApellido("German");
		emp2.setEdad(32);
		emp2.setFechaCreacion(LocalDateTime.now());
		
		lstEmpleadoResult.add(emp);
		lstEmpleadoResult.add(emp2);
		
		return lstEmpleadoResult;
		
	}

}
