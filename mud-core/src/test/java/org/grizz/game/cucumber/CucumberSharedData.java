package org.grizz.game.cucumber;

import lombok.Getter;
import lombok.Setter;
import org.grizz.game.model.PlayerResponse;

@Getter
@Setter
public class CucumberSharedData {
    private PlayerResponse response;
    private String currentPlayer;
    private String locationBeforeCommand;
    private String locationAfterCommand;
}
