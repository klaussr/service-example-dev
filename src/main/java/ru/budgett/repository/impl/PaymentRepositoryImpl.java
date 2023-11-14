package ru.budgett.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.budgett.data.Payment;
import ru.budgett.repository.api.PaymentRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final NamedParameterJdbcTemplate template;

    private static final String SQL_QUERY =
            "        SELECT user_id, payment_number, payment_date, payment_quantity, debt, procents, commission, rest_of_debt " +
                    "FROM payments_schedule " +
                    "WHERE user_id = :ID"
            ;

    @Override
    public List<Payment> findUserPayments(Integer user_id) {
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource().addValue("ID", user_id);

        return template.query(SQL_QUERY, sqlParameters, getResultDtoRowMapper());
    }

    private RowMapper<Payment> getResultDtoRowMapper() {
        return (resultSet, i) -> toDto(resultSet);
    }

    private Payment toDto(ResultSet resultSet) throws SQLException {
        return new Payment()
                .setUser_id(resultSet.getInt("user_id"))
                .setPayment_number(resultSet.getInt("payment_number"))
                .setPayment_date(resultSet.getTimestamp("payment_date").toLocalDateTime().toLocalDate())
                .setPayment_quantity(resultSet.getDouble("payment_quantity"))
                .setDebt(resultSet.getDouble("debt"))
                .setProcents(resultSet.getDouble("procents"))
                .setCommission(resultSet.getDouble("commission"))
                .setRest_of_debt(resultSet.getDouble("rest_of_debt"))
                ;
    }
}
