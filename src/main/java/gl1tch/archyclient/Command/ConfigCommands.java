package gl1tch.archyclient.Command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import gl1tch.archyclient.ArchyClient;
import gl1tch.archyclient.Util.ModConfigHandler;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ConfigCommands {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext buildContext) {
        dispatcher.register(ClientCommandManager.literal("autoGG")
                .then(ClientCommandManager.argument("msg", StringArgumentType.greedyString())
                        .executes((context) -> {
                            String msg = StringArgumentType.getString(context, "msg");


                            ArchyClient.configOptions.setAutoGG(msg.replaceAll("\"", "\\\""));
                            context.getSource().getPlayer().displayClientMessage(Component.literal("AutoGG Message set to: " + msg), false);
                            ModConfigHandler.writeClientConfig();

                            return 0;
                        })));

        dispatcher.register(ClientCommandManager.literal("autotpa")
                .then(ClientCommandManager.literal("acceptadd")
                        .then(ClientCommandManager.argument("target", StringArgumentType.string())
                                .executes((context) -> {
                                    List<String> players = ArchyClient.configOptions.getAutoTPAACCEPT();
                                    String target = StringArgumentType.getString(context, "target");


                                    players.add(target);

                                    context.getSource().getPlayer().displayClientMessage(Component.literal("Attempted to add '" + target + "' to AutoTPAACCEPT"), false);

                                    ArchyClient.configOptions.setAutoTPAACCEPT(players);

                                    ModConfigHandler.writeClientConfig();

                                    return 0;
                                })))

                .then(ClientCommandManager.literal("acceptremove")
                        .then(ClientCommandManager.argument("target", StringArgumentType.string())
                                .executes((context) -> {
                                    List<String> players = ArchyClient.configOptions.getAutoTPAACCEPT();
                                    String target = StringArgumentType.getString(context, "target");


                                    players.remove(target);

                                    context.getSource().getPlayer().displayClientMessage(Component.literal("Attempted to remove '" + target + "' from AutoTPAACCEPT"), false);

                                    ArchyClient.configOptions.setAutoTPAACCEPT(players);

                                    ModConfigHandler.writeClientConfig();

                                    return 0;
                                }))));

        dispatcher.register(ClientCommandManager.literal("autotorture")
                .then(ClientCommandManager.literal("add")
                        .then(ClientCommandManager.argument("target", StringArgumentType.string())
                                .executes((context) -> {
                                    List<String> players = ArchyClient.configOptions.getAutoTPAACCEPT();
                                    String target = StringArgumentType.getString(context, "target");


                                    if (players.stream().count() == 3) {
                                        context.getSource().getPlayer().displayClientMessage(Component.literal("\u00a7cUnable to add another player, you must remove one first!"), false);
                                        return 0;
                                    }

                                    if (players.stream().count() > 3) {
                                        context.getSource().getPlayer().displayClientMessage(Component.literal("\u00a7cUnable to add another player, you have already exceeded the maximum player count, this may get you kicked for spamming!"), false);
                                        return 0;
                                    }
                                    
                                    players.add(target);

                                    context.getSource().getPlayer().displayClientMessage(Component.literal("Attempted to add '" + target + "' to AutoTPAACCEPT"), false);

                                    ArchyClient.configOptions.setAutoTorture(players);

                                    ModConfigHandler.writeClientConfig();

                                    return 0;
                                })))

                .then(ClientCommandManager.literal("remove")
                        .then(ClientCommandManager.argument("target", StringArgumentType.string())
                                .executes((context) -> {
                                    List<String> players = ArchyClient.configOptions.getAutoTPAACCEPT();
                                    String target = StringArgumentType.getString(context, "target");


                                    players.remove(target);

                                    context.getSource().getPlayer().displayClientMessage(Component.literal("Attempted to remove '" + target + "' from AutoTPAACCEPT"), false);

                                    ArchyClient.configOptions.setAutoTorture(players);

                                    ModConfigHandler.writeClientConfig();

                                    return 0;
                                }))));
    }
}
