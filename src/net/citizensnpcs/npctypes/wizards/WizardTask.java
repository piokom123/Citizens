package net.citizensnpcs.npctypes.wizards;

import java.util.Map.Entry;

import net.citizensnpcs.npcs.NPCManager;
import net.citizensnpcs.properties.SettingsManager;
import net.citizensnpcs.resources.npclib.HumanNPC;

public class WizardTask implements Runnable {

	@Override
	public void run() {
		for (Entry<Integer, HumanNPC> entry : NPCManager.getList().entrySet()) {
			if (!entry.getValue().isType("wizard")) {
				return;
			}
			HumanNPC npc = entry.getValue();
			Wizard wizard = npc.getType("wizard");
			if (SettingsManager.getBoolean("RegenWizardMana")
					&& !wizard.hasUnlimitedMana()) {
				WizardManager.increaseMana(npc, 1);
			}
		}
	}
}