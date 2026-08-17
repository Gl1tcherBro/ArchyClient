package gl1tch.archyclient;

import gl1tch.archyclient.Util.ModConfigOptions;
import gl1tch.archyclient.Util.ModStuffs;
import net.fabricmc.api.ClientModInitializer;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchyClient implements ClientModInitializer {
	public static final String MOD_ID = "archyclient";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ModConfigOptions configOptions;

	@Override
	public void onInitializeClient() {
		ModStuffs.init();

		LOGGER.info("Thank you for using ArchyClient!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
