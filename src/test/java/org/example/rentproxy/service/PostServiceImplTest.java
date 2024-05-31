package org.example.rentproxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.filter.PostOrder;
import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.PostResponse;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.example.rentproxy.service.user.UserParamName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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

    @WithMockUser(username = "findPostLogin", password = "findPostPassword", authorities = "USER")
    @Test
    void getCorrectCurrencyAndValueInGetPostResponse() throws Exception {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordGetCorrectCurrencyAndValueInGetPostResponse",
                "landlordGetCorrectCurrencyAndValueInGetPostResponse");
        PostDto savedPost = createPost(landlord, 1000.0);
        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostLogin",
                "findPostPassword");
        UserParameter defaultCurrencyParam = createUserParameter(
                authenticatedUser.getId(),
                UserParamName.DEFAULT_CURRENCY.getName(),
                "USD");
        createUserParameter(
                authenticatedUser.getId(),
                UserParamName.AUTO_CONVERSION.getName(),
                "true");

        when(currencyService.convertCurrency(
                        savedPost.getRentConditionInfoDto().getCurrency(),
                        defaultCurrencyParam.getParamValue(),
                        savedPost.getRentConditionInfoDto().getPrice()
                )
        ).thenReturn(ApiLayerResponse.builder().result(100.0).build());

        when(currencyService.convertCurrency(
                savedPost.getRentConditionInfoDto().getCurrency(),
                defaultCurrencyParam.getParamValue(),
                savedPost.getRentConditionInfoDto().getDeposit())
        ).thenReturn(ApiLayerResponse.builder().result(10.0).build());

        WithIdRequest withIdRequest = new WithIdRequest();
        withIdRequest.setId(savedPost.getId());

        MvcResult result = mockMvc.perform(post("/post/get")
                        .content(objectMapper.writeValueAsString(withIdRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedPost.getId()))
                .andExpect(jsonPath("$.rentConditionInfoResponse.deposit").value(10.0))
                .andExpect(jsonPath("$.rentConditionInfoResponse.price").value(100.0))
                .andExpect(jsonPath("$.rentConditionInfoResponse.currency").value("USD"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        PostResponse postResponse = objectMapper.readValue(content, PostResponse.class);

        assertEquals(savedPost.getId(), postResponse.getId());
        assertEquals(
                10.0,
                postResponse.getRentConditionInfoResponse().getDeposit()
        );
        assertEquals(
                100.0,
                postResponse.getRentConditionInfoResponse().getPrice()
        );
        assertEquals(
                "USD",
                postResponse.getRentConditionInfoResponse().getCurrency()
        );
    }

    @WithMockUser(username = "findPostByFilterLogin", password = "findPostByFilterPassword", authorities = "USER")
    @Test
    void getCorrectCurrencyAndValueInGetPostsByFilterResponse() throws Exception {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordGetCorrectCurrencyAndValueInGetPostsByFilterResponseLogin",
                "landlordGetCorrectCurrencyAndValueInGetPostsByFilterResponsePassword");
        PostDto savedPost1 = createPost(landlord, 0.5);
        PostDto savedPost2 = createPost(landlord, 0.7);

        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostByFilterLogin",
                "findPostByFilterPassword");

        UserParameter defaultCurrencyParam = createUserParameter(
                authenticatedUser.getId(),
                UserParamName.DEFAULT_CURRENCY.getName(),
                "USD");
        createUserParameter(
                authenticatedUser.getId(),
                UserParamName.AUTO_CONVERSION.getName(),
                "true");

        when(currencyService.convertCurrency(
                        savedPost1.getRentConditionInfoDto().getCurrency(),
                        defaultCurrencyParam.getParamValue(),
                        savedPost1.getRentConditionInfoDto().getPrice()
                )
        ).thenReturn(ApiLayerResponse.builder().result(232323.0).build());

        when(currencyService.convertCurrency(
                savedPost1.getRentConditionInfoDto().getCurrency(),
                defaultCurrencyParam.getParamValue(),
                savedPost1.getRentConditionInfoDto().getDeposit())
        ).thenReturn(ApiLayerResponse.builder().result(10.0).build());

        when(currencyService.convertCurrency(
                        savedPost2.getRentConditionInfoDto().getCurrency(),
                        defaultCurrencyParam.getParamValue(),
                        savedPost2.getRentConditionInfoDto().getPrice()
                )
        ).thenReturn(ApiLayerResponse.builder().result(323232.0).build());

        when(currencyService.convertCurrency(
                savedPost2.getRentConditionInfoDto().getCurrency(),
                defaultCurrencyParam.getParamValue(),
                savedPost2.getRentConditionInfoDto().getDeposit())
        ).thenReturn(ApiLayerResponse.builder().result(10.0).build());

        Filter filter = new Filter();
        filter.setPostOrder(PostOrder.ASCENDING_PRICE);
        filter.setMinPrice(0.5);
        filter.setMaxPrice(0.7);
        filter.setPageSize(2);
        filter.setPageNumber(0);

        mockMvc.perform(post("/post/find")
                        .content(objectMapper.writeValueAsString(filter))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedPost1.getId()))
                .andExpect(jsonPath("$[0].rentConditionInfoResponse.deposit").value(10.0))
                .andExpect(jsonPath("$[0].rentConditionInfoResponse.price").value(232323.0))
                .andExpect(jsonPath("$[0].rentConditionInfoResponse.currency").value("USD"))

                .andExpect(jsonPath("$[1].id").value(savedPost2.getId()))
                .andExpect(jsonPath("$[1].rentConditionInfoResponse.deposit").value(10.0))
                .andExpect(jsonPath("$[1].rentConditionInfoResponse.price").value(323232.0))
                .andExpect(jsonPath("$[1].rentConditionInfoResponse.currency").value("USD"));
    }

    @Test
    void findPostByIdWhenUserIsAuthenticated() {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordFindPostByIdWhenUserIsAuthenticatedLogin",
                "landlordFindPostByIdWhenUserIsAuthenticatedPassword");
        PostDto savedPost = createPost(landlord, 1000.0);
        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostLoginRenter",
                "findPostPasswordRenter");
        UserParameter defaultCurrencyParam = createUserParameter(
                authenticatedUser.getId(),
                UserParamName.DEFAULT_CURRENCY.getName(),
                "USD");
        createUserParameter(
                authenticatedUser.getId(),
                UserParamName.AUTO_CONVERSION.getName(),
                "true");

        when(currencyService.convertCurrency(
                        savedPost.getRentConditionInfoDto().getCurrency(),
                        defaultCurrencyParam.getParamValue(),
                        savedPost.getRentConditionInfoDto().getPrice()
                )
        ).thenReturn(ApiLayerResponse.builder().result(100.0).build());

        when(currencyService.convertCurrency(
                        savedPost.getRentConditionInfoDto().getCurrency(),
                        defaultCurrencyParam.getParamValue(),
                        savedPost.getRentConditionInfoDto().getDeposit())
        ).thenReturn(ApiLayerResponse.builder().result(10.0).build());

        PostDto actualPost = postService.findPostById(authenticatedUser.getLogin(), savedPost.getId());

        assertEquals(100.0, actualPost.getRentConditionInfoDto().getPrice());
        assertEquals(10.0, actualPost.getRentConditionInfoDto().getDeposit());
    }
}