package com.retailstorediscount;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailstorediscount.controller.DiscountController;
import com.retailstorediscount.model.Bill;
import com.retailstorediscount.model.Item;
import com.retailstorediscount.model.User;
import com.retailstorediscount.model.UserType;
import com.retailstorediscount.service.DiscountService;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountService discountService;

    private Bill testBill;

    @BeforeEach
    public void setUp() {
        User user = new User("John",UserType.OTHER, LocalDate.now());
        

        Item item1 = new Item("item1", 100, false);
        Item item2 = new Item("item2", 200, false);
        Item item3 = new Item("grocery", 50, true);

        testBill = new Bill(user, Arrays.asList(item1, item2, item3));
    }

    @Test
    public void calculateNetPayableAmount() throws Exception {
        Mockito.when(discountService.calculateNetPayableAmount(any(Bill.class))).thenReturn(210.0);

        String billJson = "{\n" +
                "  \"user\": {\n" +
                "    \"userType\": \"EMPLOYEE\"\n" +
                "  },\n" +
                "  \"items\": [\n" +
                "    {\"name\": \"item1\", \"price\": 100, \"isGrocery\": false},\n" +
                "    {\"name\": \"item2\", \"price\": 200, \"isGrocery\": false},\n" +
                "    {\"name\": \"grocery\", \"price\": 50, \"isGrocery\": true}\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/discount/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(billJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("210.0"));
    }
}
