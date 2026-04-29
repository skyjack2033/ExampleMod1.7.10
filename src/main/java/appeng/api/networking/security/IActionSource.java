package appeng.api.networking.security;

import net.minecraft.entity.player.EntityPlayer;
import java.util.Optional;

public interface IActionSource {
    IActionHost via();
    boolean isPlayer();
    boolean isMachine();
    default Optional<EntityPlayer> player() { return Optional.empty(); }
    default <T> Optional<T> context(Class<T> key) { return Optional.empty(); }
}
