package com.rifqi.jsnowball.service

import com.rifqi.jsnowball.entity.user.User
import com.rifqi.jsnowball.repository.user.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun createUser(user: User) {
        if (userRepository.existsByUsername(user.username)) {
            throw Exception("Username already exists")
        }

        val user = User(
            username = user.username,
            password = BCryptPasswordEncoder().encode(user.password),
            name = user.name
        )

       userRepository.save(user)
    }
    fun getById(id: Long): User? {

        return userRepository.findById(id).orElse(null)
    }
}