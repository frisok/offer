package com.advidi.offer.domain

import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class OfferConversion(

        @Id
        val id: Long,

        @Column
        val timestamp: ZonedDateTime,

        @Column
        val payout: BigDecimal,

        @Column
        val received: BigDecimal,

        @ManyToOne
        val offer: Offer

)
