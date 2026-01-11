package com.cinema.recommender.service;

import com.cinema.recommender.entity.Promotion;

import java.util.List;

public interface PromotionAdminService {

    Promotion createPromotion(Promotion promotion);

    Promotion updatePromotion(Long id, Promotion promotion);

    void deletePromotion(Long id);

    List<Promotion> getAllPromotions();

    Promotion getPromotionById(Long id);
}
