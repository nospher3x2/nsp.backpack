package me.nospher.backpack.command;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.item.Backpack;
import me.nospher.backpack.utils.Helper;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 */
public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("backpack")) {
            if (!sender.hasPermission("nsp.backpack.commands.give")) {
                String insufficient_permissions = NospherBackpack.getInstance().getConfig().getString(
                        "settings." +
                                "messages." +
                                "insufficient_permissions");

                sender.sendMessage(Helper.colorize(insufficient_permissions));
                return true;
            }

            if (args.length != 4) {
                String insufficient_arguments = NospherBackpack.getInstance().getConfig().getString(
                        "settings." +
                                "messages." +
                                "insufficient_arguments");

                sender.sendMessage(Helper.colorize(insufficient_arguments));
                return true;
            }

            String action = args[0];
            String preMaterial = args[1];
            String target = args[2];
            String preRows = args[3];

            if (action.equalsIgnoreCase("give")) {
                Material material = Material.getMaterial(preMaterial);
                Player player = Bukkit.getPlayerExact(target);

                if (material == null || material == Material.AIR) {
                    String invalid_material = NospherBackpack.getInstance().getConfig().getString(
                            "settings." +
                                    "messages." +
                                    "invalid_material");

                    sender.sendMessage(Helper.colorize(invalid_material));
                    return true;
                }

                if (player == null || !player.isOnline()) {
                    String invalid_player = NospherBackpack.getInstance().getConfig().getString(
                            "settings." +
                                    "messages." +
                                    "invalid_player");

                    sender.sendMessage(Helper.colorize(invalid_player));
                    return true;
                }

                if (!Helper.isInteger(preRows)) {
                    String invalid_rows = NospherBackpack.getInstance().getConfig().getString(
                            "settings." +
                                    "messages." +
                                    "invalid_rows");

                    sender.sendMessage(Helper.colorize(invalid_rows));
                    return true;
                }

                int rows = Integer.parseInt(preRows);

                if (rows < 1 || rows > 6) {
                    String invalid_rows = NospherBackpack.getInstance().getConfig().getString(
                            "settings." +
                                    "messages." +
                                    "invalid_rows");

                    sender.sendMessage(Helper.colorize(invalid_rows));
                    return true;
                }

                Backpack backpack = new Backpack(material);
                backpack.setRows(rows);
                ItemStack item = backpack.getItem();

                for (int i = 0; i < 1; i++) player.getInventory().addItem(item);

                String gived_backpack = StringUtils.replaceEach(NospherBackpack.getInstance().getConfig().getString(
                        "settings." +
                                "messages." +
                                "gived_backpack"),
                        new String[]{
                                "{player}",
                                "{rows}"
                        },
                        new String[]{
                                player.getName(),
                                Integer.toString(rows)
                        }
                );

                sender.sendMessage(Helper.colorize(gived_backpack));
                return true;
            } else {
                String unknown_action = NospherBackpack.getInstance().getConfig().getString(
                        "settings." +
                                "messages." +
                                "unknown_action");

                sender.sendMessage(Helper.colorize(unknown_action));
                return true;
            }
        }
        return false;
    }
}
