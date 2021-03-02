package com.advidi.offer.web

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/authenticate")
class AuthenticationRestController() {

    private val log = LoggerFactory.getLogger(javaClass)


    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun authenticate(): ResponseEntity<Void> {
        return ResponseEntity.status(HttpStatus.OK).build()
    }

}