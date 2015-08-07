package org.grizz.game.service.impl;

import org.grizz.game.model.PlayerContext;
import org.grizz.game.model.PlayerResponse;
import org.grizz.game.service.ScriptRunnerService;
import org.springframework.stereotype.Component;

/**
 * Created by tomasz.bielaszewski on 2015-08-06.
 */
@Component
public class ScriptRunnerServiceImpl implements ScriptRunnerService {

    @Override
    public void execute(final String command, final String scriptId, final PlayerContext playerContext, final PlayerResponse response) {

    }
}
