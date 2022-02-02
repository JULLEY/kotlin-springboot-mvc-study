package com.programming.mvc.model.http

import javax.validation.constraints.*

data class UserRequest (
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name: String?=null,

    @field:PositiveOrZero
    var age: Int?=null,

    @field:Email    // 이메일형식 검증
    var email: String?=null,

    @field:NotBlank // 공백검증
    var address: String?=null,

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
    var phoneNumber: String?=null

    //    @JsonProperty("phone_number")
    //    var phoneNumber: String?=null
)