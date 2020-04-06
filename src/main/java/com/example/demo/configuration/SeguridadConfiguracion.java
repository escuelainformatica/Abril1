
package com.example.demo.configuration;

import com.example.demo.entidad.UsuarioSeguridad;
import com.example.demo.repo.UsuarioSeguridadRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // <-- Que significa? Cuando parte Spring boot, carga esta clase para "configurar" los parametros.
@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
    private UsuarioSeguridadRepo usec;
    
    	@Override
	protected void configure(HttpSecurity http) throws Exception {
            // esto se carga al comienzo y es la configuracion x pagina web.
            
            http
                    .authorizeRequests()
                            .antMatchers("/", "/inicio").hasAnyRole("USER","ADMIN")
                            .antMatchers("/admin").hasAnyRole("ADMIN")
                            .anyRequest().authenticated()
                            .and()
                    .formLogin()
                            .loginPage("/login").permitAll()
                            .defaultSuccessUrl("/inicio")
                            .and()
                    .logout() // th:action="@{/logout}"
                            .logoutSuccessUrl("/login")
                            .permitAll();
            
        }
    
	@Bean // Bean Manejado (CDI), EJB (Bean Empresariales, E. Java Beans)
	@Override
	public UserDetailsService userDetailsService() {
            
            System.out.println("Creando usuario --------------------------");
            
            List<UsuarioSeguridad> listado=(List<UsuarioSeguridad>)usec.findAll();
            
            List<UserDetails> listadoDetalles=new ArrayList<>();
            
            for(UsuarioSeguridad usuario:listado) {
                 UserDetails detail=User.withDefaultPasswordEncoder() 
                                    .username( usuario.getUsuario() )
                                    .password( usuario.getClave() )
                                    .roles( usuario.getRol() )
                                    .build();
                listadoDetalles.add(detail);
                
            }
            
        
            return new InMemoryUserDetailsManager(listadoDetalles); 

        }
    
    
}
