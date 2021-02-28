package com.advidi.conversion.service

import com.advidi.conversion.domain.Conversion
import com.advidi.offer.service.ConversionSubscriber
import org.apache.commons.lang3.time.StopWatch
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit


@Service
class ConversionPublisher(private val conversionsService: ConversionsService, private val conversionSubscriber: ConversionSubscriber) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedDelay = 10000)
    fun publish() {
        log.debug("Start cron job to publish Conversions")
        val stopWatch = StopWatch()
        stopWatch.start()
        conversionsService.findUnpublished()
                .stream()
                .forEach { c -> publishAndUpdate(c) }

        stopWatch.stop()
        log.debug("Cron job to publish Conversions completed in ${stopWatch.getTime(TimeUnit.MILLISECONDS)} ms")
    }

    fun publishAndUpdate(conversion: Conversion) {
        conversionSubscriber.convertToOfferConversionAndSave(conversion)
        conversionsService.updatePublished(conversion.id)
    }

}