package adress;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.mashape.unirest.http.exceptions.UnirestException;

import adress.api.ClientRepository;
import adress.model.Client;

import java.io.IOException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureTestDatabase
class ClientControllerIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void resetBeforeDb() {
        clientRepository.deleteAll();
    }

    @AfterEach
    public void resetAfterDb() {
        clientRepository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateClient() throws UnirestException {
        Client manuel = new Client("Manuel", "1-1-2021", "123", "manuel", 1, 1, "Aveiro", "manuel@gmail.com");
        restTemplate.postForEntity("/api/v1/clients", manuel, Client.class);

        System.out.println(clientRepository.findAll());
        List<Client> clientsFound = clientRepository.findAll();
        assertThat(clientsFound).hasSize(1);
    }

    @Test
    public void whenInvalidInput_thenNotCreateClient() throws UnirestException {
        Client manuel = new Client("Manuel", "1-1-2021", "123", "manuel", 0, 1, "Aveiro", "manuel@gmail.com");

        ResponseEntity<Client> entity = restTemplate.postForEntity("/api/v1/clients", manuel, Client.class);
        System.out.println(entity);
        assertThat(entity.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    @Test
    void whenPostClientWithRepeatedEmail_thenClienNotCreated() throws IOException, Exception {
        Client manuel = new Client("Manuel", "1-1-2021", "123", "manuel", 1, 1, "Aveiro", "manuel@gmail.com");
        Client manuel2 = new Client("Manuel Gaspar", "1-1-2021", "123", "manuel", 0, 1, "Aveiro", "manuel@gmail.com");

        clientRepository.save(manuel);

        ResponseEntity<Client> entity = restTemplate.postForEntity("/api/v1/clients", manuel2, Client.class);

        assertThat(entity.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
