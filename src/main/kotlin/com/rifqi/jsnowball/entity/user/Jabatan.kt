package com.rifqi.jsnowball.entity.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name ="jabatan")
data class Jabatan (

    @Id
    @Column(name = "id_jabatan")
    val id_jabatan: Long? = null,

    @Column(name = "nama_jabatan")
    val namaJabatan: String,

    @Column(name = "kode_jabatan")
    val kodeJabatan: String
)
