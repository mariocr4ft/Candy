package dev.lugami.candy;

import dev.lugami.candy.base.CandyBridge;
import dev.lugami.candy.base.CandyCPSHandler;
import dev.lugami.candy.base.CandyPlayer;
import dev.lugami.candy.craftcandy.Candy;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CandyExample extends JavaPlugin {

    public void onEnable() {
        new Candy(this).setup();
        CandyBridge.registerHandler(new CPSHandler());
    }

    private static class CPSHandler implements CandyCPSHandler {

        private final Map<CandyPlayer, Integer> map = new HashMap<>();

        @Override
        public void onClick(CandyPlayer player) {
            if (!map.containsKey(player)) {
                map.put(player, 0);
            }
            if (player.getCPS() >= 20) {
                if (map.get(player) >= 35) {
                    player.getPlayer().kickPlayer("You're doing more than 20 CPS, please click slower!");
                    map.remove(player);
                } else {
                    int i = map.get(player) + 1;
                    map.put(player, i);
                }
            }
        }
    }

}
