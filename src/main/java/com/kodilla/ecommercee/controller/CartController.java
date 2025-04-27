package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {

        List<Cart> carts = cartService.findAllCarts();
        return ResponseEntity.ok(cartMapper.mapToCartDtoList(carts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long id) throws CartNotFoundException {

        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.findCartById(id)));
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {

        Cart cart = cartMapper.mapToCart(cartDto);
        Cart savedCart = cartService.saveCart(cart);
        return ResponseEntity.ok(cartMapper.mapToCartDto(savedCart));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable Long id, @RequestBody CartDto cartDto) throws UserNotFoundException, CartNotFoundException {
        Cart updatedCart = cartService.updateCart(id, cartDto);
        return ResponseEntity.ok(cartMapper.mapToCartDto(updatedCart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) throws CartNotFoundException {

         cartService.deleteCart(id);
         return ResponseEntity.noContent().build();
    }
}
