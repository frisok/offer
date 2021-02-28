package com.advidi.conversion.service

import com.advidi.conversion.domain.Conversion
import com.advidi.conversion.repository.ConversionRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class ConversionsService {

    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var conversionRepository: ConversionRepository;

    fun getConversion(id: Long): Conversion = conversionRepository.getOne(id)

    fun findAll(): List<Conversion> = conversionRepository.findAll()

    fun findUnpublished(): List<Conversion> {
        return conversionRepository.findFirst2000ByPublishedOrderByTimestampAsc(false)
    }

    fun save(conversion: Conversion) = conversionRepository.save(conversion)

    fun updatePublished(id: Long, published: Boolean = true) {
        conversionRepository.updatePublished(id, published)
    }

}