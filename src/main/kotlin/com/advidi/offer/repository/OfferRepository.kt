package com.advidi.offer.repository

import com.advidi.offer.domain.Offer
import org.springframework.data.jpa.repository.JpaRepository

interface OfferRepository : JpaRepository<Offer, Long>
