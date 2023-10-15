package com.lokytech.questionservice.validator;

import com.lokytech.questionservice.client.UsersClient;
import com.lokytech.questionservice.dto.UsersDTO;
import com.lokytech.questionservice.exception.ExternalServiceException;
import com.lokytech.questionservice.exception.UserNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExistenceValidator {

    @Autowired
    UsersClient usersClient;

    public UsersDTO validateUserExists(Long userId){
        try {
            UsersDTO user = usersClient.getUserById(userId);
            if (user == null || user.getId() == null) {
                throw new UserNotFoundException("User with ID " + userId + " not found in user-service.");
            }
            return user;
        } catch (FeignException e) {
            if (e.status() == 404) {
                throw new UserNotFoundException("User with ID " + userId + " not found in user-service.");
            } else {
                throw new ExternalServiceException("Error occurred when calling user-service: " + e.contentUTF8(), e);
            }
        }
    }
}
