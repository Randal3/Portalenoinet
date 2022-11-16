package com.Portalenoinet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Portalenoinet.model.*;


@Controller
public class PageController {

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
		Utente user = credentials.getUser();
        model.addAttribute("user",user);
        model.addAttribute("Sim",this.simservice.all());
        model.addAttribute("role",credentials.getRole());
        return "dashboard";
    }
}
