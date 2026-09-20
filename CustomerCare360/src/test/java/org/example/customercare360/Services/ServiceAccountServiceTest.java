package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceAccountRequest;
import org.example.customercare360.DTO.ServiceAccountResponse;
import org.example.customercare360.Entity.ServiceAccount;
import org.example.customercare360.Repository.ServiceAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceAccountServiceTest {

    @Mock
    private ServiceAccountRepository serviceAccountRepository;

    @InjectMocks
    private ServiceAccountService serviceAccountService;

    @Test
    void testGetAllServiceAccounts() {

        List<ServiceAccount> accounts = new ArrayList<>();

        ServiceAccount account = new ServiceAccount();
        account.setAccountId(1);

        accounts.add(account);

        when(serviceAccountRepository.findAll())
                .thenReturn(accounts);

        List<ServiceAccountResponse> result =
                serviceAccountService.getAllServiceAccounts();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(serviceAccountRepository,
                times(1))
                .findAll();
    }

    @Test
    void testGetServiceAccountDetails() {

        ServiceAccount account =
                new ServiceAccount();

        account.setAccountId(1);

        when(serviceAccountRepository.findById(1))
                .thenReturn(Optional.of(account));

        ServiceAccountResponse result =
                serviceAccountService.getServiceAccountDetails(1);

        assertNotNull(result);
        assertEquals(
                1,
                result.getAccountId());

        verify(serviceAccountRepository,
                times(1))
                .findById(1);
    }

    @Test
    void testCreateServiceAccount() {

        ServiceAccountRequest request =
                new ServiceAccountRequest();

        request.setCustomerId(16);
        request.setPremiseId(11);

        ServiceAccount savedAccount =
                new ServiceAccount();

        savedAccount.setAccountId(1);
        savedAccount.setCustomerId(16);
        savedAccount.setPremiseId(11);

        when(serviceAccountRepository.save(
                any(ServiceAccount.class)))
                .thenReturn(savedAccount);

        ServiceAccountResponse result =
                serviceAccountService.createServiceAccount(
                        request);

        assertNotNull(result);
        assertEquals(
                1,
                result.getAccountId());

        verify(serviceAccountRepository,
                times(1))
                .save(any(ServiceAccount.class));
    }

    @Test
    void testUpdateServiceAccount() {

        ServiceAccount existingAccount =
                new ServiceAccount();

        existingAccount.setAccountId(1);

        ServiceAccountRequest request =
                new ServiceAccountRequest();

        request.setCustomerId(20);
        request.setPremiseId(15);

        ServiceAccount updatedAccount =
                new ServiceAccount();

        updatedAccount.setAccountId(1);
        updatedAccount.setCustomerId(20);
        updatedAccount.setPremiseId(15);

        when(serviceAccountRepository.findById(1))
                .thenReturn(Optional.of(existingAccount));

        when(serviceAccountRepository.save(
                any(ServiceAccount.class)))
                .thenReturn(updatedAccount);

        ServiceAccountResponse result =
                serviceAccountService.updateServiceAccount(
                        1,
                        request);

        assertNotNull(result);
        assertEquals(
                1,
                result.getAccountId());

        verify(serviceAccountRepository,
                times(1))
                .findById(1);

        verify(serviceAccountRepository,
                times(1))
                .save(any(ServiceAccount.class));
    }

    @Test
    void testDeleteServiceAccount() {

        ServiceAccount account =
                new ServiceAccount();

        account.setAccountId(1);

        when(serviceAccountRepository.findById(1))
                .thenReturn(Optional.of(account));

        serviceAccountService.deleteServiceAccount(1);

        verify(serviceAccountRepository,
                times(1))
                .findById(1);

        verify(serviceAccountRepository,
                times(1))
                .delete(account);
    }
}