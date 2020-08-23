package com.challenge.endpoints;

import com.challenge.entity.Candidate;
import com.challenge.entity.Company;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public Optional<Candidate> findById(@PathVariable("userId") Long userId,
            @PathVariable("accelerationId") Long accelerationId,
            @PathVariable("companyId") Long companyId) {
        return candidateService.findById(userId, companyId, accelerationId);
    }

    @GetMapping
    public Iterable<Candidate> findAll(@PathParam("accelerationId") Long accelerationId,
           @PathParam("companyId") Long companyId) {
        if (accelerationId != null)
            return candidateService.findByAccelerationId(accelerationId);
        else if (companyId != null)
            return  candidateService.findByCompanyId(companyId);
        return null;
    }
}