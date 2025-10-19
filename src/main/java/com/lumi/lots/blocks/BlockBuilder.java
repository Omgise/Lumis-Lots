package com.lumi.lots.blocks;

public class BlockBuilder {
    private int material = 0;
    private String name = "Default Value";
    private float hardness = 1.0F;
    private float resistance = 1.0F;
    private int sound = 0;
    private int tab = -1;

    public BlockBuilder setMaterial(int material) {
        this.material = material;
        return this;
    }

    public BlockBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BlockBuilder setHardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public BlockBuilder setResistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    public BlockBuilder setSound(int sound) {
        this.sound = sound;
        return this;
    }

    public BlockBuilder setTab(int tab) {
        this.tab = tab;
        return this;
    }

    public LumisBlocks build() {
        return new LumisBlocks(material, name, hardness, resistance, sound, tab);
    }
}
