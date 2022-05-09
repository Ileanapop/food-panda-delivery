package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.MenuItemService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.RestaurantService;
import com.example.demo.utils.MenuPDFExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/restaurantActions")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OrdersService ordersService;

    private final static Logger LOGGER = Logger.getLogger(RestaurantController.class.getName());


    @PostMapping("/addRestaurant")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        LOGGER.info("Start request to add restaurant");
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PostMapping("/addFoods")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDTO addFood(@Valid @RequestBody MenuItemDTO menuItemDTO) {
        LOGGER.info("Start request to add food");
        return menuItemService.addMenuItem(menuItemDTO);
    }

    @GetMapping("/viewMenuItems/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getMenuItemsByCategory(@PathVariable String category){
        LOGGER.info("Start request to get menu items");
        return menuItemService.findMenuItemsByCategory(category);

    }

    @GetMapping("/viewAllMenuItems")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getAllMenuItems(@Param("username") String username){
        LOGGER.info("Start request to get all menu items");
        return menuItemService.getAllMenuItemsFromARestaurant(username);
    }

    @GetMapping("/viewRestaurantMenu")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getRestaurantMenu(@Param("name") String name){
        LOGGER.info("Start request to view Restaurant Menu");
        return menuItemService.getMenu(name);
    }

    @GetMapping("/getAllRestaurants")
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantDTO> getAllRestaurants(){
        LOGGER.info("Start request to get list of restaurants");
        return restaurantService.getAllRestaurants();

    }

    @GetMapping("/viewRestaurantOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewOrderDTO> getRestaurantOrders(@Param("name") String name){
        LOGGER.info("Start request to view all orders for restaurant");
        return ordersService.getRestaurantOrders(name);
    }

    @GetMapping("/viewRestaurantPendingOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewOrderDTO> getRestaurantPendingOrders(@Param("name") String name){
        LOGGER.info("Start request to view pending orders");
        return ordersService.getRestaurantPendingOrders(name);
    }

    @PutMapping("/acceptOrders")
    @ResponseStatus(HttpStatus.OK)
    public boolean acceptOrders(@RequestBody AcceptedOrdersDTO acceptedOrdersDTO){
        LOGGER.info("Start request to accept order");
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
        LOGGER.info("Start request to get customer orders");
        return ordersService.getCustomerOrders(email);
    }

    @GetMapping("/exportMenuToPdf")
    public void exportMenuToPDF(@Param("username") String username, HttpServletResponse response) throws IOException {
        LOGGER.info("Start request to export menu to pdf");
        response.setContentType("application/pdf");

        MenuPDFExporter menuPDFExporter = new MenuPDFExporter(restaurantService.getListOfMenuItems(username));
        LOGGER.info("List of items returned, exporting menu...");
        menuPDFExporter.export(response);
        LOGGER.info("Pdf created successfully");
    }
}
