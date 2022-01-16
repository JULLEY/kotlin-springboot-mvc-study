package com.programming.mvc.controller.response

import com.programming.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    @GetMapping("")
    fun getMapping(@RequestParam age : Int?): ResponseEntity<String> {
        // 코틀린스럽게
        // 엘비스연산자
        return age?.let {
            // age not null
            if(it < 20) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age는 20보다 커야합니다.")
            }
            ResponseEntity.ok("OK")
        }?: kotlin.run {
            // age null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age가 null 입니다.")
        }

        /*
        // 1. age == null -> 400 error
        if(age == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        }

        // 2. age < 20 -> 400 error
        if(age < 20){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OK")
        */
    }

    // 2. post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userRequest)
    }

    // 3. pust 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. detele 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}