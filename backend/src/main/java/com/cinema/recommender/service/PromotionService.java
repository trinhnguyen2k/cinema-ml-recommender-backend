package com.cinema.recommender.service;

import com.cinema.recommender.entity.Promotion;

public interface PromotionService {

    Promotion validatePromotion(String code);

    int applyPromotion(Promotion promotion, int totalPrice);
}
