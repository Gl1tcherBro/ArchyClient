package gl1tch.archyclient.mixin;


import gl1tch.archyclient.ArchyClient;
import gl1tch.archyclient.Util.ModStuffs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LocalPlayer.class)
public class PlayerTickMixin {
    @Inject(at = @At("RETURN"), method = "tick")
    private static void playerTick(CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();


        if (client.player != null) {
            if (client.player.getDisplayName().toString().contains("literal{§4[§lADMIN§4] §f}")) {
                if (ArchyClient.configOptions.getAutoTortureActive()) {
                    if (!ArchyClient.configOptions.getAutoTorture().isEmpty()) {
                        ModStuffs.timer++;
                    } else {
                        ModStuffs.timer = 0;
                    }

                    //client.player.displayClientMessage(Component.literal(ModStuffs.timer.toString() + " " + String.valueOf(ArchyClient.configOptions.getAutoTorture().size())), true);

                    if (ModStuffs.timer >= 200) {
                        if (!ArchyClient.configOptions.getAutoTorture().isEmpty()) {
                            int sel = Math.toIntExact((ModStuffs.timer - 200));

                            if (sel < ArchyClient.configOptions.getAutoTorture().size()) {
                                client.player.connection.sendCommand("tp " + ArchyClient.configOptions.getAutoTorture().get(sel) + " 0 -10000 0");
                            }

                            if (sel >= ArchyClient.configOptions.getAutoTorture().size() - 1) {
                                ModStuffs.timer = 0;
                            }
                        }
                    }
                }

                if (ArchyClient.configOptions.getAutoCommandActive() && ArchyClient.hasRunCommand) {
                    if (ArchyClient.configOptions.getAutoCommand().startsWith("/")) {
                        client.player.connection.sendCommand(ArchyClient.configOptions.getAutoCommand().substring(1));

                        ArchyClient.hasRunCommand = true;
                    }
                }
            } else {
                ArchyClient.hasRunCommand = false;
            }

            if (ArchyClient.checkScoreboardTimer >= 100) {
                ArchyClient.checkScoreboardTimer = 0;

                client.player.connection.sendCommand("scoreboard");
            }

            ArchyClient.checkScoreboardTimer++;
        }
    }
}
