package com.advidi.offer.domain.dto

import java.math.BigDecimal

data class OfferTotalsDto (

        val offerId: Long,

        val name: String,

        val url: String,

        val payoutTotal: BigDecimal,

        val receivedTotal: BigDecimal
)