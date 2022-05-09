package com.example.demo;

import com.example.demo.dto.AcceptedOrdersDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ViewOrderDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.repository.*;
import com.example.demo.service.OrdersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTest {
    @InjectMocks
    private OrdersService ordersService;

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private AdministratorRepository administratorRepository;

    @Mock
    private OrderStatusRepository orderStatusRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRestaurantOrdersNonExistingAdministratorShouldReturnNull() {

        Mockito.when(administratorRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());

        List<ViewOrderDTO> orders = ordersService.getRestaurantOrders("non_existing_admin");

        Assertions.assertNull(orders);
    }

    @Test
    public void acceptOrdersExistingIDShouldReturnTrue() {

        Mockito.when(ordersRepository.getById(Mockito.anyInt())).thenReturn(new Order());
        Mockito.when(orderStatusRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(new OrderStatus("ACCEPTED")));

        AcceptedOrdersDTO acceptedOrdersDTO = new AcceptedOrdersDTO();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        acceptedOrdersDTO.setOrdersIds(ids);
        boolean transaction_result = ordersService.acceptOrders(acceptedOrdersDTO);

        Assertions.assertTrue(transaction_result);
    }

    @Test
    public void getCustomerOrdersNonExistingCustomerShouldReturnNull() {

        Mockito.when(customerRepository.findFirstByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        List<ViewOrderDTO> orders = ordersService.getCustomerOrders("invalid_email");

        Assertions.assertNull(orders);

    }

    @Test
    public void getCustomerOrdersExistingCustomerShouldReturnList() {

        Customer customer = new Customer();
        customer.setIdCustomer(1);
        Mockito.when(customerRepository.findFirstByEmail(Mockito.anyString())).thenReturn(Optional.of(customer));

        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderStatus(new OrderStatus("STATUS"));
        order.setIdMenuItem(1);
        orders.add(order);

        Mockito.when(ordersRepository.findOrderByCustomer_IdCustomer(Mockito.anyInt())).thenReturn(orders);

        List<ViewOrderDTO> customerOrders = ordersService.getCustomerOrders("email");

        Assertions.assertEquals(orders.size(),customerOrders.size());

    }


}
