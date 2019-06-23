package vnj.sql2.commands;

import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import vnj.sql2.Sql;

public abstract class Holder extends Sql implements CommandExecutor {
    public abstract CommandSpec cmdExecutor();
}
