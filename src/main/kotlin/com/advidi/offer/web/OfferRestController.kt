package com.advidi.offer.web

import com.advidi.offer.domain.Offer
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OfferRestController

@GetMapping(produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
fun getOffers(): List<Offer> {
    return listOf(
            Offer(1, "www.test.com/1", "first"),
            Offer(2, "www.test.com/2", "second"),
            Offer(3, "www.test.com/3", "third")
    )
}
