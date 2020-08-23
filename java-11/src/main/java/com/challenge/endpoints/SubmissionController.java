package com.challenge.endpoints;

import com.challenge.entity.Submission;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public Iterable<Submission> findAll(@PathParam("challengeId") Long challengeId,
                                        @PathParam("accelerationId") Long accelerationId) {
        return submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }
}
