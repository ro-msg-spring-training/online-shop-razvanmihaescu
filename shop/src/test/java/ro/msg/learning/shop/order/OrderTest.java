package ro.msg.learning.shop.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.msg.learning.shop.dtos.OrderDto;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "TEST_CUSTOMER", password = "1", roles = "CUSTOMER")
    public void createOrderSuccessfully() throws Exception {
        OrderDto orderDto = objectMapper.readValue(new File("src\\test\\java\\ro\\msg\\learning\\shop\\order\\successfull_order.json"), OrderDto.class);
        String body = objectMapper.writeValueAsString(orderDto);

        this.mockMvc.perform(post("/Orders").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated());
    }

    @Test
    public void failToCreateOrderDueToMissingStock() throws Exception {
        OrderDto orderDto = objectMapper.readValue(new File("src\\test\\java\\ro\\msg\\learning\\shop\\order\\failure_order.json"), OrderDto.class);
        String body = objectMapper.writeValueAsString(orderDto);

        this.mockMvc.perform(post("/Orders").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().is4xxClientError());
    }
}
