package org.grizz.game.command.parsers.system.movement;

import org.grizz.game.command.executors.system.MoveCommandExecutor;
import org.grizz.game.model.Direction;
import org.grizz.game.model.Player;
import org.grizz.game.model.PlayerResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NorthMoveCommandTest {
    @Mock
    private Environment environment;
    @Mock
    private MoveCommandExecutor commandExecutor;

    @InjectMocks
    private NorthMoveCommand command = new NorthMoveCommand(environment);

    @Before
    public void setUp() {
        when(environment.getProperty(command.getClass().getCanonicalName()))
                .thenReturn("north;" +
                        "polnoc;" +
                        "idz na polnoc");
    }

    @Test
    public void testAccept() throws Exception {
        assertTrue(command.accept("north"));
        assertTrue(command.accept("polnoc"));
        assertTrue(command.accept("idz na polnoc"));
    }

    @Test
    public void testExecute() throws Exception {
        Player player = Player.builder().build();
        PlayerResponse response = new PlayerResponse();

        command.execute("north", player, response);

        verify(commandExecutor).move(Direction.NORTH, player, response);
    }
}