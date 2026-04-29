package appeng.api.networking.security;

public interface IActionSource {
    IActionHost via();
    boolean isPlayer();
    boolean isMachine();
}
