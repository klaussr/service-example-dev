package ru.budgett.data;

import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Payment {
    private Integer user_id;
    private Integer payment_number;
    private LocalDate payment_date;
    private Double payment_quantity;
    private Double debt;
    private Double procents;
    private Double commission;
    private Double rest_of_debt;
}
