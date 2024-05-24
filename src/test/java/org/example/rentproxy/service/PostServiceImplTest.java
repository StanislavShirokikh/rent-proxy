package org.example.rentproxy.service;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;


class PostServiceImplTest extends PostServiceBaseTest {
    @MockBean
    private CurrencyService currencyService;

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

        Assertions.assertEquals(100.0, actualPost.getRentConditionInfoDto().getPrice());
        Assertions.assertEquals(10.0, actualPost.getRentConditionInfoDto().getDeposit());
    }
}