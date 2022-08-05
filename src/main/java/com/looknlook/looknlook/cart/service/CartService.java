package com.looknlook.looknlook.cart.service;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.Item.repository.StockRepository;
import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.looknlook.looknlook.cart.domain.request.ReqCart;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.cart.repository.CartRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final StockRepository stockRepository;

    public List<ResCart> readCartList(Long memNo) {
        List<ResCart> cartList = cartRepository.findAllByMemNo(memNo);
        return cartList;
    }

    public void createCart(Long memNo, ReqCart reqCart) {
        Member member = memberRepository.findById(memNo).orElseGet(Member::new);
        Stock stock = stockRepository.findById(reqCart.getStockNo()).orElseGet(Stock::new);

        Cart cart = Cart.builder()
                .member(member)
                .stock(stock)
                .stockQua(reqCart.getStockQua())
                .cartDt(LocalDateTime.now())
                .build();

        cartRepository.save(cart);
    }
}
