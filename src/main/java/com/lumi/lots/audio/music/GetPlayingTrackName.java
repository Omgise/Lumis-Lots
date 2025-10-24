package com.lumi.lots.audio.music;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;

public class GetPlayingTrackName {
    @SubscribeEvent
    public void onSoundPlay(PlaySoundEvent17 event) {
        ISound sound = event.sound;

        if (sound.getAttenuationType() == ISound.AttenuationType.NONE && sound.getPositionedSoundLocation().getResourceDomain().equals("minecraft") && sound.getPositionedSoundLocation().getResourcePath().startsWith("music.")) {
            SoundEventAccessorComposite soundAccesor = event.manager.sndHandler.getSound(sound.getPositionedSoundLocation());

            if (soundAccesor != null) {
                SoundPoolEntry entry = soundAccesor.func_148720_g();
                if (entry != null) {
                    ResourceLocation actualSound = entry.getSoundPoolEntryLocation();
                    String trackName = actualSound.getResourcePath();

                    String displayName = trackName.replace("sounds/music/", "").replace(".ogg", "");
                    String[] nameParts =  displayName.split("/")[1].split("_");
                    StringBuilder builder = new StringBuilder();

                    for (String part : nameParts) {
                        if (builder.length() > 0) {
                            builder.append(" ");
                        }
                        builder.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
                    }

                    Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(builder.toString());
                }
            }
        }
    }
}
