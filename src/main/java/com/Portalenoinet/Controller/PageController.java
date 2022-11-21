package com.Portalenoinet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Portalenoinet.model.*;
import com.Portalenoinet.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialsService credentialservice;

    @Autowired
    private SimService simservice;


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
		Utente user = credentials.getUser();
        model.addAttribute("user", user);
        model.addAttribute("Sim",this.simservice.all());
        model.addAttribute("role",credentials.getRole());
        return "dashboard";
    }


    @GetMapping(value="/copertura")
    public String Copertura() {
        return "copertura";
    }

    @GetMapping(value="/gestioneSim")
    public String gestioneSim() {
        return "gestioneSim";
    }

    @RequestMapping(value =  "/gestioneSim/{areaCode}" , method = RequestMethod.GET)
     public String gestioneSim(@PathVariable("areaCode") String id, Model model) {
        model.addAttribute("id", id);
        return "gestioneSim";
     }

    @GetMapping(value="/tableSim")
    public String tableSim() {
        return "tableSim";
    }
    
    
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String NewUser(Model model) {
        Utente u = new Utente();
        u.setCognome("Admin");
        u.setNome("user");
        Credentials c = new Credentials();
        c.setRole("ADMIN");
        c.setUser(u);
        c.setUsername("Admin");
        c.setPassword(this.passwordEncoder.encode("admin"));
        this.credentialservice.save(c);
        return "dashboard";
    }
}
