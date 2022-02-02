package com.programming.mvc.controller.put

import com.programming.mvc.model.http.Result
import com.programming.mvc.model.http.UserRequest
import com.programming.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String> {

        if(bindingResult.hasErrors()){
            // 500 error
            var msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append(field.toString() + " : " + message + "\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")

        // 0. Response
//        return UserResponse().apply {
//            // 1. result
//            this.result = Result().apply {
//                this.resultCode = "OK"
//                this.resultMessage = "success"
//            }
//        }.apply {
//            // 2. description
//            this.description = "~~~~~~~~~~~~~~~~~~~~~"
//        }.apply {
//            // 3. user mutable list
//            val userList = mutableListOf<UserRequest>()
//            userList.add(userRequest)
//            userList.add(UserRequest().apply {
//                this.name = "leo"
//                this.age = 32
//                this.email = "leo@leo.com"
//                this.address = "leo address"
//                this.phoneNumber = "010-1234-1234"
//            })
//            userList.add(UserRequest().apply {
//                this.name = "dd"
//                this.age = 32
//                this.email = "dd@dd.com"
//                this.address = "dd address"
//                this.phoneNumber = "010-1234-5678"
//            })
//            this.user = userList
//        }
    }
}