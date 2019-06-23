package vnj.sql2;

import com.google.inject.Inject;
import io.github.nucleuspowered.nucleus.api.events.NucleusRTPEvent;
import io.github.nucleuspowered.nucleus.api.events.NucleusWarpEvent;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.dispatcher.SimpleDispatcher;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.permission.SubjectData;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Tristate;
import vnj.sql2.commands.warpList.CommandWarpList;
import vnj.sql2.dao.SqlExecutor;
import vnj.sql2.entity.User;
import vnj.sql2.entity.Warp;
import vnj.sql2.services.UserService;
import vnj.sql2.services.WarpService;

import java.sql.SQLException;
import java.util.Optional;


@Plugin(
        id = "sql",
        name = "connector",
        version = "1.2-SNAPSHOT"
)

public class Sql {

    protected final Text PLUGINNAME = Text.of(TextColors.DARK_PURPLE, "[Sql] ");
    private UserService userService = new UserService();
    private WarpService warpService = new WarpService();

    @Inject
    private Logger logger;

    @Inject
    private void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return this.logger;
    }

    @Listener
    public void OnInit(GameInitializationEvent event){
        new Vnjlog(this);
        new SqlExecutor(this);
        SimpleDispatcher rootCommand = new SimpleDispatcher();
        rootCommand.register(new CommandWarpList().cmdExecutor(), "list");
        rootCommand.register(new CommandWarpList().cmdExecutor(), "delete");
        Sponge.getCommandManager().register(this, rootCommand, "wrp");
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) throws SQLException {

    }

    @Listener
    public void onPlayerLoad(ClientConnectionEvent.Join event) {
        Player user = event.getTargetEntity();
        logger.info("User name : " + user.getName());
        if (!userService.isExist(user.getName())) {
            User newUser = new User(user.getName(), 2, 2);
            userService.addNewUser(newUser);
        }
    }

    @Listener
    public void onWarpSetEvent(NucleusWarpEvent.Create event) {
        Cause cause = event.getCause();
        Optional<Player> player = cause.first(Player.class);
        if (player.isPresent()){
            Player user = player.get();
            userService.changeWarpByName(user.getName(), false);
            User newUser = userService.getUserByName(user.getName());
            Warp warp = new Warp(newUser, event.getName(), 0);
            warpService.addWarp(warp);
            logger.info("" + event.getName());
            if (userService.getUserByName(user.getName()).getWarp() <= 0){
                user.getSubjectData().setPermission(SubjectData.GLOBAL_CONTEXT, "nucleus.warp.set.base", Tristate.FALSE);
            }
        }
    }

    @Listener
    public void onWarpEvent(NucleusWarpEvent event) {
        warpService.incrementWarpCount(event.getName());
    }

    @Listener
    public void onRtp(NucleusRTPEvent event){
        Cause cause = event.getCause();
        Optional<Player> player = cause.first(Player.class);
        if (player.isPresent()) {
            Player user = player.get();
            userService.changeRtpByName(user.getName(), false);
            if (userService.getUserByName(user.getName()).getRtp() <= 0){
                user.getSubjectData().setPermission(SubjectData.GLOBAL_CONTEXT, "nucleus.rtp.base", Tristate.FALSE);
            }
        }
    }
}
