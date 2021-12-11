package com.java.ee.service;

import com.java.ee.data.IPersonaDao;
import com.java.ee.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Stateless
@Path("/personas")
public class PersonaServiceRS {
    
    @Inject
    private IPersonaDao iPersonaDao;
    
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    public List<Persona> listarPersonas(){
        List<Persona> personas = iPersonaDao.encontrarTodasPersonas();
        System.out.println("Personas encontradas: " + personas);
        return personas;
    }
    
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("{id}") //hace referencia al path: /personas/{id}
    public Persona encontrarPersona(@PathParam("id") int id){
        Persona persona = iPersonaDao.encontrarPersona(new Persona(id));
        System.out.println("Persona encontrada: " + persona);
        return persona;
    }
    
    @POST
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona persona){
        iPersonaDao.insertarPersona(persona);
        System.out.println("Persona agregada: " + persona);
        return persona;
    }
    
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("{id}") //hace referencia al path: /personas/{id}
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
        Persona persona = iPersonaDao.encontrarPersona(new Persona(id));
        if(persona != null){
            iPersonaDao.actualizarPersona(personaModificada);
            System.out.println("Persona modificada: " + personaModificada);
            return Response.ok().entity(personaModificada).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        } 
    }
    
    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarPersona(@PathParam("id") int id){
        iPersonaDao.eliminarPersona(new Persona(id));
        System.out.println("Persona eliminada con el id: " + id);
        return Response.ok().build();
    }    
    
}
