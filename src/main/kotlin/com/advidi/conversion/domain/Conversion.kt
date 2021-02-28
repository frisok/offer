package com.advidi.conversion.domain

import com.opencsv.bean.CsvBindByPosition
import com.opencsv.bean.CsvDate
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Conversion(

        @Id
        @CsvBindByPosition(position = 0)
        var id: Long,

        @Column(name = "offer_id")
        @CsvBindByPosition(position = 1)
        var offerId: Long,

        @Column
        @CsvBindByPosition(position = 2)
        var affiliate: Long,

        @Column
        @CsvBindByPosition(position = 3)
        @CsvDate("yyyy-MM-dd HH:mm:ss")
        var timestamp: LocalDateTime,

        @Column
        @CsvBindByPosition(position = 4)
        var payout: BigDecimal,

        @Column
        @CsvBindByPosition(position = 5)
        var received: BigDecimal,

        @Column
        var published: Boolean

) {
        override fun toString(): String {
                return "Conversion(id=$id, offerId=$offerId, affiliate=$affiliate, timestamp=$timestamp, payout=$payout, received=$received), published=$published"
        }
}