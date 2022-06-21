package adress.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import adress.dto.ClientDTO;
import adress.model.Client;

@Mapper
public interface ClientClientDTOMapper {

    ClientClientDTOMapper MAPPER = Mappers.getMapper(ClientClientDTOMapper.class );

    ClientDTO clientToClientDTO(Client client);
    Client clientDTOToClient(ClientDTO clientDTO);
}
