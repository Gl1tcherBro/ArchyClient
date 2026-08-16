package gl1tch.archyclient.Util;

import gl1tch.archyclient.ArchyClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import gl1tch.archyclient.Command.*;

public class ModStuffs {
    public static Integer timer = 0;

    public static void init() {
        ArchyClient.configOptions = ModConfigHandler.readClientConfig();

        registerCommands();
    }

    private static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register(ConfigCommands::register);
    }
}
