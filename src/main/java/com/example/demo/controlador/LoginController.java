
package com.example.demo.controlador;

import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// validacion
// web service
// JAVA EE

@Controller
// @RolesAllowed({"ROLE_ADMIN"})
// @PreAuthorize("hasRole('ADMIN')")
// @Secured("ADMIN")
public class LoginController {
    
    @Secured("ADMIN")
    @RequestMapping("/admin2")
    public String admin2(Model modelo) {
        return "admin";
    }
    
    
    @RequestMapping("/inicio")
    
    public String inicio(Model modelo) {
        
        return "inicio";
        
    }
    @RequestMapping("/login")
    public String login(Model modelo) {
        return "login";
    }
    @RequestMapping("/admin")

    public String admin(Model modelo) {
        
        return "admin";
        
    }
}
