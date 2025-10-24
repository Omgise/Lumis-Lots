package com.lumi.lots.audio.music.overwrite;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;

public class Music extends MusicTicker {
    private final Minecraft mc;
    private ISound currentMusic;

    public Music(Minecraft mc) {
        super(mc);
        this.mc = mc;
    }

    @Override
    public void update()
    {
        MusicTicker.MusicType musicType = mc.func_147109_W();

        if (currentMusic != null)
        {
            if (!musicType.getMusicTickerLocation().equals(currentMusic.getPositionedSoundLocation()))
            {
                mc.getSoundHandler().stopSound(currentMusic);
            }

            if (!mc.getSoundHandler().isSoundPlaying(currentMusic))
            {
                currentMusic = null;
            }
        }

        if (currentMusic == null)
        {
            currentMusic = PositionedSoundRecord.func_147673_a(musicType.getMusicTickerLocation());
            mc.getSoundHandler().playSound(currentMusic);
        }
    }
}
