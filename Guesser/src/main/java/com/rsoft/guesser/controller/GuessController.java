package com.rsoft.guesser.controller;

import com.rsoft.guesser.entity.ValidityType;
import com.rsoft.guesser.service.GuessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${spring.data.rest.basePath}")
@CrossOrigin(origins = "http://localhost:5000")
public class GuessController {
    private final GuessService guessService;

    @RequestMapping("/start")
    public String initGuess() {
        return guessService.getPlayerSession();
    }

    @RequestMapping("/{sessionId}/{value}")
    public ValidityType makeGuess(@PathVariable(value = "sessionId") String sessionId,
                                  @PathVariable(value = "value") String value) {
        return guessService.validateGuess(sessionId, value);
    }

}

