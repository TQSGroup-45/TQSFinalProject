package adress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adress.api.ClientRepository;
import adress.model.Client;

@Service
public class ClientService {
    
    @Autowired
    ClientRepository clientRepository;

    public Client createClient(Client c){
        return null;
    }
}
