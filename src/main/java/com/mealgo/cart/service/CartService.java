package com.mealgo.cart.service;

import com.mealgo.cart.domain.dto.RequestCart;
import com.mealgo.cart.domain.dto.ResponseCart;
import com.mealgo.cart.domain.entity.Cart;
import com.mealgo.cart.repository.CartRepository;
import com.mealgo.common.code.CartEnum;
import com.mealgo.meal.domain.entity.Meal;
import com.mealgo.meal.repository.MealRepository;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final MealRepository mealRepository;

    @Transactional
    public void addCart(RequestCart requestCart) {
        Meal meal = mealRepository.findById(requestCart.getMealNo())
                .orElseThrow(() -> new RuntimeException("Meal 없음"));

        Member member = memberRepository.findById(requestCart.getMemNo())
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        // 1. 이미 존재하는 장바구니인지 확인
        Optional<Cart> existingCartOpt = cartRepository.findByMemNoAndMeal(meal.getMealNo(), requestCart.getMemNo());

        if (existingCartOpt.isPresent()) {
            // 2. 이미 담겨 있으면 수량 추가
            Cart existingCart = existingCartOpt.get();
            existingCart.setCartCnt(existingCart.getCartCnt() + requestCart.getCartCnt());
            existingCart.setCartUpDt(LocalDateTime.now());
        } else {
            // 3. 없으면 새로 추가
            Cart cart = Cart.builder()
                    .member(member)
                    .meal(meal)
                    .cartCnt(requestCart.getCartCnt())
                    .cartStat(CartEnum.ADD)  // 담기 상태
                    .cartRegDt(LocalDateTime.now())
                    .build();
            cartRepository.save(cart);
        }
    }

    @Transactional(readOnly = true)
    public List<ResponseCart> getCartListByMemNo(Integer memNo) {
        return cartRepository.findByMemNo(memNo).stream()
                .map(ResponseCart::from)
                .toList();
    }

    @Transactional
    public void updateCartQuantity(Integer cartNo, int delta) {
        Cart cart = cartRepository.findById(cartNo).orElseThrow();
        int newCount = cart.getCartCnt() + delta;
        if (newCount < 1) {
            newCount = 1;
        }
        cart.setCartCnt(newCount);
        cart.setCartUpDt(LocalDateTime.now());
    }

    public void deleteCartItems(List<Integer> cartIds) {
        cartRepository.deleteAllById(cartIds);
    }

    @Transactional
    public void updateCartState(Integer cartNo, CartEnum status) {
        Cart cart = cartRepository.findById(cartNo).orElseThrow();
        cart.setCartStat(status);
        cart.setCartUpDt(LocalDateTime.now());
    }

    public List<ResponseCart> findByCartNoIn(List<Integer> cartNos) {
        return cartRepository.findByCartNoIn(cartNos).stream()
                .map(ResponseCart::from)
                .toList();
    }

}
