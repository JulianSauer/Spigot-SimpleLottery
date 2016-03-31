package de.gmx.endermansend.main;

import org.bukkit.plugin.java.JavaPlugin;

public class SimpleLottery extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("SimpleLottery enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("SimpleLottery disabled");
    }

}
