package net.citizensnpcs.events;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import com.citizens.events.NPCCreatureSpawnEvent;
import com.citizens.events.NPCDisplayTextEvent;
import com.citizens.events.NPCInventoryOpenEvent;
import com.citizens.events.NPCRightClickEvent;
import com.citizens.events.NPCSpawnEvent;
import com.citizens.events.NPCTargetEvent;

public class NPCListener extends CustomEventListener implements Listener {

	public void onNPCCreatureSpawn(NPCCreatureSpawnEvent event) {
	}

	public void onNPCDisplayText(NPCDisplayTextEvent event) {
	}

	public void onNPCInventoryOpen(NPCInventoryOpenEvent event) {
	}

	public void onNPCRightClick(NPCRightClickEvent event) {
	}

	public void onNPCSpawn(NPCSpawnEvent event) {
	}

	public void onNPCTarget(NPCTargetEvent event) {
	}

	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof NPCCreatureSpawnEvent) {
			onNPCCreatureSpawn((NPCCreatureSpawnEvent) event);
		} else if (event instanceof NPCDisplayTextEvent) {
			onNPCDisplayText((NPCDisplayTextEvent) event);
		} else if (event instanceof NPCInventoryOpenEvent) {
			onNPCInventoryOpen((NPCInventoryOpenEvent) event);
		} else if (event instanceof NPCRightClickEvent) {
			onNPCRightClick((NPCRightClickEvent) event);
		} else if (event instanceof NPCSpawnEvent) {
			onNPCSpawn((NPCSpawnEvent) event);
		} else if (event instanceof NPCTargetEvent) {
			onNPCTarget((NPCTargetEvent) event);
		}
	}
}