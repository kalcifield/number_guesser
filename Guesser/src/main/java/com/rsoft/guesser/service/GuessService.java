package com.rsoft.guesser.service;

import com.rsoft.guesser.entity.NumberEntity;
import com.rsoft.guesser.entity.ValidityType;
import com.rsoft.guesser.exception.CustomEntityNotFoundException;
import com.rsoft.guesser.repository.NumberEntityRepository;
import com.rsoft.guesser.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static com.rsoft.guesser.entity.ValidityType.*;

@Service
@RequiredArgsConstructor
public class GuessService {
    private final NumberEntityRepository numberEntityRepository;

    public ValidityType validateGuess(String sessionId, String value) {
        Optional<NumberEntity> numberEntity = numberEntityRepository.findById(sessionId);
        if (!numberEntity.isPresent()) {
            throw new CustomEntityNotFoundException("NumberEntity was not found with id " + sessionId);
        }

        int actual = numberEntity.get().getValue();
        int guess = Integer.parseInt(value);

        if (actual == guess) {
            setSolveTime(numberEntity.get());
            return CORRECT;
        } else if (actual > guess) {
            return HIGHER;
        }
        else {
            return LOWER;
        }
    }

    private void setSolveTime(NumberEntity numberEntity) {
        Instant creationDate = numberEntity.getCreationDate();
        Duration duration = Duration.between(creationDate, Instant.now());
        numberEntity.setSolutionTimeSeconds(duration.getSeconds());
        numberEntityRepository.save(numberEntity);
    }

    public String getPlayerSession() {
        int randomNumber = RandomGenerator.getRandomNumberUpTo(100);
        NumberEntity numberEntity = new NumberEntity()
                .setValue(randomNumber);
        numberEntity = numberEntityRepository.save(numberEntity);
        return obtainSessionId(numberEntity);
    }

    private String obtainSessionId(NumberEntity numberEntity) {
        return numberEntity.getId();
    }

}
