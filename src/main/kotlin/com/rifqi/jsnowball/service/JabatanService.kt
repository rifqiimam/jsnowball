package com.rifqi.jsnowball.service
import com.rifqi.jsnowball.entity.user.Jabatan
import com.rifqi.jsnowball.repository.user.JabatanRepository
import com.rifqi.jsnowball.resource.DefaultResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class JabatanService(
    val jabatanRepository: JabatanRepository
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    fun createJabatan(jabatan: Jabatan) {
        log.info("Message  : {}", "Starting service membuat jabatan..")
        if (jabatanRepository.existsBynamaJabatan(jabatan.namaJabatan)) {
            log.info("Message  : {}", "gagal membuat jabatan..")
            throw Exception("Jabatan already exists")
        }

        val jabatan = Jabatan(
            id_jabatan = jabatan.id_jabatan,
            namaJabatan = jabatan.namaJabatan,
            kodeJabatan = jabatan.kodeJabatan
        )

        jabatanRepository.save(jabatan)
    }

    fun getAllJabatan():DefaultResponse{
        log.info("Message : {}", "Start trying get all data gagal burekol")

        val data = jabatanRepository.findAllJabatan()
        log.info("Message : {}", "Success getAllGagalBurekol()")

        return DefaultResponse("00", "Success get all data gagal burekol.", data, null)
    }


    fun getByIdJabatan(id_jabatan: Long): Jabatan? {

        return jabatanRepository.findById(id_jabatan).orElse(null)
    }
}