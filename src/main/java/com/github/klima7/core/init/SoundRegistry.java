package com.github.klima7.core.init;

import com.github.klima7.RubiksCubeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {

    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RubiksCubeMod.MODID);

    public static final RegistryObject<SoundEvent> MOVE = registerSound("rubiks_cube.move");
    public static final RegistryObject<SoundEvent> ROTATE = registerSound("rubiks_cube.rotate");
    public static final RegistryObject<SoundEvent> HIT = registerSound("rubiks_cube.hit");
    public static final RegistryObject<SoundEvent> PLACE = registerSound("rubiks_cube.place");

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(RubiksCubeMod.MODID, name)));
    }

}
