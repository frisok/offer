package com.advidi.offer.repository

import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import org.springframework.data.jpa.repository.JpaRepository

interface OfferConversionRepository : JpaRepository<OfferConversion, Long> {

    fun findFirstByOfferOrderByTimestampDesc(offer: Offer): List<OfferConversion>

}


