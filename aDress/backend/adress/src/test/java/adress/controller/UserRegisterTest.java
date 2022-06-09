package adress.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import adress.model.Client;
import adress.service.AppService;
import adress.JsonUtils;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AppController.class)
public class UserRegisterTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppService appService;

    @Test
    void whenPostClient_thenCreateClient() throws IOException, Exception{
        Client manuel = new Client();

        when( appService.createClient(manuel)).thenReturn(manuel);

        mvc.perform(
            post("/api/clients").contentType(MediaType.APPLICATION_JSON).content( JsonUtils.toJson(manuel) ) )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is("manuel")));
        
        verify(appService, times(1)).createClient(manuel);
    }

    @Test
    void whenPostClientWithRepeatedEmail_thenClienNotCreated() throws IOException, Exception{
        Client manuel = new Client();

        when( appService.createClient(manuel)).thenReturn(new Client()) ;

        mvc.perform(
            post("/api/clients").contentType(MediaType.APPLICATION_JSON).content( JsonUtils.toJson(manuel) ) )
            .andExpect(status().isConflict());
        
        verify(appService, times(0)).createClient(manuel);
    }
}
