package sasukefinal;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener
{
	boolean start = false;
	boolean rl = false;

	@Override
	public void onEnable()
	{
		super.onEnable();
		getServer().getPluginManager().registerEvents(this,this);
		getLogger().info("プラグインが読み込まれました");
	}
	@Override
	public void onDisable()
	{
		getLogger().info("プラグインが終了しました");
	}




	@EventHandler
	public void action(PlayerAnimationEvent e)
	{
		Player event = e.getPlayer();
		Location loc = event.getLocation();
		World world = event.getWorld();
		if(start)
		{
			if(rl)
			{
				if(160<=loc.getX()&&loc.getX()<=161&&44<=loc.getY()&&loc.getY()<=65&&42<=loc.getZ()&&loc.getZ()<=45)
				{
					Block set = world.getBlockAt(loc.add(0, -1, 0));
					set.setType(Material.BARRIER);
					new BukkitRunnable()
					{
						@Override
						public void run()
						{
							set.setType(Material.AIR);
						}
					}.runTaskLater(this, 20);
					rl=false;
				}
			}
			if(!rl)
			{
				if(162<=loc.getX()&&loc.getX()<=163&&44<=loc.getY()&&loc.getY()<=65&&42<=loc.getZ()&&loc.getZ()<=45)
				{
					Block set = world.getBlockAt(loc.add(0, -1, 0));
					set.setType(Material.BARRIER);
					new BukkitRunnable()
					{
						@Override
						public void run()
						{
							set.setType(Material.AIR);
						}
					}.runTaskLater(this, 20);
					rl=true;
				}
			}
			if(161<=loc.getX()&&loc.getX()<=162&&67<=loc.getY()&&loc.getY()<=81&&43<=loc.getZ()&&loc.getZ()<=44)
			{
				Block set = world.getBlockAt(loc.add(0,-1,0));
				set.setType(Material.BARRIER);
				new BukkitRunnable()
				{
					@Override
					public void run()
					{
						set.setType(Material.AIR);
					}
				}.runTaskLater(this, 10);
			}
		}
	}




	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("kogefinal"))
		{
			if(args.length>0)
			{
				if(args[0].equalsIgnoreCase("start"))
				{
					if(args.length==1)
					{
						start=true;
						rl=false;
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("end"))
				{
					if(args.length==1)
					{
						start=false;
						rl=false;
						return true;
					}
				}
			}
		}
		return false;
	}
}
