package com.Portalenoinet.Controller;

import java.time.LocalDate;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Portalenoinet.model.Credentials;
import com.Portalenoinet.model.Sim;
import com.Portalenoinet.model.Utente;
import com.Portalenoinet.service.CredentialsService;
import com.Portalenoinet.service.SimService;
import com.Portalenoinet.service.UtenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.*;

@RestController
public class provaController {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CredentialsService credentialservice;

  @Autowired
  private SimService simservice;

  @Autowired
  private UtenteService utenteservice;

  @PostMapping("/SimService")
  public ObjectNode simService() {
    System.out.println("SONO SOPRA");

    ObjectNode prova = objectMapper.createObjectNode();

    prova.put("retCode", 0);
    prova.put("retMsg", "Errore associato ai parametri del Vas");
    prova.put("AreaCode", "AREA51");

    RestTemplate restTemplate = new RestTemplate();

    String url = "http://95.174.12.104:10674/SimService/Inquiry";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth("08e20755-20e5-39c1-b9bc-d14835ac2f22");

    ObjectNode requestBody = objectMapper.createObjectNode();
    requestBody.put("IdSim", "222380990002001");
    requestBody.put("IdRecord", "");

    HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

    // ResponseEntity<String> response = restTemplate.postForEntity(url, entity,
    // String.class);
    ResponseEntity<ObjectNode> response = restTemplate.exchange(url, HttpMethod.GET, entity,
        new ParameterizedTypeReference<ObjectNode>() {
        });

    ObjectNode responseBody = response.getBody();

    System.out.println("SONO QUI");
    System.out.println(responseBody);

    return prova;
  }

  @PostMapping("/salvataggioSim")
  public void salvataggioSim(@RequestBody ObjectNode node) {

    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Credentials credentials = credentialservice.getCredentials(userDetails.getUsername());
    Utente user = credentials.getUser();

    List<Sim> elencoSim = simservice.all();
    String seriale = node.get("IdSim").asText();
    boolean tmp = false;
    for (int i = 0; i < elencoSim.size(); i++) {
      if (elencoSim.get(i).getSeriale().equals(seriale)) {
        tmp = true;
      }
    }

    if (tmp) {
      // UPDATE SIM
      Sim simAttuale = simservice.getSim(seriale);

      simAttuale.setStato(node.get("State").asText());

      simservice.save(simAttuale);
    } else {
      // INSERIMENTO NUOVA SIM
      Sim nuovaSim = new Sim();
      nuovaSim.setSeriale(node.get("IdSim").asText());
      nuovaSim.setTipoServizio(node.get("SrvType").asText());
      nuovaSim.setIdRecord(node.get("IdRecord").asText());
      nuovaSim.setStato(node.get("State").asText());
      nuovaSim.setAreaCode(node.get("AreaCode").asText());
      nuovaSim.setAreaCode(node.get("AreaCode").asText());
      nuovaSim.setOperatore(user);
      nuovaSim.setData(LocalDate.now());

      simservice.save(nuovaSim);
    }
  }

  @GetMapping("/getAllSim")
  public List<Sim> getAllSim() {

    List<Sim> data = simservice.all();

    return data;
  }

  @GetMapping("/getAllOperatori")
  public List<Utente> getAllOperatori() {

    List<Utente> utente = utenteservice.all();

    return utente;
  }
}
