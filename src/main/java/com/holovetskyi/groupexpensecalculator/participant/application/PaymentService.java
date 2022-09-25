package com.holovetskyi.groupexpensecalculator.participant.application;

import com.holovetskyi.groupexpensecalculator.participant.domain.repo.PaymentRepository;
import com.holovetskyi.groupexpensecalculator.participant.web.dto.UpdatePaymentDTO;
import com.holovetskyi.groupexpensecalculator.participant.web.response.UpdatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public UpdatePaymentResponse updatePayment(Long id, UpdatePaymentDTO paymentDTO) {
        return paymentRepository
                .findByIdPaymentEntity(id)
                .map(payment -> {
                    payment.updateFields(paymentDTO);
                    return UpdatePaymentResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdatePaymentResponse(false,
                        Collections.singletonList("Product not found with id: " + id)));
    }

    public void deleteById(Long id){
        paymentRepository.delete(id);
    }

}
