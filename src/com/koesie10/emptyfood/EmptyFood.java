package com.koesie10.emptyfood;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
//import org.bukkit.event.Event;
//import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EmptyFood extends JavaPlugin{
	public static EmptyFood plugin;
	public final ArrayList<Player> playerList = new ArrayList<Player>();
	//FoodFillerEntityListener entityListener = new FoodFillerEntityListener(this);

	@Override
	public void onDisable() {
		System.out.println("EmptyFood successfully disabled!");
		
	}

	@Override
	public void onEnable() {
		//PluginManager pm = getServer().getPluginManager();
        
        //FoodFillerEntityListener entityListener = new FoodFillerEntityListener(this);
        //pm.registerEvent(Event.Type.FOOD_LEVEL_CHANGE, entityListener, Event.Priority.Normal, this);
		System.out.println("EmptyFood successfully enabled!");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		boolean succeed = true;
		
		if (sender instanceof Player) 
		{
		    Player player = (Player) sender;
		    int foodLevel = 0;
		    
		    
		    if (cmd.getName().equalsIgnoreCase("efeed"))
			{
		    	if (args.length == 2) 
				{
		    		if(player.hasPermission("emptyfood.other")) {
			    		Player other = Bukkit.getPlayer(args[1]);
			            if (other == null) {
			               player.sendMessage(ChatColor.RED + "[EmptyFood] " + args[1] + " is not online!");
			            }
			            else
			            {
				            try
			            	{
			            		foodLevel = Integer.parseInt( args[0] );
			            		if (foodLevel > 20 || foodLevel < 0)
			            		{
			            			player.sendMessage(ChatColor.RED + "[EmptyFood] <amount> must be an integer between 0 and 20!");
			            		}
			            		else
			            		{
				            		other.setFoodLevel(foodLevel);
				            		player.sendMessage(ChatColor.AQUA + "[EmptyFood] The Food Level of " + args[1] + " is now " + args[0]);
			            		}
			            	}
			            	catch(NumberFormatException e)
			            	{
			            		sender.sendMessage(ChatColor.RED + "[EmptyFood]" + args[0] + " is not an integer!");
			            	}
			            	finally {}
			            }
		    		}
		    		else
		    		{
		    			player.sendMessage(ChatColor.RED + "[EmptyFood] You don't have permissions to do this!");
		    		}
		    	}
		        else if (args.length == 1)
		        {
		        	if(player.hasPermission("emptyfood.self")) {
			        	try
		            	{
		            		foodLevel = Integer.parseInt( args[0] );
		            		if (foodLevel > 20 || foodLevel < 0)
		            		{
		            			player.sendMessage(ChatColor.RED + "[EmptyFood] <amount> must be an integer between 0 and 20!");
		            		}
		            		else
		            		{
			            		player.setFoodLevel(foodLevel);
			            		player.sendMessage(ChatColor.AQUA + "[EmptyFood] Your food level is now " + args[0]);
		            		}
		            	}
		            	catch(NumberFormatException e)
		            	{
		            		sender.sendMessage(ChatColor.RED + "[EmptyFood] " + args[0] + " is not an integer!");
		            	}
		            	finally {}
		        	}
		        	else
		    		{
		    			player.sendMessage(ChatColor.RED + "[EmptyFood] You don't have permissions to do this!");
		    		}
		        }
		    	else
		    	{
		    		if(player.hasPermission("emptyfood.self")) {
			    		player.setFoodLevel(0);
			    		player.sendMessage(ChatColor.AQUA + "[EmptyFood] Your Food Level is now empty!");
		    		}
		    		else
		    		{
		    			player.sendMessage(ChatColor.RED + "[EmptyFood] You don't have permissions to do this!");
		    		}
		    	}
		    		
		        succeed = true;
			}
		    else if (cmd.getName().equalsIgnoreCase("eat"))
			{
		    	if (args.length == 1) 
				{
		    		if(player.hasPermission("emptyfood.eat.other")) 
		    		{
			    		Player other = Bukkit.getPlayer(args[0]);
			            if (other == null) {
			               player.sendMessage(ChatColor.RED + "[EmptyFood] " + args[0] + " is not online!");
			            }
			            else
			            {
				            other.setFoodLevel(8);
				            PlayerInventory inventory = other.getInventory(); // The player's inventory
				    	    ItemStack cake = new ItemStack(Material.CAKE, 1); // A stack of diamonds
				    		inventory.addItem(cake);
				            player.sendMessage(ChatColor.AQUA + "[EmptyFood] The Food Level of " + args[0] + " is now 8 and " + args[0] + " got a cake!");
				            other.sendMessage(ChatColor.AQUA + "[EmptyFood] Your Food Level is now 8 and you got a cake! EAT IT!");
			            }
			        }
		    	}
		    	else
		    	{
			    	if(player.hasPermission("emptyfood.eat.self")) {
			    		player.setFoodLevel(8);
			    		PlayerInventory inventory = player.getInventory(); // The player's inventory
			    	    ItemStack cake = new ItemStack(Material.CAKE, 1); // A stack of diamonds
			    		inventory.addItem(cake);
			    		player.sendMessage(ChatColor.AQUA + "[EmptyFood] Your Food Level is now 8 and you got a cake!");
		    		}
		    		else
		    		{
		    			player.sendMessage(ChatColor.RED + "[EmptyFood] You don't have permissions to do this!");
		    		}
		    	}
			}
		}
		return succeed;
}
}
