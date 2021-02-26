package com.advidi.conversion.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Conversion(

        @Id
        val id: Long,

        @Column(name = "offer_id")
        val offerId: Long,

        @Column
        val affiliate: Long,

        @Column
        val timestamp: LocalDateTime,

        @Column
        val payout: BigDecimal,

        @Column
        val received: BigDecimal

)