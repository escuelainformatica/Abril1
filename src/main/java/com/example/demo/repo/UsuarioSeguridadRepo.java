
package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entidad.UsuarioSeguridad;

public interface UsuarioSeguridadRepo extends CrudRepository<UsuarioSeguridad, String>{
    
}
