package com.metrognome;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Preferences;
import net.runelite.api.SoundEffectID;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Metrognome"
)
public class MetrognomePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private MetrognomeConfig config;

	private int cyclePosition = 0;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Metrognome started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Metrognome stopped!");
		cyclePosition = 0;
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		if (config.tickCount() <= 0)
		{
			return;
		}

		Preferences preferences = client.getPreferences();
		int previousVolume = preferences.getSoundEffectVolume();

		if (cyclePosition == 0 && config.tickVolume() > 0)
		{
			preferences.setSoundEffectVolume(config.tickVolume());
			client.playSoundEffect(SoundEffectID.GE_INCREMENT_PLOP, config.tickVolume());
		}
		else if (cyclePosition != 0 && config.tockVolume() > 0)
		{
			preferences.setSoundEffectVolume(config.tockVolume());
			client.playSoundEffect(SoundEffectID.GE_DECREMENT_PLOP, config.tockVolume());
		}

		preferences.setSoundEffectVolume(previousVolume);

		cyclePosition = (cyclePosition + 1) % config.tickCount();
	}

	@Provides
	MetrognomeConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MetrognomeConfig.class);
	}
}
