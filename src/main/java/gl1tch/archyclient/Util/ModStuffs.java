package gl1tch.archyclient.Util;

import gl1tch.archyclient.ArchyClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import gl1tch.archyclient.Command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModStuffs {
    public static Integer timer = 0;

    public static void init() {
        ArchyClient.configOptions = ModConfigHandler.readClientConfig();

        registerCommands();
    }

    private static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register(ConfigCommands::register);
    }

    public static String getListAsString(List<String> val) {
        List<String> list = new ArrayList<>(val);
        String returnVal = "";

        if (!list.isEmpty()) {
            returnVal += list.get(0);

            list.remove(0);

            if (!list.isEmpty()) {
                for (String str : list) {
                    returnVal += "," + str;
                }
            }
        }

        return returnVal;
    }

    public static void setAutoTPAACCEPTString(String val) {
        List<String> list = new ArrayList<>();


        for (String str : val.split(",")) {
            list.add(str);
        }

        ArchyClient.configOptions.setAutoTPAACCEPT(list);
    }

    public static void setAutoTortureString(String val) {
        List<String> list = new ArrayList<>();


        for (String str : val.split(",")) {
            list.add(str);
        }

        ArchyClient.configOptions.setAutoTorture(list);
    }
}
