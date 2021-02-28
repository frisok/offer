package com.advidi.offer.service

import com.advidi.offer.domain.Offer
import com.advidi.offer.repository.OfferRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class OfferService(private val offerRepository: OfferRepository) {

    fun save(offer: Offer): Offer = offerRepository.save(offer)

    fun findAll(): List<Offer> = offerRepository.findAll()

    fun getOffer(id: Long): Offer = offerRepository.getOne(id)

}