package com.advidi.offer.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class OfferConversion(

        @Id
        val id: Long,

        @Column
        val timestamp: LocalDateTime,

        @Column
        val payoutTotal: BigDecimal,

        @Column
        val receivedTotal: BigDecimal,

        @ManyToOne
        val offer: Offer

)
