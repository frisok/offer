package com.advidi.offer.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "offer_conversion")
class OfferConversion(

        @Column
        var timestamp: LocalDateTime,

        @Column(name = "payout_total")
        var payoutTotal: BigDecimal,

        @Column(name = "received_total")
        var receivedTotal: BigDecimal,

        @ManyToOne
        var offer: Offer

) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator", allocationSize = 1)
    var id: Long = 0
}
