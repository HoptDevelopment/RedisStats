package io.hopt.command;

import io.hopt.RedisStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;

/**
 * Created by Owner on 24/06/2017.
 */
public class StatsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("stats")) {
            if (!(commandSender instanceof Player)) {
                return true;
            }
            try (Jedis jedis = RedisStats.pool.getResource()) {
                Player player = (Player) commandSender;
                jedis.set(";redstone", String.valueOf(player.getStatistic(Statistic.MINE_BLOCK, Material.REDSTONE_ORE)));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (Jedis jedis = RedisStats.pool.getResource()) {
                Player player = (Player) commandSender;
                jedis.set(";diamond", String.valueOf(player.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE)));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Player player = (Player) commandSender;
            Jedis jedis = RedisStats.pool.getResource();

            player.sendMessage(ChatColor.AQUA + "Diamonds: " + jedis.get(";diamond"));
            player.sendMessage(ChatColor.RED + "RedStone: " + jedis.get(";redstone"));

        }
        return false;
    }
}
