package com.ivanfranchin.orderapi;

import com.ivanfranchin.orderapi.order.OrderService;
import com.ivanfranchin.orderapi.rest.PublicController;
import com.ivanfranchin.orderapi.security.SecurityConfig;
import com.ivanfranchin.orderapi.security.TokenProvider;
import com.ivanfranchin.orderapi.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicController.class)
@Import(SecurityConfig.class)
class OrderApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private TokenProvider tokenProvider;

    @Test
    void publicEndpointLoads() throws Exception {
        when(userService.countUsers()).thenReturn(0L);

        mockMvc.perform(get("/public/numberOfUsers"))
                .andExpect(status().isOk());
    }
}
