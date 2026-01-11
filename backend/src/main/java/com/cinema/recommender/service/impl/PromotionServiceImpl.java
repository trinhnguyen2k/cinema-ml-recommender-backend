package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Promotion;
import com.cinema.recommender.repository.PromotionRepository;
import com.cinema.recommender.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    public Promotion validatePromotion(String code) {
        if (code == null || code.isBlank()) {
            throw new RuntimeException("Promotion code is empty");
        }

        // Chi lay promotion code isActive = true
        return promotionRepository
                .findByCodeAndIsActiveTrue(code)
                .orElseThrow(() -> new RuntimeException("Invalid or inactive promotion code"));
    }

    @Override
    public int applyPromotion(Promotion promotion, int totalPrice) {

        if (promotion.getDiscountPercent() != null) {
            totalPrice -= totalPrice * promotion.getDiscountPercent() / 100;
        } else if (promotion.getDiscountAmount() != null) {
            totalPrice -= promotion.getDiscountAmount();
        }

        if (totalPrice < 0) {
            totalPrice = 0;
        }

        return totalPrice;
    }
}
