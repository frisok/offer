package com.advidi.conversion.web

import com.advidi.conversion.domain.Conversion
import com.advidi.conversion.service.ConversionsService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException


@RestController
@RequestMapping("/conversion")
class ConversionRestController(private val conversionsService: ConversionsService) {

    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getConversions(): List<Conversion> = conversionsService.findAll()

    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun uploadConversions(@RequestParam("file") multipartFile: MultipartFile): ResponseEntity<Void> {

        return try {
            conversionsService.loadIntoDatabase(multipartFile)
            ResponseEntity.status(HttpStatus.OK).build()
        } catch (e: IOException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } catch (exception: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

}
