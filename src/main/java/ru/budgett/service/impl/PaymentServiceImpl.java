package ru.budgett.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.budgett.data.Payment;
import ru.budgett.repository.api.PaymentRepository;
import ru.budgett.service.api.PaymentService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    @Override
    public List<Payment> getUserPayments(Integer userId) {
        return repository.findUserPayments(userId);
    }
}
