package com.programming.mvc.controller.exception

import com.programming.mvc.model.http.Error
import com.programming.mvc.model.http.ErrorResponse
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(){
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    @GetMapping("")
    fun get(
            @NotBlank
            @Size(min = 2, max = 6)
            @RequestParam name:String,

            @Min(10)
            @RequestParam age: Int
    ): String{
        println(name)
        println(age)
        return name + " ||| " + age
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(e : ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse>{
        // 1. 에러 분석
        val errors = mutableListOf<Error>()

        e.constraintViolations.forEach{
            val field = it.propertyPath.last().name
            val message = it.message
            val error = Error().apply {
                this.filed = message
                this.message = message
            }
            errors.add(error)
        }

        // 2. ErrorResponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "9999"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.message = "요청에 에러가 발생하였습니다"
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()

        }
        // 3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)

    }


    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e : IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IndexOutOfBoundsException#### 컨트롤러에 직접 생성 시 공통적용된 Advice무시하고 이 메소드를 탄다")
    }
}