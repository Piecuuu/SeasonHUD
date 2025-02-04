package club.iananderson.seasonhud.event;

import club.iananderson.seasonhud.client.KeyBindings;
import club.iananderson.seasonhud.client.gui.screens.SeasonHUDScreen;
import club.iananderson.seasonhud.client.overlays.JourneyMap;
import club.iananderson.seasonhud.client.overlays.MapAtlases;
import club.iananderson.seasonhud.client.overlays.SeasonHUDOverlay;
import club.iananderson.seasonhud.impl.minimaps.CurrentMinimap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class ClientEvents {
  private ClientEvents() {
  }

  //Key Bindings
  private static void registerKeyInputs() {
    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      if (KeyBindings.seasonhudOptionsKeyMapping.consumeClick()) {
        SeasonHUDScreen.open();
      }
    });
  }

  private static void registerKeyMappings() {
    KeyBindings.seasonhudOptionsKeyMapping = KeyBindingHelper.registerKeyBinding(
        KeyBindings.seasonhudOptionsKeyMapping);
  }

  private static void registerHud() {
    SeasonHUDOverlay.init();
    if (CurrentMinimap.mapAtlasesLoaded()) {
      MapAtlases.init();
    }
    if (CurrentMinimap.journeyMapLoaded()) {
      JourneyMap.init();
    }
  }

  public static void register() {
    registerKeyMappings();
    registerKeyInputs();
    registerHud();
  }
}

