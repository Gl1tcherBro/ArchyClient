package gl1tch.archyclient.mixin;

import gl1tch.archyclient.ArchyClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class SystemMessageMixin {
    @Inject(at = @At("TAIL"), method = "handleSystemChat")
    private void kill(ClientboundSystemChatPacket clientboundSystemChatPacket, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        boolean checkedKilled = false;


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

//        client.player.displayClientMessage(Component.literal(clientboundSystemChatPacket.content().toString()), false);
    }
}
