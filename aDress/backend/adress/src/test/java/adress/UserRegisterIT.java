package adress;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import adress.api.ClientRepository;
import adress.model.Client;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureTestDatabase
//@TestPropertySource( locations = "application-integrationtest.properties")
public class UserRegisterIT {
    
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    public void resetDb(){
        clientRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateClient(){
        Client manuel = new Client();
        ResponseEntity<Client> entity = restTemplate.postForEntity("/api/clients", manuel, Client.class);

        List<Client> clientsFound = clientRepository.findAll();
        assertThat( clientsFound.stream().anyMatch(c -> c.getName().equals(manuel.getName())));
    }

    @Test
    public void whenInvalidInput_thenCreateClient(){
        Client manuel = new Client();
        ResponseEntity<Client> entity = restTemplate.postForEntity("/api/clients", manuel, Client.class);

        List<Client> clientsFound = clientRepository.findAll();
        assertThat( clientsFound ).isEmpty();;
    }
}
