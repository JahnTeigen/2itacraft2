/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.ItacraftMod;

public class ItacraftModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, ItacraftMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> THEPRICEISRIGHT = REGISTRY.register("thepriceisright", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("itacraft", "thepriceisright")));
	public static final DeferredHolder<SoundEvent, SoundEvent> KACHING = REGISTRY.register("kaching", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("itacraft", "kaching")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CHINA_TTS_1 = REGISTRY.register("china_tts_1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("itacraft", "china_tts_1")));
	public static final DeferredHolder<SoundEvent, SoundEvent> RUSSIA_TTS_1 = REGISTRY.register("russia_tts_1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("itacraft", "russia_tts_1")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SUKABLYAT = REGISTRY.register("sukablyat", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("itacraft", "sukablyat")));
}