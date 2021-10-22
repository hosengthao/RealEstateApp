package net.yorksolutions.realestate;

import net.yorksolutions.realestate.controller.RealEstateController;
import net.yorksolutions.realestate.model.RealEstate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.RequestParam;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RealestateApplicationTests {
    @Autowired
    private RealEstateController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    int num = (int) (Math.random()*99999);

    @Test
    void realEstateControllerLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void realEstateGetAll() {
        RealEstate[] realEstates = restTemplate.getForObject("http://localhost:" + port + "/realestate/all", RealEstate[].class);
        assertThat(realEstates).isNotNull();
        assertThat(realEstates).isNotEmpty();
    }

    @Test
    void realEstateGetByRows() {
        RealEstate[] realEstatesRows = restTemplate.getForObject("http://localhost:" + port + "/realestate/getByRowAmount?rows=" + num, RealEstate[].class);
        assertThat(realEstatesRows).isNotNull();
        assertThat(realEstatesRows).isNotEmpty();
    }

    @Test
    void searchTest() {
        String baseURL = "http://localhost:" + port + "/realestate/";
        RealEstate[] realEstatesRows = restTemplate.getForObject(baseURL + "search?fname=Hoseng", RealEstate[].class);
        assertThat(realEstatesRows).isNotNull();

        RealEstate realEstate = new RealEstate();
        realEstate.setFname("Hoseng");
        String response = restTemplate.postForObject(baseURL + "add", realEstate, String.class);
        assertThat(response).isEqualTo("success");

        realEstatesRows = restTemplate.getForObject(baseURL + "search?fname=Hoseng", RealEstate[].class);
        assertThat(realEstatesRows).isNotNull();
        assertThat(realEstatesRows).hasSizeGreaterThan(0);

        for (RealEstate realEstate1 : realEstatesRows)
            assertThat(realEstate1.getFname()).isEqualTo("Hoseng");
    }

}
