package com.rifqi.jsnowball.controller

import com.rifqi.jsnowball.entity.udang.Udang
import com.rifqi.jsnowball.service.UdangService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/udang")
class UdangController (
        val udangService: UdangService
) {
        @PostMapping
        fun create(@RequestBody udang: Udang): ResponseEntity<Void> {
                udangService.createUdang(udang)
                return ResponseEntity.status(HttpStatus.CREATED).build()
        }
}