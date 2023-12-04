package com.rifqi.jsnowball.service

import com.rifqi.jsnowball.entity.udang.Udang
import com.rifqi.jsnowball.repository.udang.UdangRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class UdangService(
    val udangRepository: UdangRepository
) {

    fun createUdang(udang: Udang) {
        if (udangRepository.existsByEmail(udang.email)) {
            throw Exception("email already exists")
        }

        val udang = Udang(
            email = udang.email,
            name = udang.name,
            phone_number = udang.phone_number,
            log_last_time = Timestamp(System.currentTimeMillis())
        )

        udangRepository.save(udang)
    }

    fun getById(id: Long): Udang? {
        return udangRepository.findById(id).orElse(null)
    }

    fun getAllUdangs(): List<Udang> {
        return udangRepository.findAll()
    }

    fun updateUdang(udang: Udang) {
        udangRepository.save(udang)
    }

    fun deleteUdang(id: Long) {
        udangRepository.deleteById(id)
    }
}