package com.kdp.fretquiz.game.db;

import com.kdp.fretquiz.game.db.entity.GameEntity;
import com.kdp.fretquiz.user.db.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{
    @Override
    List<GameEntity> findAll();

    @Query("""
            SELECT "user".* FROM "user"
            LEFT JOIN game_user ON "user".id = game_user.user
            WHERE game_user.game = :gameId
            """)
    List<UserEntity> findUsers(@Param("gameId") Long gameId);

    @Query("""
            SELECT game_user.game FROM game_user
            LEFT JOIN "user" ON "user".id = game_user.user
            WHERE game_user.user = :userId
            """)
    Optional<Long> findGameId(@Param("userId") Long userId);
}
