package com.advidi.offer.service

import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import com.advidi.offer.repository.OfferConversionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class OfferConversionService(private val offerConversionRepository: OfferConversionRepository) {

    fun save(offerConversion: OfferConversion): OfferConversion = offerConversionRepository.save(offerConversion)

    fun findLastOfferConversionBy(offer: Offer): OfferConversion? {
        val offerConversions = offerConversionRepository.findFirstByOfferOrderByTimestampDesc(offer)
        return offerConversions.elementAtOrNull(0)
    }

    fun findAll(): List<OfferConversion> = offerConversionRepository.findAll()

}