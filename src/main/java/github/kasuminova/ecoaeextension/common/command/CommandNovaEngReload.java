package github.kasuminova.ecoaeextension.common.command;

import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.util.MachineCoolants;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.FMLCommonHandler;

import java.util.List;

/**
 * Hot reload command: /novaeng reload
 * Reloads coolant definitions without server restart.
 * Note: Machine structure definitions are now handled via StructureLib - no reload needed.
 */
public class CommandNovaEngReload extends CommandBase {

    @Override
    public String getCommandName() {
        return "novaeng";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/novaeng reload - Reload coolant definitions";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender.canCommandSenderUseCommand(4, getCommandName());
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "reload") : null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.addChatMessage(new ChatComponentText("§eUsage: " + getCommandUsage(sender)));
            return;
        }

        if ("reload".equals(args[0])) {
            reloadCoolants(sender);
        } else {
            sender.addChatMessage(new ChatComponentText("§cUnknown subcommand: " + args[0]));
        }
    }

    private void reloadCoolants(ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText("§a[NovaEng] Reloading coolant definitions..."));

        try {
            MachineCoolants.INSTANCE.clear();
            MachineCoolants.INSTANCE.init();
            sender.addChatMessage(new ChatComponentText("§a[NovaEng] Coolants reloaded"));
        } catch (Exception e) {
            sender.addChatMessage(new ChatComponentText("§c[NovaEng] Coolant reload failed: " + e.getMessage()));
            ECOAEExtension.log.error("Coolant reload failed", e);
        }

        if (sender instanceof EntityPlayerMP) {
            ECOAEExtension.log.info("Hot reload triggered by " + ((EntityPlayerMP) sender).getCommandSenderName());
        }

        sender.addChatMessage(new ChatComponentText("§a[NovaEng] Hot reload complete!"));
    }
}
