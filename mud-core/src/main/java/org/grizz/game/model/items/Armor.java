package org.grizz.game.model.items;

import lombok.Data;
import lombok.experimental.Builder;

import java.util.List;

@Data
@Builder
public class Armor implements Item {
    private final ItemType itemType = ItemType.ARMOR;

    private String id;
    private String name;
    private String description;
    private List<ScriptCommandDto> commands;
}
