package com.mealgo.wish.service;

import com.mealgo.meal.repository.MealRepository;
import com.mealgo.member.repository.MemberRepository;
import com.mealgo.wish.domain.dto.ResponseWish;
import com.mealgo.wish.domain.entity.Wish;
import com.mealgo.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;
    private final MealRepository mealRepository;

    public Page<ResponseWish> getWishListByMemNo(Integer memNo, Pageable pageable) {
        return wishRepository.findByMember_MemNo(memNo, pageable)
                .map(ResponseWish::from);
    }

    public void addWish(Integer memNo, Integer mealNo) {
        if (wishRepository.existsByMember_MemNoAndMeal_MealNo(memNo, mealNo)) return;

        Wish wish = Wish.builder()
                .member(memberRepository.findById(memNo).orElseThrow())
                .meal(mealRepository.findById(mealNo).orElseThrow())
                .build();
        wishRepository.save(wish);
    }

    @Transactional
    public void deleteWish(Integer wishNo) {
        wishRepository.deleteByWishNo(wishNo);
    }
}
