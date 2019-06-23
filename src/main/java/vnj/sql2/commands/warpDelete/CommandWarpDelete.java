package vnj.sql2.commands.warpDelete;

import io.github.nucleuspowered.nucleus.api.NucleusAPI;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import vnj.sql2.commands.Holder;
import vnj.sql2.entity.Warp;
import vnj.sql2.services.WarpService;
import java.util.Optional;

public class CommandWarpDelete extends Holder {
    @Override
    public CommandSpec cmdExecutor() {
        return CommandSpec.builder()
                .permission("sql.warp.delete")
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("warp_name"))))
                .executor(this)
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            Optional<Player> playerSrc = ((Player) src).getPlayer();
            Player p = playerSrc.get();
            String s = null;
            if (args.hasAny("warp_name")) {
                Optional<String> cmd = args.getOne("warp_name");
                if (cmd.isPresent()) {
                    s = cmd.get();
                } else throw new CommandException(Text.of(PLUGINNAME, TextColors.RED, " Incorrect list key."));
            }
            if (s != null){
                WarpService warpService = new WarpService();
                Warp warp = warpService.getWarpByWarpName(s);
                if (p.getName().equals(warp.getUser().getName())){
                    warpService.removeWarpByName(s);
                    NucleusAPI.getWarpService().get().removeWarp(s);
                }

            }

        } else return CommandResult.empty();
        return CommandResult.success();
    }
}
