package com.leska.springapp.controller;

import com.leska.springapp.dto.VacationDTO;
import com.leska.springapp.dto.VacationPayDTO;
import com.leska.springapp.model.Vacation;
import com.leska.springapp.services.VacationService;
import com.leska.springapp.util.VacationErrorResponse;
import com.leska.springapp.util.VacationNotResponseException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculate")
public class VacationController {

    private final VacationService vacationService;
    private final ModelMapper modelMapper;

    @Autowired
    public VacationController(VacationService vacationService, ModelMapper modelMapper) {
        this.vacationService = vacationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<VacationPayDTO> calculateVacationPay(@RequestBody @Valid VacationDTO vacationDTO,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errors.append(fieldError.getField())
                        .append(" : ").append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            throw new VacationNotResponseException(errors.toString());
        }

        Vacation vacation = convertToVacation(vacationDTO);
        String vacationPay = vacationService.getVacationPayOfPeriodDay(vacation);
        VacationPayDTO response = new VacationPayDTO(vacationPay);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ExceptionHandler(VacationNotResponseException.class)
    private ResponseEntity<VacationErrorResponse> handleVacationNotResponseException(VacationNotResponseException ex) {
        VacationErrorResponse response = new VacationErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Vacation convertToVacation(VacationDTO vacationDTO) {
        return modelMapper.map(vacationDTO, Vacation.class);
    }
}
