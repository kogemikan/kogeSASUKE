package sasuke3rd;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener
{
	boolean start = false;

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
	public void move(PlayerMoveEvent e)
	{
		Player event = e.getPlayer();
		Location loc = event.getLocation();
		if(start)
		{
			if(144<=loc.getX()&&loc.getX()<=145&&47<=loc.getY()&&loc.getY()<=49&&121<=loc.getZ()&&loc.getZ()<=133)
			{
				int foodlv = event.getFoodLevel();
				event.setFoodLevel(foodlv-1);
				if(foodlv <= 0)
				{
					event.setFoodLevel(0);
				}
				if(event.getFoodLevel()==0)
				{
					event.teleport(loc.add(0,-2,0));
				}
			}
			else
			{
				event.setFoodLevel(20);
			}

		}
	}





	@EventHandler
	public void action(PlayerAnimationEvent e)
	{
		Player event = e.getPlayer();
		Location loc = event.getLocation();
		World world = event.getWorld();
		if(start)
		{
			if(144<=loc.getX()&&loc.getX()<=146&&46<=loc.getY()&&loc.getY()<=48)
			{
				if(161<=loc.getZ()&&loc.getZ()<=163)
				{
					Block set = world.getBlockAt(new Location(world,144,45,161));
					set.setType(Material.BARRIER);
					set.getRelative(1,0,0).setType(Material.BARRIER);
					new BukkitRunnable()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void run()
						{
							set.setTypeId(36);
							set.getRelative(1,0,0).setTypeId(36);
						}
					}.runTaskLater(this, 20);
				}
				else if(158<=loc.getZ()&&loc.getZ()<=160)
				{
					Block set1 = world.getBlockAt(new Location(world,144,45,158));
					set1.setType(Material.BARRIER);
					set1.getRelative(1,0,0).setType(Material.BARRIER);
					new BukkitRunnable()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void run()
						{
							set1.setTypeId(36);
							set1.getRelative(1,0,0).setTypeId(36);
						}
					}.runTaskLater(this, 20);
				}
				else if(155<=loc.getZ()&&loc.getZ()<=157)
				{
					Block set2 = world.getBlockAt(new Location(world,144,45,155));
					set2.setType(Material.BARRIER);
					set2.getRelative(1,0,0).setType(Material.BARRIER);
					new BukkitRunnable()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void run()
						{
							set2.setTypeId(36);
							set2.getRelative(1,0,0).setTypeId(36);
						}
					}.runTaskLater(this, 20);
				}
				else if(152<=loc.getZ()&&loc.getZ()<=154)
				{
					Block set3 = world.getBlockAt(new Location(world,144,45,152));
					set3.setType(Material.BARRIER);
					set3.getRelative(1,0,0).setType(Material.BARRIER);
					new BukkitRunnable()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void run()
						{
							set3.setTypeId(36);
							set3.getRelative(1,0,0).setTypeId(36);
						}
					}.runTaskLater(this, 20);
				}
			}

			if(144<=loc.getX()&&loc.getX()<=146&&46<=loc.getY()&&loc.getY()<=48&&137<=loc.getZ()&&loc.getZ()<=147)
			{
				Block set4 = world.getBlockAt(new Location(world,139,31,145));
				set4.setType(Material.REDSTONE_BLOCK);
			}

			if(144<=loc.getX()&&loc.getX()<=145&&47<=loc.getY()&&loc.getY()<=49&&121<=loc.getZ()&&loc.getZ()<=133)
			{
				int recover = event.getFoodLevel();
				event.setFoodLevel(recover+4);

				if(recover >= 20)
				{
					event.setFoodLevel(20);
				}
			}
		}
	}





	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("koge3rd"))
		{
			if(args.length>0)
			{
				if(args[0].equalsIgnoreCase("start"))
				{
					if(args.length==1)
					{
						start = true;
						Bukkit.getServer().getWorld("sasuke").setDifficulty(Difficulty.EASY);
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("end"))
				{
					if(args.length==1)
					{
						start = false;
						Bukkit.getServer().getWorld("sasuke").setDifficulty(Difficulty.PEACEFUL);
						return true;
					}
				}
			}
		}
		return false;
	}
}
