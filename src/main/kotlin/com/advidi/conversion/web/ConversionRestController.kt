package com.advidi.conversion.web

import com.advidi.conversion.domain.Conversion
import com.advidi.conversion.service.ConversionsService
import com.advidi.conversion.service.UploadService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/conversion")
class ConversionRestController(private val conversionsService: ConversionsService, private val uploadService: UploadService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getConversions(): ResponseEntity<List<Conversion>> = ResponseEntity.ok(conversionsService.findAll())

    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun uploadConversions(@RequestParam("file") multipartFile: MultipartFile): ResponseEntity<String> {

        val fileName = multipartFile.originalFilename

        return try {
            uploadService.loadIntoDatabase(multipartFile)
            ResponseEntity.ok("Upload file $fileName successful")
        } catch (e: Exception) {
            logAndCreateErrorResponse(e, fileName)
        }
    }

    private fun logAndCreateErrorResponse(exception: Exception, fileName: String?): ResponseEntity<String> {
        val message = exception.message
        log.error(message)
        return ResponseEntity.badRequest().body("Upload file $fileName failed: $message")
    }

}
