package old.org.grizz.game.config;

import old.org.grizz.game.commands.CommandHandlerBus;
import old.org.grizz.game.commands.impl.*;
import old.org.grizz.game.commands.impl.admin.AdminGiveItemCommand;
import old.org.grizz.game.commands.impl.admin.AdminPutItemCommand;
import old.org.grizz.game.commands.impl.admin.AdminShowPlayerListCommand;
import old.org.grizz.game.commands.impl.admin.AdminTeleportCommand;
import old.org.grizz.game.commands.impl.movement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tomasz.bielaszewski on 2015-04-27.
 */
@Configuration
public class CommandConfig {

    @Bean
    @Autowired
    public CommandHandlerBus commandHandlerBus(
            final LookAroundCommand lookAroundCommand,
            final MoveNorthCommand moveNorthCommand,
            final MoveSouthCommand moveSouthCommand,
            final MoveEastCommand moveEastCommand,
            final MoveWestCommand moveWestCommand,
            final MoveUpCommand moveUpCommand,
            final MoveDownCommand moveDownCommand,
            final PickUpCommand pickUpCommand,
            final DropCommand dropCommand,
            final ShowEquipmentCommand showEquipmentCommand,
            final AdminTeleportCommand adminTeleportCommand,
            final AdminGiveItemCommand adminGiveItemCommand,
            final AdminPutItemCommand adminPutItemCommand,
            final AdminShowPlayerListCommand adminShowPlayerListCommand,
            final UseEquipmentItemCommand useEquipmentItemCommand,
            final UseStaticLocationItemCommand useStaticLocationItemCommand
    ) {
        CommandHandlerBus commandHandlerBus = new CommandHandlerBus();

        commandHandlerBus.addCommand(lookAroundCommand);
        commandHandlerBus.addCommand(moveEastCommand);
        commandHandlerBus.addCommand(moveWestCommand);
        commandHandlerBus.addCommand(moveNorthCommand);
        commandHandlerBus.addCommand(moveSouthCommand);
        commandHandlerBus.addCommand(moveDownCommand);
        commandHandlerBus.addCommand(moveUpCommand);
        commandHandlerBus.addCommand(pickUpCommand);
        commandHandlerBus.addCommand(dropCommand);
        commandHandlerBus.addCommand(showEquipmentCommand);
        commandHandlerBus.addCommand(adminTeleportCommand);
        commandHandlerBus.addCommand(adminGiveItemCommand);
        commandHandlerBus.addCommand(adminPutItemCommand);
        commandHandlerBus.addCommand(adminShowPlayerListCommand);
        commandHandlerBus.addCommand(useEquipmentItemCommand);
        commandHandlerBus.addCommand(useStaticLocationItemCommand);

        return commandHandlerBus;
    }
}