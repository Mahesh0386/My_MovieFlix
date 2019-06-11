package io.bhannur.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.bhannur.api.APIConfig;
import io.bhannur.api.entity.EntertainmentItem;
import io.bhannur.api.service.EntertainmentItemService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {APIConfig.class})
@WebAppConfiguration
public class EntertainmentControllerIntegrationTest {

    @Resource
    private WebApplicationContext wac;

    @Autowired
    EntertainmentItemService entertainmentItemService;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getAll_returnsAllMovies_withStatusOk() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/entItem/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        JSONArray responseArray = new JSONArray(result.getResponse().getContentAsString());
        JSONObject actual = (JSONObject) responseArray.get(0);
        assertEquals("The Godfather X", actual.get("title"));
    }

    @Test
    public void getByID_returnsOneMovie_withStatusOk() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/entItem/tt006864655"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        JSONObject actual = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("The Godfather X", actual.get("title"));
    }

    @Test
    public void deleteItem_deletesSuccessfully_withStatusOk() throws Exception {
        EntertainmentItem sample = EntertainmentItem.builder().imdbId("121").title("Test Movie").build();
        entertainmentItemService.createItem(sample);
        this.mockMvc.perform(delete("/entItem/121"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateItem_updatesMovieDetailsSuccessfully_withStatusOk() throws Exception {
        EntertainmentItem sample = EntertainmentItem.builder().imdbId("111").title("Test Movie").build();
        entertainmentItemService.createItem(sample);
        sample.setCountry("USA");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(sample);


        MvcResult result = mockMvc.perform(put("/entItem/111").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("USA"));
        entertainmentItemService.deleteItem("111");
    }

}
