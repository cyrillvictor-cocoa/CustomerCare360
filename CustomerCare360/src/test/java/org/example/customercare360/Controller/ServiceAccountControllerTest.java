package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceAccountRequest;
import org.example.customercare360.DTO.ServiceAccountResponse;
import org.example.customercare360.Services.ServiceAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceAccountControllerTest {

    @Mock
    private ServiceAccountService serviceAccountService;

    @InjectMocks
    private ServiceAccountController serviceAccountController;

    @Test
    void testGetAllServiceAccounts() {

        List<ServiceAccountResponse> accounts =
                new ArrayList<>();

        ServiceAccountResponse response =
                new ServiceAccountResponse();

        response.setAccountId(1);

        accounts.add(response);

        when(serviceAccountService.getAllServiceAccounts())
                .thenReturn(accounts);

        List<ServiceAccountResponse> result =
                serviceAccountController.getAllServiceAccounts();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(serviceAccountService,
                times(1))
                .getAllServiceAccounts();
    }

    @Test
    void testGetServiceAccountById() {

        ServiceAccountResponse response =
                new ServiceAccountResponse();

        response.setAccountId(1);

        when(serviceAccountService.getServiceAccountDetails(1))
                .thenReturn(response);

        ServiceAccountResponse result =
                serviceAccountController.getServiceAccountDetails(1);

        assertNotNull(result);
        assertEquals(1,
                result.getAccountId());

        verify(serviceAccountService,
                times(1))
                .getServiceAccountDetails(1);
    }

    @Test
    void testCreateServiceAccount() {

        ServiceAccountRequest request =
                new ServiceAccountRequest();

        request.setCustomerId(16);

        ServiceAccountResponse response =
                new ServiceAccountResponse();

        response.setAccountId(1);

        when(serviceAccountService.createServiceAccount(
                any(ServiceAccountRequest.class)))
                .thenReturn(response);

        ServiceAccountResponse result =
                serviceAccountController.createServiceAccount(
                        request);

        assertNotNull(result);
        assertEquals(1,
                result.getAccountId());

        verify(serviceAccountService,
                times(1))
                .createServiceAccount(
                        any(ServiceAccountRequest.class));
    }

    @Test
    void testUpdateServiceAccount() {

        ServiceAccountRequest request =
                new ServiceAccountRequest();


        ServiceAccountResponse response =
                new ServiceAccountResponse();

        response.setAccountId(1);

        when(serviceAccountService.updateServiceAccount(
                any(ServiceAccountRequest.class)))
                .thenReturn(response);

        ServiceAccountResponse result =
                serviceAccountController.updateServiceAccount(
                        request);

        assertNotNull(result);
        assertEquals(1,
                result.getAccountId());

        verify(serviceAccountService,
                times(1))
                .updateServiceAccount(
                        any(ServiceAccountRequest.class));
    }

    @Test
    void testDeleteServiceAccount() {

        doNothing().when(serviceAccountService)
                .deleteServiceAccount(1);

        String result =
                serviceAccountController.deleteServiceAccount(1);

        assertEquals(
                "Service Account deleted successfully",
                result);

        verify(serviceAccountService,
                times(1))
                .deleteServiceAccount(1);
    }
}