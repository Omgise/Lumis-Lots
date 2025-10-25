package com.lumi.lots.blocks;

import com.lumi.lots.blocks.BlockDropsHandler.*;
import com.lumi.lots.blocks.BlockToolHandler.*;

public class BlockBuilder {
    private int material = 0;
    private String name = "default_value";
    private float hardness = 1.0F;
    private float resistance = 1.0F;
    private String harvestTool = "pickaxe";
    private int harvestLevel = 0;
    private int sound = 0;
    private int tab = -1;
    private boolean ticks = false;
    private BlockTickHandler tickHandler = null;
    private DropAmountFortuneHandler dropAmountFortuneHandler = null;
    private DropAmountHandler dropAmountHandler = null;
    private DropTypeHandler dropTypeHandler = null;
    private DropMultiItemsHandler dropMultipleItemsHandler = null;
    private ToolEffectiveHandler checkEffectiveToolHandler = null;
    private PlayerRelativeBlockHardnessHandler playerRelativeBlockHardnessHandler = null;
    private boolean useToolEffectiveHandler = false;

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

    public BlockBuilder setHarvestTool(String harvestTool) {
        this.harvestTool = harvestTool;
        return this;
    }

    public BlockBuilder setHarvestLevel(int harvestLevel) {
        this.resistance = harvestLevel;
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

    public BlockBuilder setTicking(boolean ticks) {
        this.ticks = ticks;
        return this;
    }

    public BlockBuilder setTickHandler(BlockTickHandler tickHandler) {
        this.tickHandler = tickHandler;
        return this;
    }

    public BlockBuilder setFortuneAmountHandler(DropAmountFortuneHandler dropAmountFortuneHandler) {
        this.dropAmountFortuneHandler = dropAmountFortuneHandler;
        return this;
    }

    public BlockBuilder setDropAmountHandler(DropAmountHandler dropAmountHandler) {
        this.dropAmountHandler = dropAmountHandler;
        return this;
    }

    public BlockBuilder setDropTypeHandler(DropTypeHandler dropTypeHandler) {
        this.dropTypeHandler = dropTypeHandler;
        return this;
    }

    public BlockBuilder setDropMultipleItemsHandler(DropMultiItemsHandler dropMultipleItemsHandler) {
        this.dropMultipleItemsHandler = dropMultipleItemsHandler;
        return this;
    }

    public BlockBuilder setPlayerRelativeBlockHardnessHandler(PlayerRelativeBlockHardnessHandler playerRelativeBlockHardnessHandler) {
        this.playerRelativeBlockHardnessHandler = playerRelativeBlockHardnessHandler;
        return this;
    }

    public BlockBuilder setCheckEffectiveToolHandler(ToolEffectiveHandler checkEffectiveToolHandler) {
        this.checkEffectiveToolHandler = checkEffectiveToolHandler;
        return this;
    }

    public BlockBuilder useToolEffectiveHandler(boolean useToolEffectiveHandler) {
        this.useToolEffectiveHandler = useToolEffectiveHandler;
        return this;
    }

    public LumisBlocks build() {
        return new LumisBlocks(material, name, hardness, resistance, harvestTool, harvestLevel, sound, tab, ticks, tickHandler, dropAmountFortuneHandler, dropAmountHandler, dropTypeHandler, dropMultipleItemsHandler, checkEffectiveToolHandler, playerRelativeBlockHardnessHandler, useToolEffectiveHandler);
    }
}
