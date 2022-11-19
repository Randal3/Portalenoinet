package com.Portalenoinet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
public class provaController {
    
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/SimService")
    public ObjectNode simService(@RequestBody ObjectNode node) {

        ObjectNode prova = objectMapper.createObjectNode();

        prova.put( "retCode",0);
        prova.put( "retMsg", "Errore associato ai parametri del Vas");

      return prova;
    }

}
