package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.response.AccountResponse;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @GetMapping({"/account", "/workintech/accounts"})
    public List<AccountResponse> findAll() {
        return accountService.findAll().stream()
                .map(a -> new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount()))
                .toList();
    }
    @GetMapping({"/account/{id}", "/workintech/accounts/{id}"})
    public AccountResponse find(@PathVariable Long id){
        Account account = accountService.find(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount());
    }
    @PostMapping({"/account/{customerId}", "/workintech/accounts/{customerId}"})
    public AccountResponse save(@PathVariable Long customerId, @RequestBody Account account){
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        Account saved = accountService.save(account);
        return  new AccountResponse(saved.getId(), saved.getAccountName(),saved.getMoneyAmount());
    }
    @PutMapping({"/account/{customerId}", "/workintech/accounts/{customerId}"})
    public AccountResponse update(@PathVariable Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        Account updated = accountService.save(account);
        return new AccountResponse(updated.getId(), updated.getAccountName(), updated.getMoneyAmount());
    }

    @DeleteMapping({"/account/{id}", "/workintech/accounts/{id}"})
    public AccountResponse remove(@PathVariable Long id) {
        // First ensure the account exists
        accountService.find(id);
        Account deleted = accountService.delete(id);
        if (deleted == null) {
            return null;
        }
        return new AccountResponse(deleted.getId(), deleted.getAccountName(), deleted.getMoneyAmount());
    }


}
