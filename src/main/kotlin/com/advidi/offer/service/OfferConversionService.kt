package com.advidi.offer.service

import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import com.advidi.offer.repository.OfferConversionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
@Transactional
class OfferConversionService(private val offerConversionRepository: OfferConversionRepository) {

    fun save(offerConversion: OfferConversion): OfferConversion = offerConversionRepository.save(offerConversion)

    fun findLastOfferConversionBy(offer: Offer): OfferConversion? = offerConversionRepository.findFirstByOfferOrderByTimestampDesc(offer).elementAtOrNull(0)

    fun findFirstOfferConversionAfter(offer: Offer, startDate: LocalDateTime): OfferConversion? = offerConversionRepository.findFirstByOfferAndTimestampAfterOrderByTimestampAsc(offer, startDate).elementAtOrNull(0)

    fun findLastOfferConversionBefore(offer: Offer, endDate: LocalDateTime): OfferConversion? = offerConversionRepository.findFirstByOfferAndTimestampBeforeOrderByTimestampDesc(offer, endDate).elementAtOrNull(0)

    fun findAll(): List<OfferConversion> = offerConversionRepository.findAll()

}