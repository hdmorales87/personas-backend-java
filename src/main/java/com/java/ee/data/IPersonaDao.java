package com.java.ee.data;

import com.java.ee.domain.Persona;
import java.util.List;


public interface IPersonaDao {
    
    public List<Persona> encontrarTodasPersonas();
    
    public Persona encontrarPersona(Persona persona);
    
    public void insertarPersona(Persona persona);
    
    public void actualizarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);    
    
}
