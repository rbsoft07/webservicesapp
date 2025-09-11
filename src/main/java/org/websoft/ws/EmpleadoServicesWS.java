/**
 * 
 */
package org.websoft.ws;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.websoft.entity.Empleado;
import org.websoft.services.EmpleadoServices;

/**
 * @author rbsoft
 * WebServices creado usando la dependecia Jersey
 */
@Path("/empleado")
public class EmpleadoServicesWS {
	
	private EmpleadoServices empservices = new  EmpleadoServices();

	@GET
	@Path("/test")
	public String test() {
		return "Mostrado dato test del Web Services";
	}
	
	@GET
	@Path("consultaEmpleado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Empleado consultaEmpleado() {
		return empservices.consultarEmpleado();
	}
	
	@GET
	@Path("empleados")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Empleado> empleados() {
		return empservices.lstEmpleados();
	}
	
	@GET
	@Path("empleados/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Empleado empleadoId(@PathParam("id") String idEmp) {
		return empservices.consultarEmpleadoId(idEmp);
	}
	
	
}
