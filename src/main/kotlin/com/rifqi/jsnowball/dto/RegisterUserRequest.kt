package com.rifqi.jsnowball.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class RegisterJabatanRequest(

    @field:NotBlank
    @field:Size(max = 100)
    val username: String ,

    @field:NotBlank
    @field:Size(max = 100)
    val password: String ,

    @field:NotBlank
    @field:Size(max = 100)
    val name: String
)



