package com.programming.mvc.controller.get

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
internal class GetApiController {

    @GetMapping(path = ["/hello"])
    fun hello(): String{
        return "hello kotlin";
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age:Int): String{
        println("${name}, ${age}");
        return name + " " + age;
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age:Int): String{
        var name = "kotlin"

        println("${_name}, ${age}");
        return _name + " " + age;
    }

    @GetMapping("/get-mapping/queryParam")
    fun queryParam(@RequestParam(value = "name") name: String, @RequestParam(value = "age") age:Int): String{
        println("${name}, ${age}");
        return name + " " + age;
    }
}
