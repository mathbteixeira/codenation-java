package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable("id") Long id) {
        return companyService.findById(id);
    }

    @GetMapping
    public Iterable<Company> findAll(@PathParam("accelerationId") Long accelerationId,
             @PathParam("userId") Long userId) {
        if (accelerationId != null)
            return companyService.findByAccelerationId(accelerationId);
        else if (userId != null)
            return  companyService.findByUserId(userId);
        return null;
    }
}
