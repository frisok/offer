package com.advidi.offer.repository

import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface OfferConversionRepository : JpaRepository<OfferConversion, Long> {

    fun findFirstByOfferOrderByTimestampDesc(offer: Offer): List<OfferConversion>

    fun findFirstByOfferAndTimestampAfterOrderByTimestampAsc(offer: Offer, startDate: LocalDateTime): List<OfferConversion>

    fun findFirstByOfferAndTimestampBeforeOrderByTimestampDesc(offer: Offer, endDate: LocalDateTime): List<OfferConversion>
}


