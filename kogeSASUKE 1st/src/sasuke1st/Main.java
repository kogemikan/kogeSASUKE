package sasuke1st;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener
{
	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective click;
	private Score score;
	private HashMap<Player,Player> jumpready = new HashMap<>();
	private HashMap<Player,Player> wallready = new HashMap<>();
	private HashMap<Player,Player> vineready = new HashMap<>();
	private HashMap<Player,Player> highjump = new HashMap<>();
	private boolean cancel=false;
	private boolean start=false;


	@Override
	public void onEnable()
	{
		super.onEnable();
		getServer().getPluginManager().registerEvents(this,this);
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		click = board.getObjective("click");
		if (click == null)
		{
			click = board.registerNewObjective("click", "dummy");
		}
		getLogger().info("プラグインが読み込まれました");

	}

	@Override
	public void onDisable()
	{
		getLogger().info("プラグインが終了しました");
	}
	@EventHandler
	public void Join(PlayerJoinEvent e)
	{
		e.getPlayer().setScoreboard(board);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void Move(PlayerMoveEvent e)
	{
		Player player = e.getPlayer();
		World world = player.getWorld();
		Block block = e.getTo().getBlock().getRelative(BlockFace.DOWN);
		Location loc = player.getEyeLocation();
		Block block2 = world.getBlockAt(new Location(world,loc.getX(),loc.getY(),loc.getZ()+1));
		Byte damage = block.getData();
		Byte damage2 = block2.getData();
		Byte data = 1;
		Byte data2 = 3;
		if(jumpready.containsValue(player))
		{

			if(block.getType().equals(Material.SPONGE))
			{
				player.setVelocity(player.getLocation().getDirection().multiply(0));
				player.setVelocity(new Vector(player.getVelocity().getX(),1.0d,0.5d));
				jumpready.remove(player);
			}
			else if(block.getType().equals(Material.WOOL)&&damage.equals(data))
			{
				jumpready.remove(player,player);
				player.sendMessage(ChatColor.GRAY+"ジャンプ失敗");
			}
		}
		if(highjump.containsValue(player))
		{
			if(block.getType().equals(Material.SPONGE))
			{
				player.setVelocity(player.getLocation().getDirection().multiply(0));
				player.setVelocity(new Vector(player.getVelocity().getX(),1.8d,0.3d));
				highjump.remove(player);
			}
			else if(block.getType().equals(Material.WOOL)&&damage.equals(data))
			{
				highjump.remove(player,player);
				player.sendMessage(ChatColor.GRAY+"ジャンプ失敗");
			}
		}
		if(vineready.containsValue(player))
		{
			if(block2.getType().equals(Material.STAINED_CLAY)&&damage2.equals(data2))
			{
				vineready.remove(player);
				player.sendMessage(ChatColor.GRAY+"つたのぼり終了");
			}
		}
		double loca = e.getTo().getY();
		if(loca == 41.00000)
		{
			if(block.getType().equals(Material.GRASS))
			{
				if(jumpready.containsValue(player))
				{
					jumpready.remove(player);
					player.sendMessage(ChatColor.GRAY+"ジャンプの処理停止");
				}
				if(wallready.containsValue(player))
				{
					wallready.remove(player);
					player.sendMessage(ChatColor.GRAY+"壁の処理停止");
				}
				if(vineready.containsValue(player))
				{
					vineready.remove(player);
					player.sendMessage(ChatColor.GRAY+"つたの処理停止");
				}
				if(highjump.containsValue(player))
				{
					highjump.remove(player);
					player.sendMessage(ChatColor.GRAY+"ハイジャンプの処理停止");
				}
			}
		}

	}
	@EventHandler
	public void Interact(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
		Action act = e.getAction();
		Location loc = player.getLocation();
		World world = player.getWorld();
		Block block = world.getBlockAt(new Location(world,loc.getX(),loc.getY()-1.5,loc.getZ()));
		Block block2 = world.getBlockAt(new Location(world,loc.getX(),loc.getY()-1,loc.getZ()));
		@SuppressWarnings("deprecation")
		Byte damage = block2.getData();
		Byte data = 9;
		if(start)
		{
			if(act.equals(Action.PHYSICAL))
		{
			if(block.getType().equals(Material.EMERALD_BLOCK))
			{
				if(!jumpready.containsValue(player))
				{
					jumpready.put(player,player);
					player.sendMessage(ChatColor.GRAY+"ジャンプ準備");
				}
			}
			if(block2.getType().equals(Material.STAINED_CLAY)&&damage.equals(data))
			{
				if(!vineready.containsValue(player))
				{
					vineready.put(player, player);
					player.sendMessage(ChatColor.GRAY+"つたのぼり準備");
				}

			}
			if(block.getType().equals(Material.EMERALD_ORE))
			{
				if(!wallready.containsValue(player))
				{
					wallready.put(player,player);
					String clicker = player.getDisplayName();
					score = click.getScore(clicker);
					score.setScore(0);
					player.sendMessage(ChatColor.GRAY+"助走開始");
					cancel=false;
					new BukkitRunnable()
					{
						int exp = 60;
						@Override
						public void run()
						{
							player.setLevel(exp);
							if(exp==0)
							{
								wallready.remove(player);
								Scoreboard reset = click.getScoreboard();
								String clicker = player.getDisplayName();
								reset.resetScores(clicker);
								cancel=true;
							}
							if (cancel)
							{
								cancel();
							}
							exp -- ;
						}
					}.runTaskTimer(this, 0, 1);
				}
			}
			if(block.getType().equals(Material.DIAMOND_ORE))
			{
				if(!highjump.containsValue(player))
				{
					highjump.put(player, player);
					player.sendMessage(ChatColor.GRAY+"ハイジャンプ準備");

				}
			}
		}
		}
		if(act.equals(Action.RIGHT_CLICK_BLOCK))
		{

			if(wallready.containsValue(player))
			{
				String clicker = player.getDisplayName();
				score = click.getScore(clicker);
				int num = score.getScore()+1;
				score.setScore(num);
			}


		}
		if(act.equals(Action.RIGHT_CLICK_AIR))
		{

			if(wallready.containsValue(player))
			{
				String clicker = player.getDisplayName();
				score = click.getScore(clicker);
				int num = score.getScore()+1;
				score.setScore(num);
			}
		}
		if(act.equals(Action.LEFT_CLICK_BLOCK))
		{
		}


	}
	@EventHandler
	public void Anime(PlayerAnimationEvent e)
	{
		Player player = e.getPlayer();
		Location loc = player.getLocation();
		World world = player.getWorld();
		PlayerAnimationType ani = e.getAnimationType();
		if(ani.equals(PlayerAnimationType.ARM_SWING))
		{
			if(58<=loc.getX()&&loc.getX()<=63&&45<=loc.getY()&&loc.getY()<=51&&140<=loc.getZ()&&loc.getZ()<=142)
			{
				if(vineready.containsValue(player))
				{
					player.teleport(new Location(world,loc.getX(),loc.getY()+0.20000,loc.getZ()));
				}
			}

		}
	}
	@EventHandler
	public void Sneak(PlayerToggleSneakEvent e)
	{
		Player player = e.getPlayer();
			if(wallready.containsValue(player))
			{
				String play = player.getDisplayName();
				score = click.getScore(play);
				int result = score.getScore();
				int first = 0;
				int second = 0;
				if(1<=player.getLevel()&&player.getLevel()<=3)
				{
					first = 4;
				}
				else if(4<=player.getLevel()&&player.getLevel()<=7)
				{
					first = 3;
				}
				else if(8<=player.getLevel()&&player.getLevel()<=15)
				{
					first = 2;
				}
				else
				{
					first = 1;
				}

				if(15<=result)
				{
					second = 4;
				}
				else if(10<=result&&result<=14)
				{
					second = 3;
				}
				else if(5<=result&&result<=9)
				{
					second = 2;
				}
				else
				{
					second = 1;
				}
				wallready.remove(player);
				cancel=true;
				player.setLevel(0);
				Scoreboard reset = click.getScoreboard();
				String clicker = player.getDisplayName();
				reset.resetScores(clicker);
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,30,first+second));
				player.sendMessage(ChatColor.GRAY+"JUMP!!!");
			}

		}
	@EventHandler
	public void Left(PlayerQuitEvent e)
	{
		Player event = e.getPlayer();
		if(jumpready.containsValue(event))
		{
			jumpready.remove(event);
		}
		if(wallready.containsValue(event))
		{
			wallready.remove(event);
		}
		if(vineready.containsValue(event))
		{
			vineready.remove(event);
		}
	}



	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("koge1st"))
		{
			if(args.length>0)
			{
				if(args[0].equalsIgnoreCase("start"))
				{
					if(args.length==1)
					{
						start=true;
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("end"))
				{
					if(args.length==1)
					{
						start=false;
						return true;
					}
				}
			}
		}
		return false;
	}
}
