package com.rifqi.jsnowball.repository.udang

import com.rifqi.jsnowball.entity.udang.Udang
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UdangRepository:JpaRepository<Udang,Long> {
    fun existsByEmail(email: String): Boolean
}