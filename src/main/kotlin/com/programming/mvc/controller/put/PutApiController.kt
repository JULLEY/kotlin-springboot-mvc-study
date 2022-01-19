package com.programming.mvc.controller.put

import com.programming.mvc.model.http.Result
import com.programming.mvc.model.http.UserRequest
import com.programming.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping-put-method"
    }

    @PutMapping("/put-mapping/object")
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. Response
        return UserResponse().apply {
            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "success"
            }
        }.apply {
            // 2. description
            this.description = "~~~~~~~~~~~~~~~~~~~~~"
        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "leo"
                this.age = 32
                this.email = "leo@leo.com"
                this.address = "leo address"
                this.phoneNumber = "010-1234-1234"
            })
            userList.add(UserRequest().apply {
                this.name = "zzieuni"
                this.age = 30
                this.email = "zzieuni@zzieuni.com"
                this.address = "zzieuni address"
                this.phoneNumber = "010-7128-4284"
            })
            this.user = userList
        }
    }
}