package com.exalt_it.bankaccount.infrastructure.config;

import com.exalt_it.bankaccount.domain.ports.output.AccountEventPublisher;
import com.exalt_it.bankaccount.domain.service.AccountService;
import com.exalt_it.bankaccount.infrastructure.output.eventpublisher.AccountEventPublisherAdapter;
import com.exalt_it.bankaccount.infrastructure.output.persistence.AccountPersistenceAdapter;
import com.exalt_it.bankaccount.infrastructure.output.persistence.mapper.AccountPersistenceMapper;
import com.exalt_it.bankaccount.infrastructure.output.persistence.repository.AccountRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    AccountEventPublisherAdapter accountEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        return new AccountEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    AccountPersistenceAdapter accountPersistenceAdapter(AccountRepository accountRepository,
                                                        AccountPersistenceMapper accountPersistenceMapper) {
        return new AccountPersistenceAdapter(accountRepository, accountPersistenceMapper);
    }

    @Bean
    AccountService accountService(AccountPersistenceAdapter accountPersistenceAdapter, AccountEventPublisher accountEventPublisher) {
        return new AccountService(accountPersistenceAdapter, accountEventPublisher);
    }
}
