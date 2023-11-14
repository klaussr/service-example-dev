package ru.budgett.repository.api;

import ru.budgett.data.Payment;
import java.util.List;

public interface PaymentRepository {
    List<Payment> findUserPayments(Integer user_id);
}
