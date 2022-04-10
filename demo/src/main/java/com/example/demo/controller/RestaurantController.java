package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.MenuItemService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/restaurantActions")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OrdersService ordersService;


    @PostMapping("/addRestaurant")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PostMapping("/addFoods")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDTO addFood(@Valid @RequestBody MenuItemDTO menuItemDTO) {

        return menuItemService.addMenuItem(menuItemDTO);
    }

    @GetMapping("/viewMenuItems/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getMenuItemsByCategory(@PathVariable String category){

        return menuItemService.findMenuItemsByCategory(category);

    }

    @GetMapping("/viewAllMenuItems")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getAllMenuItems(@Param("username") String username){

        return menuItemService.getAllMenuItemsFromARestaurant(username);
    }

    @GetMapping("/viewRestaurantMenu")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getRestaurantMenu(@Param("name") String name){

        return menuItemService.getMenu(name);
    }

    @GetMapping("/getAllRestaurants")
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantDTO> getAllRestaurants(){

        return restaurantService.getAllRestaurants();

    }

    @GetMapping("/viewRestaurantOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewOrderDTO> getRestaurantOrders(@Param("name") String name){

        return ordersService.getRestaurantOrders(name);
    }

    @GetMapping("/viewRestaurantPendingOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewOrderDTO> getRestaurantPendingOrders(@Param("name") String name){

        return ordersService.getRestaurantPendingOrders(name);
    }

    @PutMapping("/acceptOrders")
    @ResponseStatus(HttpStatus.OK)
    public boolean acceptOrders(@RequestBody AcceptedOrdersDTO acceptedOrdersDTO){

        return ordersService.acceptOrders(acceptedOrdersDTO);
    }

    @GetMapping("/filterOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewOrderDTO> filterOrdersByStatus(@Param("orderStatus") String orderStatus, @Param("administrator") String administrator){

        System.out.println("Filteeeeeeeeeeeeeee");
        return ordersService.filterOrdersByStatus(orderStatus, administrator);
    }

    @GetMapping("/viewCustomerOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewOrderDTO> getCustomerOrders(@Param("email") String email){

        return ordersService.getCustomerOrders(email);
    }
}
