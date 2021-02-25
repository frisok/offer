package com.advidi.offer.web

import com.advidi.offer.domain.Offer
import com.advidi.offer.service.OfferService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OfferRestController(private val offerService: OfferService) {

    @GetMapping("/offer", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOffers(): List<Offer> = offerService.findAll()

}
