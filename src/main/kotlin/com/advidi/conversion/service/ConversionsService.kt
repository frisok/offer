package com.advidi.conversion.service

import com.advidi.conversion.domain.Conversion
import com.advidi.conversion.repository.ConversionRepository
import com.opencsv.CSVReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader
import java.time.LocalDateTime

@Service
class ConversionsService() {

    @Autowired
    private lateinit var conversionRepository: ConversionRepository;

    fun findAll(): List<Conversion> = conversionRepository.findAll()

    fun loadIntoDatabase(multipartFile: MultipartFile) {
        val before: Long = System.currentTimeMillis()
        val reader = CSVReader(InputStreamReader(multipartFile.inputStream))
        reader.asSequence().forEach { line -> println(line[0]) }
        val after: Long = System.currentTimeMillis()
        println(after- before)
    }

}