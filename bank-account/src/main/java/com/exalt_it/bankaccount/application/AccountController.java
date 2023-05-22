package com.exalt_it.bankaccount.application;

import com.exalt_it.bankaccount.domain.model.Account;
import com.exalt_it.bankaccount.domain.model.Operation;
import com.exalt_it.bankaccount.domain.ports.input.*;
import com.exalt_it.bankaccount.application.data.request.AccountCreateRequest;
import com.exalt_it.bankaccount.application.data.request.DepositRequest;
import com.exalt_it.bankaccount.application.data.request.WithDrawalRequest;
import com.exalt_it.bankaccount.application.data.response.DepositResponse;
import com.exalt_it.bankaccount.application.data.response.AccountCreatedResponse;
import com.exalt_it.bankaccount.application.data.response.WithDrawalResponse;
import com.exalt_it.bankaccount.application.mapper.AccountRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/api/accounts")
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final MakeDepositUseCase makeDepositUseCase;
    private final MakeWithDrawalUseCase makeWithDrawalUseCase;
    private final GetStatementUseCase getStatementUseCase;
    private final GetCurrentBalanceUseCase getCurrentBalanceUseCase;
    private final AccountRestMapper accountRestMapper;

    public AccountController(CreateAccountUseCase createAccountUseCase,
                             MakeDepositUseCase makeDepositUseCase,
                             MakeWithDrawalUseCase makeWithDrawalUseCase,
                             GetStatementUseCase getStatementUseCase,
                             GetCurrentBalanceUseCase getCurrentBalanceUseCase,
                             AccountRestMapper accountRestMapper) {
        this.createAccountUseCase = createAccountUseCase;
        this.makeDepositUseCase = makeDepositUseCase;
        this.makeWithDrawalUseCase = makeWithDrawalUseCase;
        this.getStatementUseCase = getStatementUseCase;
        this.getCurrentBalanceUseCase = getCurrentBalanceUseCase;
        this.accountRestMapper = accountRestMapper;
    }

    @PostMapping
    public ResponseEntity<AccountCreatedResponse> createAccount(@RequestBody @Validated AccountCreateRequest request) {
        Account account = accountRestMapper.toAccount(request);
        account = createAccountUseCase.create(account.getEmail());
        return new ResponseEntity<>(accountRestMapper.toAccountCreateResponse(account), CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@RequestBody @Validated DepositRequest request) {
        Operation operation = accountRestMapper.toOperation(request);
        Account account = makeDepositUseCase.makeDeposit(operation);
        return new ResponseEntity<>(accountRestMapper.toAccountDeposit(account), CREATED);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<WithDrawalResponse> withDrawal(@RequestBody @Validated WithDrawalRequest request) {
        Operation operation = accountRestMapper.toOperation(request);
        Account account = makeWithDrawalUseCase.makeWithDrawal(operation);
        return new ResponseEntity<>(accountRestMapper.toAccountWithDrawal(account), CREATED);
    }

    @GetMapping("/{email}/print-statement")
    public ResponseEntity<String> printStatement(@PathVariable String email) {
        return new ResponseEntity<>(getStatementUseCase.printStatement(email), OK);
    }

    @GetMapping("/{email}/current-balance")
    public ResponseEntity<String> getCurrentBalance(@PathVariable String email) {
        return new ResponseEntity<>(getCurrentBalanceUseCase.displayCurrentBalance(email), OK);
    }
}
