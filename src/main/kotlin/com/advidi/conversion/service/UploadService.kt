package com.advidi.conversion.service

import com.advidi.conversion.domain.Conversion
import com.opencsv.CSVReader
import com.opencsv.bean.CsvToBeanBuilder
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader


@Service
class UploadService(private val conversionsService: ConversionsService) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun loadIntoDatabase(multipartFile: MultipartFile) {
        val startTime: Long = System.currentTimeMillis()

        CsvToBeanBuilder<Conversion>(CSVReader(InputStreamReader(multipartFile.inputStream)))
                .withType(Conversion::class.java)
                .build()
                .stream()
                .forEach { c -> saveConversion(c) }

        log.debug("Processing file ${multipartFile.originalFilename} took ${System.currentTimeMillis() - startTime} ms")
    }

    private fun saveConversion(conversion: Conversion) = conversionsService.save(conversion)

}