package org.grizz.game.integration.tests;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.grizz.game.Game;
import org.grizz.game.integration.config.TestGameWithEmbeddedMongo;
import org.grizz.game.model.Equipment;
import org.grizz.game.model.Player;
import org.grizz.game.model.PlayerResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestGameWithEmbeddedMongo.class)
public class ProofOfConcept {
    public static final String PLAYER = "player";

    @Autowired
    private Game game;
    @Autowired
    private MongoOperations mongo;

    @Before
    public void init() {
        mongo.save(Player.builder()
                .name(PLAYER)
                .currentLocation("1")
                .equipment(Equipment.builder()
                        .backpack(Lists.newArrayList())
                        .build())
                .parameters(Maps.newHashMap())
                .build());
    }

    @Test
    public void name() throws Exception {
        PlayerResponse playerResponse = game.runCommand("spojrz", PLAYER);

        System.out.println(playerResponse);
    }
}
