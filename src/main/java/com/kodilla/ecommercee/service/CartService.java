package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public Cart findCartById(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
    }

    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(final Long id, final CartDto cartDto) throws CartNotFoundException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);

        cart.setCreatedAt(cartDto.getCreatedAt());
        List<Product> products = productRepository.findAllById(cartDto.getProductIds());
        cart.setProducts(products);

        return cartRepository.save(cart);
    }

    public void deleteCart(final Long id) throws CartNotFoundException {
        if (!cartRepository.existsById(id)) {
            throw new CartNotFoundException();
        }
        cartRepository.deleteById(id);
    }
}
