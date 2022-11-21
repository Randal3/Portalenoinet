package com.Portalenoinet.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Portalenoinet.model.Credentials;
import com.Portalenoinet.model.Sim;
import com.Portalenoinet.model.Utente;
import com.Portalenoinet.service.CredentialsService;
import com.Portalenoinet.service.SimService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class provaController {
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CredentialsService credentialservice;

    @Autowired
    private SimService simservice;
    @PostMapping("/SimService")
    public ObjectNode simService(@RequestBody ObjectNode node) {

        ObjectNode prova = objectMapper.createObjectNode();

        prova.put( "retCode",0);
        prova.put( "retMsg", "Errore associato ai parametri del Vas");
        prova.put( "AreaCode","AREA51");

      return prova;
    }

    @PostMapping("/salvataggioSim")
    public void salvataggioSim(@RequestBody ObjectNode node) {

      UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
      Utente user = credentials.getUser();


      Sim nuovaSim = new Sim();
      nuovaSim.setSeriale(node.get("IdSim").asText());
      nuovaSim.setTipoServizio(node.get("SrvType").asText());
      nuovaSim.setIdRecord(node.get("IdRecord").asText());
      nuovaSim.setStato(node.get("State").asText());
      nuovaSim.setAreaCode(node.get("AreaCode").asText());
      nuovaSim.setAreaCode(node.get("AreaCode").asText());
      nuovaSim.setOperatore(user);
      //nuovaSim.setData(new Date());

      simservice.save(nuovaSim);
    }

    @GetMapping("/getAllSim")
    public List<Sim> getAllSim() {

      List<Sim> data = simservice.all();

      return data;
    }
}
