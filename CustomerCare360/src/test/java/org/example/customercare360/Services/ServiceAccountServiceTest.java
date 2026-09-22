package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceAccountRequest;
import org.example.customercare360.DTO.ServiceAccountResponse;
import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Entity.ServiceAccount;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Repository.CustomerRepository;
import org.example.customercare360.Repository.PremiseRepository;
import org.example.customercare360.Repository.ServiceAccountRepository;
import org.example.customercare360.Repository.UserRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceAccountServiceTest {

    @Mock
    private ServiceAccountRepository serviceAccountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PremiseRepository premiseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ServiceAccountService serviceAccountService;

    @Test
    void testGetAllServiceAccounts() {

        Customer customer = new Customer();
        customer.setUserId(16);

        Premise premise = new Premise();
        premise.setPremiseId(11);

        User user = new User();
        user.setUserId(1);

        ServiceAccount account = new ServiceAccount();
        account.setAccountId(1);
        account.setCustomer(customer);
        account.setPremise(premise);
        account.setCreatedBy(user);
        account.setModifiedBy(user);

        List<ServiceAccount> accounts = new ArrayList<>();
        accounts.add(account);

        when(serviceAccountRepository.findAll())
                .thenReturn(accounts);

        List<ServiceAccountResponse> result =
                serviceAccountService.getAllServiceAccounts();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(serviceAccountRepository, times(1))
                .findAll();
    }

    @Test
    void testGetServiceAccountDetails() {

        Customer customer = new Customer();
        customer.setUserId(16);

        Premise premise = new Premise();
        premise.setPremiseId(11);

        User user = new User();
        user.setUserId(1);

        ServiceAccount account = new ServiceAccount();
        account.setAccountId(1);
        account.setCustomer(customer);
        account.setPremise(premise);
        account.setCreatedBy(user);
        account.setModifiedBy(user);

        when(serviceAccountRepository.findById(1))
                .thenReturn(Optional.of(account));

        ServiceAccountResponse result =
                serviceAccountService.getServiceAccountDetails(1);

        assertNotNull(result);
        assertEquals(1, result.getAccountId());

        verify(serviceAccountRepository, times(1))
                .findById(1);
    }

    @Test
    void testCreateServiceAccount() {

        ServiceAccountRequest request = new ServiceAccountRequest();
        request.setCustomerId(16);
        request.setPremiseId(11);
        request.setCreatedBy(1);
        request.setModifiedBy(1);

        Customer customer = new Customer();
        customer.setUserId(16);

        Premise premise = new Premise();
        premise.setPremiseId(11);

        User user = new User();
        user.setUserId(1);

        ServiceAccount savedAccount = new ServiceAccount();
        savedAccount.setAccountId(1);
        savedAccount.setCustomer(customer);
        savedAccount.setPremise(premise);
        savedAccount.setCreatedBy(user);
        savedAccount.setModifiedBy(user);

        when(customerRepository.findById(16))
                .thenReturn(Optional.of(customer));

        when(premiseRepository.findById(11))
                .thenReturn(Optional.of(premise));

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        when(serviceAccountRepository.save(any(ServiceAccount.class)))
                .thenReturn(savedAccount);

        ServiceAccountResponse result =
                serviceAccountService.createServiceAccount(request);

        assertNotNull(result);
        assertEquals(1, result.getAccountId());

        verify(serviceAccountRepository, times(1))
                .save(any(ServiceAccount.class));
    }

    @Test
    void testUpdateServiceAccount() {

        Customer customer = new Customer();
        customer.setUserId(20);

        Premise premise = new Premise();
        premise.setPremiseId(15);

        User user = new User();
        user.setUserId(1);

        ServiceAccount existingAccount = new ServiceAccount();
        existingAccount.setAccountId(1);
        existingAccount.setCustomer(customer);
        existingAccount.setPremise(premise);
        existingAccount.setCreatedBy(user);
        existingAccount.setModifiedBy(user);

        ServiceAccountRequest request = new ServiceAccountRequest();
        request.setAccountId(1);
        request.setCustomerId(20);
        request.setPremiseId(15);
        request.setCreatedBy(1);
        request.setModifiedBy(1);

        ServiceAccount updatedAccount = new ServiceAccount();
        updatedAccount.setAccountId(1);
        updatedAccount.setCustomer(customer);
        updatedAccount.setPremise(premise);
        updatedAccount.setCreatedBy(user);
        updatedAccount.setModifiedBy(user);

        when(serviceAccountRepository.findById(1))
                .thenReturn(Optional.of(existingAccount));

        when(customerRepository.findById(20))
                .thenReturn(Optional.of(customer));

        when(premiseRepository.findById(15))
                .thenReturn(Optional.of(premise));

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        when(serviceAccountRepository.save(any(ServiceAccount.class)))
                .thenReturn(updatedAccount);

        ServiceAccountResponse result =
                serviceAccountService.updateServiceAccount(request);

        assertNotNull(result);
        assertEquals(1, result.getAccountId());

        verify(serviceAccountRepository, times(1))
                .findById(1);

        verify(serviceAccountRepository, times(1))
                .save(any(ServiceAccount.class));
    }

    @Test
    void testDeleteServiceAccount() {

        ServiceAccount account = new ServiceAccount();
        account.setAccountId(1);

        when(serviceAccountRepository.findById(1))
                .thenReturn(Optional.of(account));

        serviceAccountService.deleteServiceAccount(1);

        verify(serviceAccountRepository, times(1))
                .findById(1);

        verify(serviceAccountRepository, times(1))
                .delete(account);
    }
}