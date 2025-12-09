package com.lumi.lots.items;

import com.lumi.lots.items.ItemMetaDataHandler.*;
import com.lumi.lots.items.ItemUseHandler.OnItemUseHandler;

public class ItemBuilder {
    private String name = "default_value";
    private int creativeTab = -1;
    private boolean hasSubtypes = false;
    private ItemUseHandler.OnItemUseHandler itemUseHandler = null;
    private RegisterIconsHandler registerIconsHandler = null;
    private IconFromMetadataHandler iconFromMetadataHandler = null;
    private CombinedMetadataHandler combinedMetadataHandler = null;

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setCreativeTab(int creativeTab) {
        this.creativeTab = creativeTab;
        return this;
    }

    public ItemBuilder setItemUseHandler(OnItemUseHandler itemUseHandler) {
        this.itemUseHandler = itemUseHandler;
        return this;
    }

    public ItemBuilder setHasSubtypes(boolean hasSubtypes) {
        this.hasSubtypes = hasSubtypes;
        return this;
    }

    public ItemBuilder setRegisterIconsHandler(RegisterIconsHandler registerIconsHandler) {
        this.registerIconsHandler = registerIconsHandler;
        return this;
    }

    public ItemBuilder setIconFromMetadataHandler(IconFromMetadataHandler iconFromMetadataHandler) {
        this.iconFromMetadataHandler = iconFromMetadataHandler;
        return this;
    }

    public ItemBuilder setCombinedMetadataHandler(CombinedMetadataHandler combinedMetadataHandler) {
        this.combinedMetadataHandler = combinedMetadataHandler;
        return this;
    }

    public LumisItems build() {
        return new LumisItems(name, creativeTab, hasSubtypes, itemUseHandler,registerIconsHandler, iconFromMetadataHandler, combinedMetadataHandler);
    }
}
