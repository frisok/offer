package com.advidi.offer.service

import com.advidi.conversion.domain.Conversion
import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class ConversionSubscriber(private val offerConversionService: OfferConversionService, private val offerService: OfferService) {

    fun convertToOfferConversionAndSave(conversion: Conversion): OfferConversion {

        val offer: Offer = offerService.getOffer(conversion.offerId)
        val lastOfferConversion: OfferConversion? = offerConversionService.findLastOfferConversionBy(offer)

        val payoutTotal = lastOfferConversion?.payoutTotal ?: BigDecimal(0)
        val newPayoutTotal = payoutTotal.plus(conversion.payout)

        val receivedTotal = lastOfferConversion?.receivedTotal ?: BigDecimal(0)
        val newReceivedTotal = receivedTotal.plus(conversion.received)

        return offerConversionService.save(OfferConversion(conversion.timestamp, newPayoutTotal, newReceivedTotal, offer));
    }

}