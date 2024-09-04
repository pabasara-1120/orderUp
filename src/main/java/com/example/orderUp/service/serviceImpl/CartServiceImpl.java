package com.example.orderUp.service.serviceImpl;

import com.example.orderUp.dto.CartDTO;
import com.example.orderUp.dto.OrderDetailsDTO;
import com.example.orderUp.entity.Cart;
import com.example.orderUp.entity.Customer;
import com.example.orderUp.entity.OrderDetails;
import com.example.orderUp.repository.CartRepository;
import com.example.orderUp.repository.CustomerRepository;
import com.example.orderUp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CartDTO addItemToCart(Long customerId, OrderDetailsDTO orderDetailsDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){

            Customer customer = optionalCustomer.get();
            Cart cart = customer.getCart();

            if(cart==null){
                cart = new Cart();
                cart.setCustomer(customer);
                cart.setOrderDetails(new ArrayList<>());
                cart.setQuantity(0);
                cart.setTime(LocalDateTime.now());
                customer.setCart(cart);
            }

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setQuantity(orderDetailsDTO.getQuantity());
            orderDetails.setCart(cart);

            cart.getOrderDetails().add(orderDetails);
            cart.setQuantity(cart.getQuantity()+orderDetailsDTO.getQuantity());
            cart.setTime(LocalDateTime.now());

            Cart savedCart = cartRepository.save(cart);
            return convertToDTO(savedCart);

        }
        return null;

    }

    @Override
    public CartDTO getCartByCustomerId(Long customerId) {
        Cart cart = cartRepository.findByCustomer_CustomerId(customerId);
        if(cart!=null){
            return convertToDTO(cart);
        }
        return null;
    }

    @Override
    public CartDTO clearCart(Long customerId) {
        Cart cart = cartRepository.findByCustomer_CustomerId(customerId);
        if(cart!=null){
            cart.getOrderDetails().clear();
            cart.setQuantity(0);
            cart.setTime(LocalDateTime.now());

            Cart savedCart = cartRepository.save(cart);
            return convertToDTO(savedCart);
        }
        return null;

    }

    private CartDTO convertToDTO(Cart cart) {
        return CartDTO.builder()
                .cartId(cart.getCart_id())
                .quantity(cart.getQuantity())
                .time(cart.getTime())
                .orderDetails(cart.getOrderDetails().stream()
                        .map(orderDetails -> OrderDetailsDTO.builder()
                                .orderDetailsId(orderDetails.getOrderDetail_id())
                                .quantity(orderDetails.getQuantity())
                                // Map other fields from OrderDetails to OrderDetailsDTO
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
