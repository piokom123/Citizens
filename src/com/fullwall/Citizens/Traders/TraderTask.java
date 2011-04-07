package com.fullwall.Citizens.Traders;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.fullwall.Citizens.Citizens;
import com.fullwall.resources.redecouverte.NPClib.HumanNPC;

public class TraderTask implements Runnable {
	private HumanNPC npc;
	private CraftPlayer player;
	private int taskID;
	private Citizens plugin;
	private PlayerInventory previousNPCInv;
	private PlayerInventory previousPlayerInv;

	public TraderTask(HumanNPC NPC, Player player, Citizens plugin) {
		this.npc = NPC;
		this.player = (CraftPlayer) player;
		this.plugin = plugin;
		this.previousNPCInv = npc.getBukkitEntity().getInventory();
		this.previousPlayerInv = player.getInventory();
	}

	public void addID(int ID) {
		this.taskID = ID;
	}

	public void kill() {
		plugin.getServer().getScheduler().cancelTask(taskID);
		int index = TraderInterface.tasks.indexOf(taskID);
		if (index != -1)
			TraderInterface.tasks.remove(TraderInterface.tasks.indexOf(taskID));
	}

	@Override
	public void run() {
		if (player == null
				|| player.getHandle().activeContainer == player.getHandle().defaultContainer) {
			npc.setFree(true);
			kill();
			return;
		}
		if (player.getHandle().inventory.j() == null)
			return;
		int count = 0;
		boolean found = false;
		for (ItemStack i : npc.getBukkitEntity().getInventory().getContents()) {
			if (!(previousNPCInv.getItem(count).equals(i))) {
				found = true;
				handleNPCItemClicked(i, count);
				break;
			}
			count += 1;
		}
		count = 0;
		if (!found) {
			for (ItemStack i : player.getInventory().getContents()) {
				if (!(previousPlayerInv.getItem(count).equals(i))) {
					handlePlayerItemClicked(i, count);
					break;
				}
				count += 1;
			}
		}
		previousNPCInv = npc.getBukkitEntity().getInventory();
		previousPlayerInv = player.getInventory();
		player.getHandle().inventory.b((net.minecraft.server.ItemStack) null);
	}

	private void handleNPCItemClicked(ItemStack i, int slot) {
		npc.getBukkitEntity().getInventory()
				.setItem(slot, previousNPCInv.getItem(slot));
		if (!npc.getTraderNPC().isBuyable(i.getTypeId())) {
			return;
		}
		ItemStack toBuy = npc.getTraderNPC().getBuyable(i.getTypeId());
		int amount = npc.getBukkitEntity().getInventory().getItem(slot)
				.getAmount();
		if (amount - toBuy.getAmount() <= 0) {
			return;
		}
	}

	private void handlePlayerItemClicked(ItemStack i, int slot) {
		player.getInventory().setItem(slot, previousPlayerInv.getItem(slot));
		if (!npc.getTraderNPC().isSellable(i.getTypeId())) {
			return;
		}
		ItemStack toSell = npc.getTraderNPC().getSellable(i.getTypeId());
		int amount = npc.getBukkitEntity().getInventory().getItem(slot)
				.getAmount();
		if (amount - toSell.getAmount() <= 0) {
			return;
		}
	}
}
