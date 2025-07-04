package linhlt.project.backend_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "Hello Controller")
public class HelloController {

    @Operation(summary = "Test API", description = "detail")
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
