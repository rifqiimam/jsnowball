package com.rifqi.jsnowball.controller

import com.rifqi.jsnowball.entity.user.User
import com.rifqi.jsnowball.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody user: User): ResponseEntity<Void> {
        userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {

        val user = userService.getById(id)

        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    /*@RestController

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/create") //
    fun createUser(@RequestBody request: RegisterUserRequest): ResponseEntity<Void> {
        userService.register(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}*/

}