package org.example.rentproxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.PostResponse;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PostServiceImplTest extends PostServiceBaseTest {
    @MockBean
    private CurrencyService currencyService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCorrectCurrencyAndValueInGetPostResponse() throws Exception {
        PostDto savedPost = createPost();
        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostLogin",
                "findPostPassword");
        UserParameter userParameter = createUserParameter(authenticatedUser.getId());

        when(currencyService.convertCurrency(
                        savedPost.getRentConditionInfoDto().getCurrency(),
                        userParameter.getParamValue(),
                        savedPost.getRentConditionInfoDto().getPrice()
                )
        ).thenReturn(ApiLayerResponse.builder().result(100.0).build());

        when(currencyService.convertCurrency(
                savedPost.getRentConditionInfoDto().getCurrency(),
                userParameter.getParamValue(),
                savedPost.getRentConditionInfoDto().getDeposit())
        ).thenReturn(ApiLayerResponse.builder().result(10.0).build());

        WithIdRequest withIdRequest = new WithIdRequest();
        withIdRequest.setId(savedPost.getId());

        MvcResult result = mockMvc.perform(post("/rent-proxy/post/get")
                        .content(objectMapper.writeValueAsString(withIdRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("findPostLogin", "findPostPassword")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedPost.getId()))
                .andExpect(jsonPath("$.rentConditionInfoDto.deposit").value(10.0))
                .andExpect(jsonPath("$.rentConditionInfoDto.price").value(100.0))
                .andExpect(jsonPath("$.rentConditionInfoDto.currency").value("USD"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        PostResponse postResponse = objectMapper.readValue(content, PostResponse.class);

        assertEquals(savedPost.getId(), postResponse.getId());
        assertEquals(
                savedPost.getRentConditionInfoDto().getDeposit(),
                postResponse.getRentConditionInfoResponse().getDeposit()
        );
        assertEquals(
                savedPost.getRentConditionInfoDto().getPrice(),
                postResponse.getRentConditionInfoResponse().getPrice()
        );
        assertEquals(
                savedPost.getRentConditionInfoDto().getCurrency(),
                postResponse.getRentConditionInfoResponse().getCurrency()
        );
    }

    @Test
    void getCorrectCurrencyAndValueInGetPostsByFilterResponse() {
        PostDto savedPost = createPost();
        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostLogin",
                "findPostPassword");
        UserParameter userParameter = createUserParameter(authenticatedUser.getId());


    }

    @Test
    void findPostByIdWhenUserIsAuthenticated() {
        PostDto savedPost = createPost();
        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostLogin",
                "findPostPassword");
        UserParameter userParameter = createUserParameter(authenticatedUser.getId());

        when(currencyService.convertCurrency(
                        savedPost.getRentConditionInfoDto().getCurrency(),
                        userParameter.getParamValue(),
                        savedPost.getRentConditionInfoDto().getPrice()
                )
        ).thenReturn(ApiLayerResponse.builder().result(100.0).build());

        when(currencyService.convertCurrency(
                        savedPost.getRentConditionInfoDto().getCurrency(),
                        userParameter.getParamValue(),
                        savedPost.getRentConditionInfoDto().getDeposit())
        ).thenReturn(ApiLayerResponse.builder().result(10.0).build());

        PostDto actualPost = postService.findPostById(authenticatedUser.getLogin(), savedPost.getId());

        assertEquals(100.0, actualPost.getRentConditionInfoDto().getPrice());
        assertEquals(10.0, actualPost.getRentConditionInfoDto().getDeposit());
    }
}