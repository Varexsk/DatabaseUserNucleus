package vnj.sql2.commands.warpList;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import vnj.sql2.commands.Holder;
import vnj.sql2.entity.Warp;
import vnj.sql2.services.UserService;
import vnj.sql2.services.WarpService;

import java.util.ArrayList;
import java.util.Optional;

public class CommandWarpList extends Holder {

    @Override
    public CommandSpec cmdExecutor() {
        return CommandSpec.builder()
                .permission("sql.warp.list")
                .executor(this)
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            Optional<Player> playerSrc = ((Player) src).getPlayer();
            Player p = playerSrc.get();
//            String s = null;
//            if (args.hasAny("warplist")) {
//                Optional<String> cmd = args.getOne("warplist");
//                if (cmd.isPresent()) {
//                    s = cmd.get();
//                } else throw new CommandException(Text.of(PLUGINNAME, TextColors.RED, " Incorrect list key."));
//            }

            WarpService warpService = new WarpService();
            ArrayList<Warp> warps = (ArrayList<Warp>) warpService.getAllWarpByUserId(new UserService().getUserByName(p.getName()).getId());
            p.sendMessage(Text.of(PLUGINNAME, "Warp list: "));
            for (Warp warp:warps) {
                p.sendMessage(Text.of(warp.getWarp_name()));

            }

        } else return CommandResult.empty();
        return CommandResult.success();
    }
}
