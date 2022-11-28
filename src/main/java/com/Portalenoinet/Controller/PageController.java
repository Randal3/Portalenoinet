package com.Portalenoinet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Portalenoinet.model.*;
import com.Portalenoinet.service.*;
import com.Portalenoinet.validator.CredentialsValidator;
import com.Portalenoinet.validator.UtenteValidator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialsService credentialservice;

    @Autowired
	private CredentialsValidator credentialsValidator;

    @Autowired
	private UtenteValidator userValidator;


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
		Utente user = credentials.getUser();
        model.addAttribute("user", user);
        System.out.println("SONO QUI " + user.getCognome());
        return "dashboard";
    }

    @GetMapping(value="/index")
    public String index() {
        return "index";
    }

    @GetMapping(value="/copertura")
    public String copertura(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
		Utente user = credentials.getUser();
        model.addAttribute("user", user);
        return "copertura";
    }

    @GetMapping(value="/gestioneSim")
    public String gestioneSim(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
		Utente user = credentials.getUser();
        model.addAttribute("user", user);
        return "gestioneSim";
    }

    @RequestMapping(value =  "/gestioneSim/{areaCode}" , method = RequestMethod.GET)
     public String gestioneSim(@PathVariable("areaCode") String id, Model model) {
        model.addAttribute("id", id);
        return "gestioneSim";
     }

    @GetMapping(value="/tableSim")
    public String tableSim(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
		Utente user = credentials.getUser();
        model.addAttribute("user", user);
        return "tableSim";
    }
    
    @GetMapping(value="/gestioneOperatori")
    public String gestioneOperatori(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
		Utente user = credentials.getUser();
        model.addAttribute("user", user);


        Utente utente = new Utente();
		Credentials credenziali = new Credentials();
		model.addAttribute("utente", utente);
		model.addAttribute("credentials", credenziali);
        return "gestioneOperatori";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("utente") Utente utente, BindingResult userBindingResult, @ModelAttribute("credentials") Credentials credenziali, BindingResult credentialsBindingResult, Model model) {
    
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
            Utente user = credentials.getUser();
            model.addAttribute("user", user);

            this.userValidator.validate(utente, userBindingResult);
            this.credentialsValidator.validate(credenziali, credentialsBindingResult);
            if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
                credenziali.setUser(utente);
                credentialservice.saveCredentials(credenziali);
                return "dashboard";
            }
            return "gestioneOperatori";
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
