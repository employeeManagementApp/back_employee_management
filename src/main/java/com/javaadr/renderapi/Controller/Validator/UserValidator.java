package com.javaadr.renderapi.Controller.Validator;

import com.javaadr.renderapi.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

import static com.javaadr.renderapi.Controller.Validator.utils.StringValidator.isNotNullAndNotBlank;

@Component
@AllArgsConstructor
public class UserValidator {
    public  void validate(User user){
        StringBuilder error = new StringBuilder();
        if (user.getBirthdate() == null) {
            error.append("Birthdate is mandatory. ");
        } else if (user.getBirthdate().toInstant().isAfter(Instant.from((LocalDate.now())))) {
            error.append("Birthdate could not be after today. ");
        }
        if (!isNotNullAndNotBlank(user.getFirstname())) {
            error.append("Last firstName is mandatory. ");
        }
        if (!isNotNullAndNotBlank(user.getLastname())) {
            error.append("Last name is mandatory. ");
        }
        if (!isNotNullAndNotBlank(user.getAddress())) {
            error.append("Last address is mandatory. ");
        }
        if (!isNotNullAndNotBlank(user.getEmail())) {
            error.append("Last email is mandatory. ");
        }
        if (!isNotNullAndNotBlank(user.getGender())) {
            error.append("Last gender is mandatory. ");
        }
        if (user.getStart_work_date() == null) {
            error.append("Start work date is mandatory. ");
        } else if (user.getStart_work_date().toInstant().isAfter(Instant.from((LocalDate.now())))) {
            error.append("Start work date could not be after today. ");
        }
        if (user.getLast_work_date() == null) {
            error.append("Last work date is mandatory. ");
        } else if (user.getLast_work_date().toInstant().isAfter(Instant.from((LocalDate.now())))) {
            error.append("last work date could not be after today. ");
        }
    }
}
