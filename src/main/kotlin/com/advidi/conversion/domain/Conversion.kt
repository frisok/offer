package com.advidi.conversion.domain

import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Conversion(

        @Id
        val id: Long,

        @Column
        val offerId: Long,

        @Column
        val affiliate: Long,

        @Column
        val timestamp: ZonedDateTime,

        @Column
        val payout: BigDecimal,

        @Column
        val received: BigDecimal

)