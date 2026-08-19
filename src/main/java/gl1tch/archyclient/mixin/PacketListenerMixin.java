package gl1tch.archyclient.mixin;

import gl1tch.archyclient.ArchyClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class PacketListenerMixin {
    @Inject(at = @At("TAIL"), method = "handleSystemChat")
    private void receiveServerMessage(ClientboundSystemChatPacket clientboundSystemChatPacket, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        boolean checkedKilled = false;


        if (ArchyClient.configOptions.getAutoGGActive()) {
            if (clientboundSystemChatPacket.content().toString().contains("death.attack")) {
                for (String str : clientboundSystemChatPacket.content().toString().split(",")) {
                    if (checkedKilled) {
                        if (str.contains(client.player.getName().getString())) {
                            client.player.connection.sendChat(ArchyClient.configOptions.getAutoGG());
                            break;
                        }
                    } else {
                        if (str.contains("insert")) {
                            if (str.contains(client.player.getName().getString())) {
                                break;
                            }

                            checkedKilled = true;
                        }
                    }
                }
            }
        }

        if (ArchyClient.configOptions.getAutoTPAACCEPTActive()) {
            if (clientboundSystemChatPacket.content().getString().contains("has requested")) {
//            client.player.displayClientMessage(Component.literal(clientboundSystemChatPacket.content().toString()), false);

                for (String name : ArchyClient.configOptions.getAutoTPAACCEPT()) {
                    if (clientboundSystemChatPacket.content().getString().contains(name + " has requested")) {
                        if (clientboundSystemChatPacket.content().toString().contains("{" + name + "}")) {
                            client.player.connection.sendCommand("tpaccept " + name);
                            break;
                        }
                    }
                }
            }
        }

//        client.player.displayClientMessage(Component.literal(clientboundSystemChatPacket.content().toString()), false);
    }

    @Inject(at = @At("TAIL"), method = "handleSetScore")
    private void scoreBoard(ClientboundSetScorePacket clientboundSetScorePacket, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();


        if (client.player != null) {
            if (ArchyClient.configOptions.getAutoSkipAdminActive() && client.player.getDisplayName().toString().contains("literal{§4[§lADMIN§4] §f}")) {
                try {
                    String str = String.valueOf(Integer.parseInt(ArchyClient.configOptions.getAutoSkipAdmin()) - 1);

                    if (ArchyClient.configOptions.getAutoSkipAdmin().contains("10")) {
                        client.player.connection.sendCommand("skipadmin");
                    } else if (clientboundSetScorePacket.owner().contains("§a⌚ §7Next admin: §f" + str)) {
                        client.player.connection.sendCommand("skipadmin");
                    }
                } catch (Exception e) {
                    client.player.displayClientMessage(Component.literal("\u00a7cAutoSkipAdmin has an invalid value, or something else broke, first make sure AutoSkipAdmin is set to a whole number, if this message still appears please report this bug."), false);
                    client.player.displayClientMessage(Component.literal("Disabled AutoSkipAdmin to prevent error message spamming."), false);
                    ArchyClient.configOptions.setAutoSkipAdminActive(false);
                }
            }

            ArchyClient.checkScoreboardTimer = 0;

//            client.player.displayClientMessage(Component.literal(clientboundSetScorePacket.owner()), false);
        }
    }
}
