package com.cinema.recommender.controller;

import com.cinema.recommender.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.recommender.dto.ValidatePromotionRequest;
import com.cinema.recommender.entity.Promotion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping("/validate")
    public Promotion validatePromotion(
            @RequestBody ValidatePromotionRequest request
    ) {
        return promotionService.validatePromotion(request.getCode());
    }
}
