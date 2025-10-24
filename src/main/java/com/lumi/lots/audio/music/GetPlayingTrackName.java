package com.lumi.lots.audio.music;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;

import java.util.Dictionary;
import java.util.Hashtable;

public class GetPlayingTrackName {
    private static final Dictionary<String, String> trackNames = new Hashtable<String, String>() {{
        put("Nuance1", "Key");
        put("Nuance2", "Oxygène");
        put("Calm1", "Minecraft");
        put("Calm2", "Clark");
        put("Calm3", "Sweden");
        put("Piano1", "Dry Hands");
        put("Piano2", "Wet Hands");
        put("Piano3", "Mice on Venus");
        put("Hal1", "Subwoofer Lullaby");
        put("Hal2", "Living Mice");
        put("Hal3", "Haggstrom");
        put("Hal4", "Danny");
        put("End", "The End");
        put("Boss", "Boss");
        put("Credits", "Alpha");
        put("Nether1", "Concrete Halls");
        put("Nether2", "Dead Voxel");
        put("Nether3", "Warmth");
        put("Nether4", "Ballad of the Cats");
        put("Creative1", "Biome Fest");
        put("Creative2", "Blind Spots");
        put("Creative3", "Haunt Muskie");
        put("Creative4", "Aria Math");
        put("Creative5", "Dreiton");
        put("Creative6", "Taswell");
        put("Menu1", "Mutation");
        put("Menu2", "Moog City 2");
        put("Menu3", "Beginning 2");
        put("Menu4", "Floating Trees");
    }};


    @SubscribeEvent
    public void onSoundPlay(PlaySoundEvent17 event) {
        if (Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null) {
            ISound sound = event.sound;

            if (sound.getAttenuationType() == ISound.AttenuationType.NONE && sound.getPositionedSoundLocation().getResourceDomain().equals("minecraft") && sound.getPositionedSoundLocation().getResourcePath().startsWith("music.")) {
                SoundEventAccessorComposite soundAccesor = event.manager.sndHandler.getSound(sound.getPositionedSoundLocation());

                if (soundAccesor != null) {
                    SoundPoolEntry entry = soundAccesor.func_148720_g();
                    if (entry != null) {
                        ResourceLocation actualSound = entry.getSoundPoolEntryLocation();
                        String trackName = actualSound.getResourcePath();

                        String displayName = trackName.replace("sounds/music/", "").replace(".ogg", "");
                        String[] nameParts =  displayName.split("/");
                        nameParts = nameParts[nameParts.length - 1].split("_");
                        StringBuilder builder = new StringBuilder();

                        for (String part : nameParts) {
                            if (builder.length() > 0) {
                                builder.append(" ");
                            }
                            builder.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
                        }

                        Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(trackNames.get(builder.toString()));
                    }
                }
            }
        }
    }
}
