package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Promotion;
import com.cinema.recommender.repository.PromotionRepository;
import com.cinema.recommender.service.PromotionAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionAdminServiceImpl implements PromotionAdminService {

    private final PromotionRepository promotionRepository;

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion updatePromotion(Long id, Promotion promotion) {

        Promotion existingPromotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        existingPromotion.setCode(promotion.getCode());
        existingPromotion.setDescription(promotion.getDescription());
        existingPromotion.setDiscountPercent(promotion.getDiscountPercent());
        existingPromotion.setDiscountAmount(promotion.getDiscountAmount());
        existingPromotion.setMaxUses(promotion.getMaxUses());
        existingPromotion.setUsedCount(promotion.getUsedCount());
        existingPromotion.setStartDate(promotion.getStartDate());
        existingPromotion.setEndDate(promotion.getEndDate());
        existingPromotion.setIsActive(promotion.getIsActive());

        return promotionRepository.save(existingPromotion);
    }

    @Override
    public void deletePromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        promotionRepository.delete(promotion);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
    }
}
