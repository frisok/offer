package com.advidi.offer.web

import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import com.advidi.offer.domain.dto.OfferTotalsDto
import com.advidi.offer.service.OfferConversionService
import com.advidi.offer.service.OfferService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/offer")
class OfferRestController(private val offerService: OfferService, private val offerConversionService: OfferConversionService) {

    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOffers(): ResponseEntity<List<Offer>> = ResponseEntity.ok(offerService.findAll())

    @GetMapping("/conversion/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOfferConversions(): ResponseEntity<List<OfferConversion>> = ResponseEntity.ok(offerConversionService.findAll())

    @GetMapping("/totals", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOfferConversionsFiltered(@RequestParam(name = "name", required = false) name: String?,
                                    @RequestParam(name = "start", required = false) startDate: LocalDateTime?,
                                    @RequestParam(name = "end", required = false) endDate: LocalDateTime?,
                                    @RequestParam(name = "page") pageable: Pageable
    ): ResponseEntity<Page<OfferTotalsDto>> = ResponseEntity.ok(offerService.getOfferTotals(name, startDate, endDate, pageable))

}
