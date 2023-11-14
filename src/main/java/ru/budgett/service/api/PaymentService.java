package ru.budgett.service.api;

import ru.budgett.data.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> getUserPayments(Integer userId);
}
