package adress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adress.model.Client;
import adress.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client ){
        return null;

    }
}
