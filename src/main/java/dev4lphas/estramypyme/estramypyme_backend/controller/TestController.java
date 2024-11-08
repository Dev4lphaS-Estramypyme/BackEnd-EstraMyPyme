package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.Test;
import dev4lphas.estramypyme.estramypyme_backend.service.TestService;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping // crear test
    public Test createTest(@RequestBody Test test) {
        return testService.createTest(test);
    }

    @GetMapping("/{companyId}") // buscar tests por ID de empresa
    public List<Test> getTestsByCompanyId(@PathVariable Long companyId) { // Cambiado a Long
        return testService.getTestsByCompanyId(companyId);
    }

    @PutMapping("/edit") // editar test
    public Test updateTest(@RequestBody Test test) {
        return testService.updateTest(test);
    }
}