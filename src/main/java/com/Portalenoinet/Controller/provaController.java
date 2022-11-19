package com.Portalenoinet.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class provaController {
    
    @PostMapping("/SimService")
    public String simService(@RequestBody ObjectNode file) {
  
        System.out.println("SONO QUI");


      return "Done";
    }

}
