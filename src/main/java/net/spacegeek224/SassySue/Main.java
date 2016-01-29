package net.spacegeek224.SassySue;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Enabled");


	}

	@Override
	public void onDisable() {
		getLogger().info("Disabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hello")) { // If the player typed
														// /basic then do the
														// following...

			Player player = (Player) sender;
			sender.sendMessage(ChatColor.GREEN + "Hello.");

			return true;
		} // If this has happened the function will break and return true. if
			// this hasn't happened the a value of false will be returned.
		return false;
	}

	@EventHandler
	public void onPlayerItem(PlayerDropItemEvent event) {
		Item item = (Item) event.getItemDrop();
		item.setCustomName(ChatColor.YELLOW
				+ event.getPlayer().getDisplayName() + "'s Item");
		item.setCustomNameVisible(true);
	}

	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GOLD + "You Sneaky Sally!");

	}

	@EventHandler
	public void onPlayerSprint(PlayerToggleSprintEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GREEN + "You Sprinting Samuel!");
	}

	@EventHandler
	public void onPlayerFly(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.LIGHT_PURPLE + "You Flying Francine!");
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.YELLOW + "You Chatty Chad!");
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block b = event.getBlock();
		Location loc = b.getLocation();
		World w = loc.getWorld();
		Block bb = w.getBlockAt(loc);
		bb.setType(Material.REDSTONE_BLOCK);

		player.sendMessage(ChatColor.RED + "You Broken Billy!");
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.BLUE + "You Interactive Iris!");
		// TODO event.getClickedBlock();
//		Location loc = player.getLocation();
//		World w = loc.getWorld();
//		loc.setY(loc.getY() - 1);
//		Block b = event.getClickedBlock();
//		b.setType(Material.GOLD_BLOCK);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCraftItem(CraftItemEvent event) {
		CraftingInventory inventory = (CraftingInventory) event.getInventory();
		Material material = (Material) inventory.getResult().getType();
		int ammount = 1;
		String name = ChatColor.RED + "A Crafted Item";

		ItemStack item = new ItemStack(material, ammount);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);

		itemMeta.setLore(Arrays.asList(ChatColor.YELLOW + "Hand-Crafted",
				ChatColor.YELLOW + "By A Player."));

		item.setItemMeta(itemMeta);
		inventory.setResult(item);
		//
		// player.sendMessage(ChatColor.DARK_RED + "You Crafty Carl!");
	}
}
