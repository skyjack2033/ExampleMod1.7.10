package zone.rong.mixinbooter;

import java.util.List;

/**
 * Stub for 1.7.10 port
 */
public interface ILateMixinLoader {
    List<String> getMixinConfigs();
    boolean shouldMixinConfigQueue(String mixinConfig);
}
