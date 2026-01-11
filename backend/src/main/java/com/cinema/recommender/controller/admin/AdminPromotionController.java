package com.cinema.recommender.controller.admin;

import com.cinema.recommender.entity.Promotion;
import com.cinema.recommender.service.PromotionAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/promotions")
@RequiredArgsConstructor
public class AdminPromotionController {

    private final PromotionAdminService promotionAdminService;

    @PostMapping
    public Promotion createPromotion(@RequestBody Promotion promotion) {
        return promotionAdminService.createPromotion(promotion);
    }

    @PutMapping("/{id}")
    public Promotion updatePromotion(
            @PathVariable Long id,
            @RequestBody Promotion promotion
    ) {
        return promotionAdminService.updatePromotion(id, promotion);
    }

    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable Long id) {
        promotionAdminService.deletePromotion(id);
    }

    @GetMapping
    public List<Promotion> getAllPromotions() {
        return promotionAdminService.getAllPromotions();
    }

    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable Long id) {
        return promotionAdminService.getPromotionById(id);
    }
}
