package me.tofpu.mobpreventer;

import me.tofpu.mobpreventer.commands.module.CommandManager;
import me.tofpu.mobpreventer.commands.Reload;
import me.tofpu.mobpreventer.listeners.EntitySpawnListener;
import me.tofpu.mobpreventer.module.Config;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobPreventer extends JavaPlugin {
    private Config config;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        this.config = new Config(this);
    
        int pluginId = 8986;
        new Metrics(this, pluginId);
    
        CommandManager manager = new CommandManager(this);
        PluginCommand pluginCommand = getCommand("mobpreventer");
        pluginCommand.setExecutor(manager);
        pluginCommand.setTabCompleter(manager);
        
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
