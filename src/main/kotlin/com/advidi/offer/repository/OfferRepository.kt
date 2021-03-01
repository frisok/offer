package com.advidi.offer.repository

import com.advidi.offer.domain.Offer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OfferRepository : JpaRepository<Offer, Long> {


    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Offer>

}
