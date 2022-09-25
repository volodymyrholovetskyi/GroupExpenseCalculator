package com.holovetskyi.groupexpensecalculator.participant.web;

import com.holovetskyi.groupexpensecalculator.participant.application.PaymentService;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.UpdatePaymentDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.response.UpdatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PatchMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    void update(@PathVariable Long id, @RequestBody UpdatePaymentDTO paymentDTO) {
        UpdatePaymentResponse response = service.updatePayment(id, paymentDTO);

        if (!response.success()) {
            String message = String.join(", ", response.errors());
            throw new ResponseStatusException(BAD_REQUEST, message);
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}

