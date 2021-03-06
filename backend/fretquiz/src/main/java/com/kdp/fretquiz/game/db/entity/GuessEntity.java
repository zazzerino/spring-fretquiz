package com.kdp.fretquiz.game.db.entity;

import com.kdp.fretquiz.game.Guess;
import com.kdp.fretquiz.game.ImmutableGuess;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("guess")
public class GuessEntity
{
    public final Long userId;
    public final boolean isCorrect;

    public GuessEntity(Long userId, boolean isCorrect)
    {
        this.userId = userId;
        this.isCorrect = isCorrect;
    }

    public static GuessEntity from(Guess guess)
    {
        return new GuessEntity(guess.userId(), guess.isCorrect());
    }

    public Guess toGuess()
    {
        return ImmutableGuess.builder()
                .userId(userId)
                .isCorrect(isCorrect)
                .build();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuessEntity that = (GuessEntity) o;
        return isCorrect == that.isCorrect && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, isCorrect);
    }

    @Override
    public String toString()
    {
        return "GuessEntity{" +
                "userId=" + userId +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
