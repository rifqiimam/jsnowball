package com.rifqi.jsnowball.controller

import com.rifqi.jsnowball.entity.user.Jabatan
import com.rifqi.jsnowball.entity.user.User
import com.rifqi.jsnowball.resource.DefaultResponse

import com.rifqi.jsnowball.service.JabatanService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/Jabatan")
class JabatanController(
    val jabatanService: JabatanService
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping("/Create")
    fun create(@RequestBody jabatan: Jabatan): ResponseEntity<Void> {
        log.info("Message  : {}", "Starting jabatan : $jabatan")
        jabatanService.createJabatan(jabatan)
        log.info("Message  : {}", "finishing jabatan..")
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/Semua")
    fun getAllJabatan(): ResponseEntity<Any> {
        log.info("Message  : {}", "Starting jabatan :")
        val data = jabatanService.getAllJabatan()
        log.info("Message  : {}", "finish jabatan :")
        return ResponseEntity(
            DefaultResponse(
                status = "${data?.status}",
                message = "${data?.message}",
                data = data?.data,
                error = data?.error
            ), HttpStatus.OK
        )
    }


    @GetMapping("/{id}")
    fun getUserByIdJabatan(@PathVariable id: Long): ResponseEntity<Jabatan> {

        val jabatan = jabatanService.getByIdJabatan(id)

        return if (jabatan != null) {
            ResponseEntity.ok(jabatan)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
