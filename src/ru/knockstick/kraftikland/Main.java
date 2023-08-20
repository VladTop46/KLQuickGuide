package ru.knockstick.kraftikland;

import org.bukkit.plugin.java.JavaPlugin;
import ru.knockstick.metrics.Metrics;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

class Main extends JavaPlugin implements Listener {
    private Main plugin;
    
    private FileConfiguration config;
    private String prefix;
    Metrics metrics;
    
    protected HashMap<String, Float> positionOne;
    protected HashMap<String, Float> positionTwo;
    protected HashMap<String, Float> positionThree;
    
    protected HashMap<String, Float> positionSpawn;
    
    protected HashMap<String, String> messages;
    
    private Boolean isEnabled;
    
    private void getVars() {
        isEnabled = config.getBoolean("plugin.enabled");
        positionOne.put("x1", 
        		(float)config.getDouble("plugin.x1"));
        positionOne.put("y1", 
        		(float)config.getDouble("plugin.y1"));
        positionOne.put("z1", 
        		(float)config.getDouble("plugin.z1"));
        positionOne.put("yaw1", 
        		(float)config.getDouble("plugin.yaw1"));
        positionOne.put("pitch1", 
        		(float)config.getDouble("plugin.pitch1")); 
        
        positionTwo.put("x2", 
        		(float)config.getDouble("plugin.x2"));
        positionTwo.put("y2", 
        		(float)config.getDouble("plugin.y2"));
        positionTwo.put("z2", 
        		(float)config.getDouble("plugin.z2"));
        positionTwo.put("yaw2", 
        		(float)config.getDouble("plugin.yaw2"));
        positionTwo.put("pitch2", 
        		(float)config.getDouble("plugin.pitch2"));
        
        positionThree.put("x3", 
        		(float)config.getDouble("plugin.x3"));
        positionThree.put("y3", 
        		(float)config.getDouble("plugin.y3"));
        positionThree.put("z3", 
        		(float)config.getDouble("plugin.z3"));
        positionThree.put("yaw3", 
        		(float)config.getDouble("plugin.yaw3"));
        positionThree.put("pitch3", 
        		(float)config.getDouble("plugin.pitch3"));
        
        positionSpawn.put("xSpawn", 
        		(float)config.getDouble("plugin.xSpawn"));
        positionSpawn.put("ySpawn", 
        		(float)config.getDouble("plugin.ySpawn"));
        positionSpawn.put("zSpawn", 
        		(float)config.getDouble("plugin.zSpawn"));
        positionSpawn.put("yawSpawn", 
        		(float)config.getDouble("plugin.yawSpawn"));
        positionSpawn.put("pitchSpawn", 
        		(float)config.getDouble("plugin.pitchSpawn")); 
        
        messages.put("msg1", 
        		config.getString("plugin.msg1"));
        messages.put("msg2", 
        		config.getString("plugin.msg2"));
        messages.put("msg3", 
        		config.getString("plugin.msg3"));
        messages.put("endmsg", 
        		config.getString("plugin.endmsg"));
        
        prefix = config.getString("plugin.prefix");
    }
    
    @SuppressWarnings("unused")
	@Override
	public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        plugin.getLogger().info("KraftikLand QuickGuide started!");
        plugin.getLogger().info("The plugin is made by the FriendWorld Network administration with love <3");
        plugin.getLogger().info("Our Contacts: https://github.com/FriendWorld-Network, Discord: https://discord.friendworld.ru/");
        
        config = plugin.getConfig();
        getVars();
        
        plugin.getServer().getPluginManager().registerEvents(this, this);
        int pluginId = 14702;
        metrics = new Metrics(this, pluginId);
    }
    
    @Override
    public void onDisable() {
    	config = null;
    	metrics = null;
    	plugin = null;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Thread thread = new Thread() {
            public void run() {
                if(p.hasPlayedBefore() == false) {
                    try {
                        p.teleport(new Location(
                        		Bukkit.getWorld("world"), 
                        		positionOne.get("x1"), 
                        		positionOne.get("y1"), 
                        		positionOne.get("z1"), 
                        		positionOne.get("yaw1"), 
                        		positionOne.get("pitch1")));
                        
                        p.playSound(p.getLocation(), 
                        		Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                        p.sendMessage(prefix + " " + messages.get("msg1"));
                        Thread.sleep(4000);
                        p.teleport(new Location(
                        		Bukkit.getWorld("world"), 
                        		positionTwo.get("x2"), 
                        		positionTwo.get("y2"),
                        		positionTwo.get("z2"), 
                        		positionTwo.get("yaw2"), 
                        		positionTwo.get("pitch2")));
                        
                        p.playSound(p.getLocation(), 
                        		Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                        p.sendMessage(prefix + " " + messages.get("msg2"));
                        Thread.sleep(4000);
                        p.teleport(new Location(
                        		Bukkit.getWorld("world"), 
                        		positionThree.get("x3"),
                        		positionThree.get("y3"), 
                        		positionThree.get("z3"), 
                        		positionThree.get("yaw3"), 
                        		positionThree.get("pitch3")));
                        
                        p.playSound(p.getLocation(), 
                        		Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                        p.sendMessage(prefix + " " + messages.get("msg3"));
                        Thread.sleep(4000);
                        p.teleport(new Location(
                        		Bukkit.getWorld("world"), 
                        		positionSpawn.get("xSpawn"), 
                        		positionSpawn.get("ySpawn"), 
                        		positionSpawn.get("zSpawn"), 
                        		positionSpawn.get("yawSpawn"), 
                        		positionSpawn.get("pitchSpawn")));
                        
                        p.playSound(p.getLocation(), 
                        		Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                        p.sendMessage(prefix + " " + messages.get("endmsg"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        if (isEnabled == true) {
        	thread.start();
        }
    }
}