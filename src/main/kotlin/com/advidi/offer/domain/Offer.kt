package com.advidi.offer.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Offer(

        @Id
        val id: Long,

        @Column
        val url: String,

        @Column
        val name: String

)
