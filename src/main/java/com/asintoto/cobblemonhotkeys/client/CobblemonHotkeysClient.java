package com.asintoto.cobblemonhotkeys.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CobblemonHotkeysClient implements ClientModInitializer {
    private final String PC_COMMAND = "pc";
    private final String HEAL_COMMAND = "pokeheal";


    @Override
    public void onInitializeClient() {
        KeyBinding pcKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.cobblemonhotkeys.pc",
                InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P,
                "key-category.cobblemonhotkeys.keys"));
        KeyBinding healKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.cobblemonhotkeys.heal",
                InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H,
                "key-category.cobblemonhotkeys.keys"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (pcKey.wasPressed()) {
                client.player.networkHandler.sendChatCommand(PC_COMMAND);
            }

            while (healKey.wasPressed()) {
                client.player.networkHandler.sendChatCommand(HEAL_COMMAND);
            }
        });
    }
}
