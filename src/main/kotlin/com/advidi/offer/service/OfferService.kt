package com.advidi.offer.service

import com.advidi.offer.domain.Offer
import com.advidi.offer.domain.OfferConversion
import com.advidi.offer.domain.dto.OfferTotalsDto
import com.advidi.offer.repository.OfferRepository
import org.apache.commons.lang3.time.StopWatch
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
@Transactional
class OfferService(private val offerRepository: OfferRepository, private val offerConversionService: OfferConversionService) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun save(offer: Offer): Offer = offerRepository.save(offer)

    fun findAll(): List<Offer> = offerRepository.findAll()

    fun getOffer(id: Long): Offer = offerRepository.getOne(id)

    fun getOfferTotals(name: String?, startDate: LocalDateTime?, endDate: LocalDateTime?, pageable: Pageable): Page<OfferTotalsDto> {
        val stopWatch = StopWatch()
        stopWatch.start()

        var offers: Page<Offer>
        if (name != null) {
            offers = offerRepository.findByName(name, pageable)
        } else {
            offers = offerRepository.findAll(pageable)
        }

        val result: List<OfferTotalsDto> = offers.stream()
                .map { o -> mapToOfferTotals(o, startDate ?: LocalDateTime.now().minusYears(30), endDate ?: LocalDateTime.now())}
                .collect(Collectors.toList())

        log.debug("Request to fetch OfferTotalsDto completed in ${stopWatch.getTime(TimeUnit.MILLISECONDS)} ms")

        return PageImpl(result)
    }

    private fun mapToOfferTotals(offer: Offer, startDate: LocalDateTime, endDate: LocalDateTime): OfferTotalsDto {

        val firstOfferConversion: OfferConversion? = offerConversionService.findFirstOfferConversionAfter(offer, startDate)
        val lastOfferConversion: OfferConversion? = offerConversionService.findLastOfferConversionBefore(offer, endDate)

        val payoutTotalAtStartDate: BigDecimal = firstOfferConversion?.payoutTotal ?: BigDecimal(0)
        val payoutTotalAtEndDate: BigDecimal = lastOfferConversion?.payoutTotal ?: BigDecimal(0)
        val payoutTotalForRange: BigDecimal = payoutTotalAtEndDate.minus(payoutTotalAtStartDate)

        val receivedTotalAtStartDate: BigDecimal = firstOfferConversion?.receivedTotal ?: BigDecimal(0)
        val receivedTotalAtEndDate: BigDecimal = lastOfferConversion?.receivedTotal ?: BigDecimal(0)
        val receivedTotal: BigDecimal = receivedTotalAtEndDate.minus(receivedTotalAtStartDate)

        return OfferTotalsDto(offer.id, offer.name, offer.url, payoutTotalForRange, receivedTotal)
    }

}