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

import adress.api.ClientRepository;
import adress.model.Client;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureTestDatabase
public class ClientIT {
    
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClientRepository clientRepository;


    @BeforeEach
    public void resetBeforeDb(){
        clientRepository.deleteAll();
    }

    @AfterEach
    public void resetAfterDb(){
        clientRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateClient(){
        Client manuel = new Client( "Manuel",  "1-1-2021", "123", "manuel", 1, 1, "Aveiro");
        restTemplate.postForEntity("/api/v1/clients", manuel, Client.class);

        System.out.println(clientRepository.findAll());
        List<Client> clientsFound = clientRepository.findAll();
        assertThat( clientsFound).hasSize(1);
    }

/*     @Test
    public void whenInvalidInput_thenNotCreateClient(){
        Client manuel = new Client( "Manuel",  "1-1-2021", "123", "manuel", 1, 1, "Aveiro");
        
        Client manuel2 = new Client( "Manuel",  "1-1-2021", "123", "manuel", 1, 1, "Aveiro");

        clientRepository.save(manuel);

        ResponseEntity<Client> entity = restTemplate.postForEntity("/api/v1/clients", manuel, Client.class);

        List<Client> clientsFound = clientRepository.findAll();
        assertThat( clientsFound ).containsOnly(manuel);
    } */

    /* @Test
    void whenPostClientWithRepeatedEmail_thenClienNotCreated() throws IOException, Exception{
        Client manuel = new Client( "Manuel",  "1-1-2021", "123", "manuel", 1, 1, "Aveiro");

        ResponseEntity<Client> entity = restTemplate.postForEntity("/api/v1/clients", manuel, Client.class);

        mvc.perform(
            post("/api/v1/clients").contentType(MediaType.APPLICATION_JSON).content( JsonUtils.toJson(manuel) ) )
            .andExpect(status().isConflict());
        
        verify(appService, times(0)).createClient(manuel);
    } */
}
