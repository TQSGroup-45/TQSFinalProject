
package adress.controller;

// import org.springframework.http.MediaType;
// import org.mockito.Mockito;
// import org.springframework.test.web.servlet.MockMvc;

// import adress.service.AppService;

// import static org.hamcrest.CoreMatchers.is;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import static org.mockito.Mockito.*;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.beans.factory.annotation.Autowired;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.List;
// import java.util.ArrayList;

// @WebMvcTest(AppController.class)
class AppControllerTest {

    // List<ContinentStats> continent;
    // List<CountryStats> country;
    // @Autowired
    // private MockMvc mvc;

    // @MockBean
    // private AppService service;

    // @BeforeEach
    // public void setUp() throws Exception {

    // }

    // @Test
    // void whenGetContinentInfo_testGetContinentInfo() throws Exception {
    // when(service.getContinentInfo(Mockito.any())).thenReturn(continent);
    // mvc.perform(get("/api/v1/continent/europe").contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(jsonPath("$[0].country", is("Portugal")))
    // .andExpect(jsonPath("$[1].rank", is(2)))
    // .andExpect(jsonPath("$[2].tcases", is(15)));
    // verify(service, times(1)).getContinentInfo(Mockito.any());
    // }

    // @Test
    // void whenGetWorldInfo_testGetWorldInfo() throws Exception {
    // when(service.getWorldInfo()).thenReturn(continent);
    // mvc.perform(get("/api/v1/continent/world").contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(jsonPath("$[0].tcases", is(10)))
    // .andExpect(jsonPath("$[1].country", is("Spain")))
    // .andExpect(jsonPath("$[2].tdeaths", is(5)));
    // verify(service, times(1)).getWorldInfo();
    // }

    // @Test
    // void whenGetCountryInfo_testGetCountryInfo() throws Exception {
    // when(service.getCountryInfo(Mockito.any(),
    // Mockito.any())).thenReturn(country);
    // mvc.perform(get("/api/v1/country/portugal&date=2022-04-21").contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(jsonPath("$[0].date", is("2022-04-21")))
    // .andExpect(jsonPath("$[1].active", is(10)));
    // verify(service, times(1)).getCountryInfo(Mockito.any(), Mockito.any());
    // }

    // @Test
    // void whenGetCacheInfo_testGetCacheInfo() throws Exception {
    // when(service.getCacheInfo()).thenReturn("{Requests: 3, Hits: 1, Misses:2}");
    // mvc.perform(get("/api/v1/stats").contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk())
    // .andExpect(jsonPath("Requests", is(3)));
    // verify(service, times(1)).getCacheInfo();
    // }
}