package com.rifqi.jsnowball.repository.user

import com.rifqi.jsnowball.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{
    fun existsByUsername(username: String): Boolean
}