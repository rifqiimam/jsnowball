package com.rifqi.jsnowball.repository.user

import com.rifqi.jsnowball.entity.user.Jabatan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface JabatanRepository:JpaRepository<Jabatan,Long> {
    fun existsBynamaJabatan(namaJabatan: String): Boolean
    @Query(
        value = "SELECT * from jabatan",
        nativeQuery = true
    )
    fun findAllJabatan():List<Jabatan>
}