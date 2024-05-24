package org.example.rentproxy.service;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;


class PostServiceImplTest extends PostServiceBaseTest {


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
                String.valueOf(savedPost.getRentConditionInfoDto().getPrice())
                )
        ).thenReturn(ApiLayerResponse.builder().result(10000.0).build());

        double actualPrice = postService.findPostById(authenticatedUser.getLogin(), savedPost.getId()).getRentConditionInfoDto().getPrice();
        Assertions.assertEquals(10000, actualPrice);
//        when(currencyService.convertCurrency(
//                        savedPost.getRentConditionInfoDto().getCurrency(),
//                        userParameter.getParamValue(),
//                        String.valueOf(savedPost.getRentConditionInfoDto().getDeposit())
//                )
//        ).thenReturn(ApiLayerResponse.builder().result(1000.0).build());
//
//        PostDto actualPost = postService.findPostById(authenticatedUser.getLogin(), savedPost.getId());
//
//        Assertions.assertEquals(10000.0, actualPost.getRentConditionInfoDto().getPrice());
//        Assertions.assertEquals(1000.0, actualPost.getRentConditionInfoDto().getDeposit());
    }
}