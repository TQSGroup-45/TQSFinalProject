package adress.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import adress.dto.ClientDTO;
import adress.model.Client;



@SpringBootApplication
class ClientClientDTOMapperTest {
    
    private ClientClientDTOMapper mapper = Mappers.getMapper(ClientClientDTOMapper.class);
    
    @Test
    void givenSourceToDestination_whenMaps_thenCorrect() {
        Client client = new Client();
        client.setName("Manel");
        client.setCity("Aveiro");
        client.setDob("1-1-2021");
        client.setSname("José");
        client.setSnum("123");
        client.setPc1(1);
        client.setPc2(1);

        ClientDTO clientDTO = mapper.clientToClientDTO(client);

        assertEquals(client.getName(), clientDTO.getName());
        assertEquals(client.getCity(), clientDTO.getCity());
        assertEquals(client.getDob(), clientDTO.getDob());
        assertEquals(client.getPc1(), clientDTO.getPc1());
        assertEquals(client.getPc2(), clientDTO.getPc2());
        assertEquals(client.getSname(), clientDTO.getSname());
        assertEquals(client.getSnum(), clientDTO.getSnum());
    }
    @Test
    void givenDestinationToSource_whenMaps_thenCorrect() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Manel");
        clientDTO.setCity("Aveiro");
        clientDTO.setDob("1-1-2021");
        clientDTO.setSname("José");
        clientDTO.setSnum("123");
        clientDTO.setPc1(1);
        clientDTO.setPc2(1);

        Client client = mapper.clientDTOToClient(clientDTO);
        
        assertEquals(client.getName(), clientDTO.getName());
        assertEquals(client.getCity(), clientDTO.getCity());
        assertEquals(client.getDob(), clientDTO.getDob());
        assertEquals(client.getPc1(), clientDTO.getPc1());
        assertEquals(client.getPc2(), clientDTO.getPc2());
        assertEquals(client.getSname(), clientDTO.getSname());
        assertEquals(client.getSnum(), clientDTO.getSnum());    }
}
