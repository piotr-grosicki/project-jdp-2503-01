package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        User user = userRepository.findById(cartDto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        List<Product> products = productRepository.findAllById(cartDto.getProductIds());

        return new Cart(
                cartDto.getCartId(),
                cartDto.getCreatedAt(),
                user,
                products
        );
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getCreatedAt(),
                cart.getUser().getUserId(),
                cart.getProducts().stream()
                        .map(Product::getProductId)
                        .collect(Collectors.toList())
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
