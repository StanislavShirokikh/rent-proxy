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

    @WithMockUser(username = "findPostWithAutoConversionLogin", password = "findPosWithAutoConversionPassword", authorities = "USER")
    @Test
    void getCorrectCurrencyAndValueInGetPostResponseWithAutoConversion() throws Exception {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordGetCorrectCurrencyAndValueInGetPostResponseWithAutoConversionLogin",
                "landlordGetCorrectCurrencyAndValueInGetPostResponseWithAutoConversionPassword");
        PostDto savedPost = createPost(landlord, 1000.0);
        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostWithAutoConversionLogin",
                "findPosWithAutoConversionPassword");
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

    @WithMockUser(
            username = "findPostWithoutAutoConversionLogin",
            password = "findPostWithoutAutoConversionPassword",
            authorities = "USER"
    )
    @Test
    void getCorrectCurrencyAndValueInGetPostResponseWithoutAutoConversion() throws Exception {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordGetCorrectCurrencyAndValueInGetPostResponseWithoutAutoConversionLogin",
                "landlordGetCorrectCurrencyAndValueInGetPostResponseWithoutAutoConversionPassword");
        PostDto savedPost = createPost(landlord, 1000.0);
        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostWithoutAutoConversionLogin",
                "findPostWithoutAutoConversionPassword");
        createUserParameter(
                authenticatedUser.getId(),
                UserParamName.DEFAULT_CURRENCY.getName(),
                "USD");
        createUserParameter(
                authenticatedUser.getId(),
                UserParamName.AUTO_CONVERSION.getName(),
                "false");


        WithIdRequest withIdRequest = new WithIdRequest();
        withIdRequest.setId(savedPost.getId());

        MvcResult result = mockMvc.perform(post("/post/get")
                        .content(objectMapper.writeValueAsString(withIdRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedPost.getId()))
                .andExpect(
                        jsonPath("$.rentConditionInfoResponse.deposit")
                                .value(savedPost.getRentConditionInfoDto().getDeposit())
                )
                .andExpect(
                        jsonPath("$.rentConditionInfoResponse.price")
                                .value(savedPost.getRentConditionInfoDto().getPrice()))
                .andExpect(
                        jsonPath("$.rentConditionInfoResponse.currency")
                                .value(savedPost.getRentConditionInfoDto().getCurrency()))
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

    @WithMockUser(
            username = "findPostByFilterWithAutoConversionLogin",
            password = "findPostByFilterWithAutoConversionPassword",
            authorities = "USER"
    )
    @Test
    void getCorrectCurrencyAndValueInGetPostsByFilterResponseWithAutoConversion() throws Exception {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordGetCorrectCurrencyAndValueInGetPostsByFilterResponseWithAutoConversionLogin",
                "landlordGetCorrectCurrencyAndValueInGetPostsByFilterResponseWithAutoConversionPassword");
        PostDto savedPost1 = createPost(landlord, 0.5);
        PostDto savedPost2 = createPost(landlord, 0.7);

        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostByFilterWithAutoConversionLogin",
                "findPostByFilterWithAutoConversionPassword");

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

    @WithMockUser(
            username = "findPostByFilterWithoutAutoConversionLogin",
            password = "findPostByFilterWithoutAutoConversionPassword",
            authorities = "USER"
    )
    @Test
    void getCorrectCurrencyAndValueInGetPostsByFilterResponseWithoutAutoConversion() throws Exception {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordGetCorrectCurrencyAndValueInGetPostsByFilterResponseWithoutAutoConversionLogin",
                "landlordGetCorrectCurrencyAndValueInGetPostsByFilterResponseWithoutAutoConversionPassword");
        PostDto savedPost1 = createPost(landlord, 0.01);
        PostDto savedPost2 = createPost(landlord, 0.02);

        UserDto authenticatedUser = createUser(
                "findPostFirstName",
                "findPostSecondName",
                "findPostLastName",
                "findPostByFilterWithoutAutoConversionLogin",
                "findPostByFilterWithoutAutoConversionPassword");

        UserParameter defaultCurrencyParam = createUserParameter(
                authenticatedUser.getId(),
                UserParamName.DEFAULT_CURRENCY.getName(),
                "USD");
        createUserParameter(
                authenticatedUser.getId(),
                UserParamName.AUTO_CONVERSION.getName(),
                "false");

        Filter filter = new Filter();
        filter.setPostOrder(PostOrder.ASCENDING_PRICE);
        filter.setMinPrice(0.01);
        filter.setMaxPrice(0.02);
        filter.setPageSize(2);
        filter.setPageNumber(0);

        mockMvc.perform(post("/post/find")
                        .content(objectMapper.writeValueAsString(filter))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedPost1.getId()))
                .andExpect(
                        jsonPath("$[0].rentConditionInfoResponse.deposit")
                                .value(savedPost1.getRentConditionInfoDto().getDeposit())
                )
                .andExpect(
                        jsonPath("$[0].rentConditionInfoResponse.price")
                                .value(savedPost1.getRentConditionInfoDto().getPrice())
                )
                .andExpect(
                        jsonPath("$[0].rentConditionInfoResponse.currency")
                                .value(savedPost1.getRentConditionInfoDto().getCurrency())
                )

                .andExpect(jsonPath("$[1].id").value(savedPost2.getId()))
                .andExpect(
                        jsonPath("$[1].rentConditionInfoResponse.deposit")
                                .value(savedPost2.getRentConditionInfoDto().getDeposit())
                )
                .andExpect(
                        jsonPath("$[1].rentConditionInfoResponse.price")
                                .value(savedPost2.getRentConditionInfoDto().getPrice()))
                .andExpect(
                        jsonPath("$[1].rentConditionInfoResponse.currency")
                                .value(savedPost2.getRentConditionInfoDto().getCurrency()));
    }

    @Test
    void getCorrectCurrencyAndValueInGetPostResponseWithConversionFromSession() throws Exception {
        UserDto landlord = createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordGetCorrectCurrencyAndValueInGetPostResponseWithConversionFromSessionLogin",
                "landlordGetCorrectCurrencyAndValueInGetPostResponseWithConversionFromSessionPassword");
        PostDto savedPost = createPost(landlord, 1000.0);
        String currencyValue = "USD";

        when(currencyService.convertCurrency(
                        savedPost.getRentConditionInfoDto().getCurrency(),
                        currencyValue,
                        savedPost.getRentConditionInfoDto().getPrice()
                )
        ).thenReturn(ApiLayerResponse.builder().result(90.0).build());

        when(currencyService.convertCurrency(
                savedPost.getRentConditionInfoDto().getCurrency(),
                currencyValue,
                savedPost.getRentConditionInfoDto().getDeposit())
        ).thenReturn(ApiLayerResponse.builder().result(9.0).build());

        WithIdRequest withIdRequest = new WithIdRequest();
        withIdRequest.setId(savedPost.getId());

        mockMvc.perform(post("/post/get")
                        .param("currency", "USD")
                        .content(objectMapper.writeValueAsString(withIdRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedPost.getId()))
                .andExpect(jsonPath("$.rentConditionInfoResponse.deposit").value(9.0))
                .andExpect(jsonPath("$.rentConditionInfoResponse.price").value(90.0))
                .andExpect(jsonPath("$.rentConditionInfoResponse.currency").value("USD"));

        mockMvc.perform(post("/post/get")
                        .content(objectMapper.writeValueAsString(withIdRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedPost.getId()))
                .andExpect(jsonPath("$.rentConditionInfoResponse.deposit").value(9.0))
                .andExpect(jsonPath("$.rentConditionInfoResponse.price").value(90.0))
                .andExpect(jsonPath("$.rentConditionInfoResponse.currency").value("USD"));

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

        PostDto actualPost = postService.findPostById(authenticatedUser.getLogin(), savedPost.getId(), null );

        assertEquals(100.0, actualPost.getRentConditionInfoDto().getPrice());
        assertEquals(10.0, actualPost.getRentConditionInfoDto().getDeposit());
    }
}