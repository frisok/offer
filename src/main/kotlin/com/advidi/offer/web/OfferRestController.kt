package com.advidi.offer.web

import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import com.advidi.offer.service.OfferConversionService
import com.advidi.offer.service.OfferService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/offer")
class OfferRestController(private val offerService: OfferService, private val offerConversionService: OfferConversionService) {

    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOffers(): List<Offer> = offerService.findAll()

    @GetMapping("/conversion/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOfferConversions(): List<OfferConversion> = offerConversionService.findAll()

}
