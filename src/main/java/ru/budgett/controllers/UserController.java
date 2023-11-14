package ru.budgett.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.budgett.data.Payment;
import ru.budgett.data.User;
import ru.budgett.service.api.PaymentService;
import ru.budgett.service.api.UserService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user/")
@RequiredArgsConstructor
public class UserController {
    private final ModelMap successResponse = new ModelMap();
    private final UserService service;
    private final PaymentService paymentService;

    @PostConstruct
    public void init() {
        successResponse.put("result", "success");
    }

    @PostMapping(value = "save")
    public ModelMap saveUser(@Valid @RequestBody User user) {
        service.saveUser(user);

        return successResponse;
    }

    @DeleteMapping(value = "delete/{userId}")
    public ModelMap deleteUser(@PathVariable Long userId) {
        service.deleteUser(userId);

        return successResponse;
    }

    @GetMapping(value = "get/{userId}")
    public User findUser(@PathVariable Long userId) {
        return service.getUser(userId);
    }

    @GetMapping(value = "all")
    public List<User> findAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(value = "payments/{userId}")
    public List<Payment> findUserPayments(@PathVariable Integer userId) {
        return paymentService.getUserPayments(userId);
    }
}
