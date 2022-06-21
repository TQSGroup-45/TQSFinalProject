package adress.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import adress.dto.ClientDTO;
import adress.model.Client;



@SpringBootApplication
public class ClientClientDTOMapperTest {
    
    private ClientClientDTOMapper mapper = Mappers.getMapper(ClientClientDTOMapper.class);
    
    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        Client client = new Client();
        client.setName("Manel");
        ClientDTO destination = mapper.clientToClientDTO(client);

        assertEquals(client.getName(), destination.getName());
    }
    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Manel");
        Client client = mapper.clientDTOToClient(clientDTO);
        assertEquals(clientDTO.getName(), client.getName());
    }
}
