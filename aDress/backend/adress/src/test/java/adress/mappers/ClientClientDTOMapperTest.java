package adress.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootApplication
public class ClientClientDTOMapperTest {
    
    @Test
    void testMapper(){

        assertThat( ClientClientDTOMapper.MAPPER ).isNotNull();
    }
}
