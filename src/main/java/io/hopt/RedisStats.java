package io.hopt;

import io.hopt.command.StatsCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.JedisPool;

/**
 * Created by Owner on 24/06/2017.
 */
public class RedisStats extends JavaPlugin implements Listener {

    public static JedisPool pool;
    public static RedisStats redisStats;

    public void onEnable() {
        redisStats = this;
        pool = new JedisPool("localhost");

        getCommand("stats").setExecutor(new StatsCommand());
    }

    public void onDisable() {
        pool.close();
    }

    public static RedisStats getRedisStats() {
        return redisStats;
    }
}
