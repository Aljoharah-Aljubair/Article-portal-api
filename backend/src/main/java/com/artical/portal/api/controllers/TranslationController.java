package com.artical.portal.api.controllers;

import com.artical.portal.api.dto.TranslationRequest;
import com.artical.portal.api.dto.TranslationResponse;
import com.artical.portal.api.service.impl.TranslationService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/translate")
    public class TranslationController {
        private final TranslationService translationService;

        public TranslationController(TranslationService translationService) {
            this.translationService = translationService;
        }

        @PostMapping
        public TranslationResponse translateText(@RequestBody TranslationRequest request) {
            return translationService.translateText(request.getText());
        }
    }

