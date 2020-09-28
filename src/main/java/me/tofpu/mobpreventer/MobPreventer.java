package me.tofpu.mobpreventer;

import me.tofpu.mobpreventer.commands.CommandManager;
import me.tofpu.mobpreventer.commands.Reload;
import me.tofpu.mobpreventer.listeners.EntitySpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobPreventer extends JavaPlugin {
    private Config config;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.config = new Config(this);
        
//        ReloadCommand reloadCommand = new ReloadCommand(this);
//        PluginCommand reload = getCommand("mobpreventer");
//        reload.setExecutor(reloadCommand);
//        reload.setTabCompleter(reloadCommand);
        
        getCommand("mobpreventer").setExecutor(new CommandManager(this));
        new Reload(this).register();
        
        Bukkit.getPluginManager().registerEvents(new EntitySpawnListener(this), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public Config getStaticConfig() {
        return config;
    }
}