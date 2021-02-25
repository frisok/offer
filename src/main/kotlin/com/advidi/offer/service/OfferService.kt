package com.advidi.offer.service

import com.advidi.offer.repository.OfferRepository
import org.springframework.stereotype.Service

@Service
class OfferService(private val offerRepository: OfferRepository) {

    fun findAll() = offerRepository.findAll()

}