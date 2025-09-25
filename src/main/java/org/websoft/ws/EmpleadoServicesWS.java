/**
 * 
 */
package org.websoft.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.websoft.entity.Empleado;
import org.websoft.services.EmpleadoServices;

/**
 * @author rbsoft WebServices creado usando la dependecia Jersey
 */
@Path("/empleado")
public class EmpleadoServicesWS {

	private EmpleadoServices empservices = new EmpleadoServices();

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

	@GET
	@Path("empleadosPorId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response empleadoById(@PathParam("id") String idEmp) {
		Empleado objEmpleado = empservices.consultarEmpleadoId(idEmp);

		if (objEmpleado == null) {
			return Response.noContent().build();

		}
		
		GenericEntity<Empleado> empleadoGeneric = new GenericEntity<Empleado>(objEmpleado, Empleado.class);
		
		return Response.ok(empleadoGeneric).build();
	}

	@POST
	@Path("guardarEmpleado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarEmpleado(Empleado empleado) {
		
		if (empleado == null) {
			return Response.status(400).entity("No se ingresó informacion del empleado, favor verificar los datos ").build();
		}
		
		
		if (empleado.getNumeroEmpleado() == null || empleado.getNumeroEmpleado().isEmpty()) {
			return Response.status(400).entity("El Numero Empleado es Requerido").build();
		}
		
		if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
			return Response.status(400).entity("El nombre es Requerido").build();
		}
		
		GenericEntity<Empleado> empleadoGenericResult = new GenericEntity<Empleado>(empleado, Empleado.class);
		return Response.ok(empleadoGenericResult).build();
	}
	
	
}
