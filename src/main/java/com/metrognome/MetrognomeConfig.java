package com.metrognome;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import net.runelite.api.SoundEffectVolume;

@ConfigGroup("metrognome")
public interface MetrognomeConfig extends Config
{
	int VOLUME_MAX = SoundEffectVolume.HIGH;

	@ConfigItem(
		keyName = "tickCount",
		name = "Tick count",
		description = "Configures the number of tocks between ticks. 1 means all ticks, 2 means alternate, etc."
	)
	default int tickCount()
	{
		return 2;
	}

	@Range(
		max = VOLUME_MAX
	)
	@ConfigItem(
		keyName = "tickVolume",
		name = "Tick volume",
		description = "Configures the volume of the tick sound. A value of 0 will disable tick sounds."
	)
	default int tickVolume()
	{
		return SoundEffectVolume.MEDIUM_HIGH;
	}

	@Range(
		max = VOLUME_MAX
	)
	@ConfigItem(
		keyName = "tockVolume",
		name = "Tock volume",
		description = "Configures the volume of the tock sound. A value of 0 will disable tock sounds."
	)
	default int tockVolume()
	{
		return SoundEffectVolume.MUTED;
	}
}
