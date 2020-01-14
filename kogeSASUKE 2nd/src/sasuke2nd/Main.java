package sasuke2nd;

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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener
{
	private Player second;
	private HashMap<Player,Player> salmon = new HashMap<Player,Player>();
	private ItemStack stick = new ItemStack(Material.STICK,1);
	private boolean stickflag = false;
	private boolean spider = false;
	private boolean start = false;
	ItemMeta meta;


	@Override
	public void onEnable()
	{
		super.onEnable();
		getServer().getPluginManager().registerEvents(this,this);
		meta = stick.getItemMeta();
		meta.setDisplayName("鉄棒");
		stick.setItemMeta(meta);
		getLogger().info("プラグインが読み込まれました");
	}
	@Override
	public void onDisable()
	{
		getLogger().info("プラグインが終了しました");
	}
	public void Move(PlayerMoveEvent e)
	{
		Player event=e.getPlayer();
		Block block = e.getTo().getBlock().getRelative(BlockFace.DOWN);
		double loca = e.getTo().getY();
		if(loca == 41.00000)
		{
			if(block.getType().equals(Material.GRASS))
			{
				if(salmon.containsValue(event.getName()))
				{
					salmon.remove(event);
					event.sendMessage(ChatColor.GRAY+"サーモン処理停止");
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void Interact(PlayerInteractEvent e)
	{
		Player event = e.getPlayer();
		Action act = e.getAction();
		Location loc = event.getLocation();
		World world = event.getWorld();
		Block block = world.getBlockAt(new Location(world,loc.getX(),loc.getY()-5,loc.getZ()));
		ItemStack item = e.getItem();
		Block clicked = e.getClickedBlock();
		Byte data = 7;
		if(event.equals(second))
		{
			if(act.equals(Action.PHYSICAL))
			{
				if(block.getType().equals(Material.IRON_BLOCK))
				{
					if(!salmon.containsValue(event))
					{
						salmon.put(event, event);
						event.setItemInHand(stick);
						event.sendMessage(ChatColor.GRAY+"サーモンラダー開始");
						event.updateInventory();
					}
				}
			}
			if(act.equals(Action.RIGHT_CLICK_BLOCK))
			{
				if(salmon.containsValue(event))
				{
					if(item!=null)
					{
						if(item.getType().equals(Material.STICK))
						{
							if(!stickflag)
							{
								Byte damage = clicked.getData();

									if(clicked.getType().equals(Material.STEP)&&damage.equals(data))
									{
										if(209<=loc.getZ()&&211>=loc.getZ())
										{
											if(119<=loc.getX()&&120>=loc.getX())
											{
												if(41<=loc.getY()&&42>=loc.getY())
												{
													event.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,10,2));
													new BukkitRunnable()
													{
														Block set = world.getBlockAt(new Location(world,119,41,209));
														@Override
														public void run()
														{
															set.setType(Material.AIR);
															set.getRelative(0,0,1).setType(Material.AIR);
														}
													}.runTaskLater(this, 10);
													stickflag = true;
												}
												else if(44<=loc.getY()&&45>=loc.getY())
												{
													event.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,10,2));
													new BukkitRunnable()
													{
														Block set = world.getBlockAt(new Location(world,119,44,209));
														@Override
														public void run()
														{
															set.setType(Material.AIR);
															set.getRelative(0,0,1).setType(Material.AIR);
														}
													}.runTaskLater(this, 10);
													stickflag = true;
												}
												else if(47<=loc.getY()&&48>=loc.getY())
												{
													event.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,10,2));
													new BukkitRunnable()
													{
														Block set = world.getBlockAt(new Location(world,119,47,209));
														@Override
														public void run()
														{
															set.setType(Material.AIR);
															set.getRelative(0,0,1).setType(Material.AIR);
														}
													}.runTaskLater(this, 10);
													stickflag = true;
												}
												else if(50<=loc.getY()&&51>=loc.getY())
												{
													Block ride = world.getBlockAt(new Location(world,120,52,209));
													ride.setType(Material.AIR);
													ride.getRelative(0,0,1).setType(Material.AIR);
													new BukkitRunnable()
													{
														Block set = world.getBlockAt(new Location(world,119,50,209));
														@Override
														public void run()
														{
															set.setType(Material.AIR);
															set.getRelative(0,0,1).setType(Material.AIR);
														}
													}.runTaskLater(this, 10);
													stickflag = true;
												}
											}
										}
									}
							}
							if(stickflag)
							{
								if(clicked.getType().equals(Material.IRON_BLOCK))
								{
									if(209<=loc.getZ()&&211>=loc.getZ())
									{
										if(119<=loc.getX()&&120>=loc.getX())
										{
											if(43<=loc.getY()&&45>=loc.getY())
											{
												Block set = world.getBlockAt(new Location(world,119,44,209));
												set.setType(Material.STEP);
												set.setData(data);
												set.getRelative(0,0,1).setType(Material.STEP);
												set.getRelative(0,0,1).setData(data);
												stickflag = false;
											}
											else if(46<=loc.getY()&&48>=loc.getY())
											{
												Block set = world.getBlockAt(new Location(world,119,47,209));
												set.setType(Material.STEP);
												set.setData(data);
												set.getRelative(0,0,1).setType(Material.STEP);
												set.getRelative(0,0,1).setData(data);
												stickflag = false;
											}
											else if(49<=loc.getY()&&51>=loc.getY())
											{
												Block set = world.getBlockAt(new Location(world,119,50,209));
												set.setType(Material.STEP);
												set.setData(data);
												set.getRelative(0,0,1).setType(Material.STEP);
												set.getRelative(0,0,1).setData(data);
												stickflag = false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void action(PlayerAnimationEvent e)
	{
		Player event = e.getPlayer();
		Location loc = event.getLocation();
		World world = event.getWorld();
		PlayerAnimationType anime = e.getAnimationType();
		if(start)
		{
			if(132<=loc.getX()&&loc.getX()<=134&&43<=loc.getY()&&loc.getY()<=51&&214<=loc.getZ()&&loc.getZ()<=233)
			{
				if(anime.equals(PlayerAnimationType.ARM_SWING))
				{
					if(spider)
					{
						Block set = world.getBlockAt(new Location(world,139,26,231));
						set.setType(Material.REDSTONE_BLOCK);
						spider = false;
					}
					else if(!spider)
					{
						Block set = world.getBlockAt(new Location(world,134,26,231));
						set.setType(Material.REDSTONE_BLOCK);
						spider = true;
					}
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("koge2nd"))
		{
			if(args.length>0)
			{
				if(args[0].equalsIgnoreCase("set"))
				{
					if(args.length<2)
					{
						sender.sendMessage(ChatColor.RED+"プレイヤーを指定してください");
						return true;
					}
					else if(args.length==2)
					{
						stickflag = false;
						for(Player play:getServer().getOnlinePlayers())
						{
							if(play.getName().equals(args[1]))
							{
								second = play;
							}
						}
						for(Player play:getServer().getOnlinePlayers())
						{
							String str = second.getName();
							if(play.isOp())
							{
								play.sendMessage(ChatColor.GRAY+str+"を2ndのプレイヤーに登録しました");
							}
						}
						start = true;
						spider = false;
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("reset"))
				{
					if(args.length<2)
					{
						World world = Bukkit.getWorld("sasuke");
						Byte data = 7;
						Block set = world.getBlockAt(new Location(world,119,41,209));
						set.setType(Material.STEP);
						set.setData(data);
						set.getRelative(0,0,1).setType(Material.STEP);
						set.getRelative(0,0,1).setData(data);
						set = world.getBlockAt(new Location(world,119,44,209));
						set.setType(Material.AIR);
						set.getRelative(0,0,1).setType(Material.AIR);
						set = world.getBlockAt(new Location(world,119,47,209));
						set.setType(Material.AIR);
						set.getRelative(0,0,1).setType(Material.AIR);
						set = world.getBlockAt(new Location(world,119,50,209));
						set.setType(Material.AIR);
						set.getRelative(0,0,1).setType(Material.AIR);
						set = world.getBlockAt(new Location(world,120,52,209));
						set.setType(Material.BARRIER);
						set.getRelative(0,0,1).setType(Material.BARRIER);
						stickflag = false;
						salmon.clear();
						second = null;
						for(Player play:getServer().getOnlinePlayers())
						{
							if(play.isOp())
							{
								play.sendMessage(ChatColor.GRAY+"2ndのプレイヤーがリセットされました");
							}
						}
						start = false;
						spider = false;
						return true;
					}
				}
			}
		}
		return false;
	}
}
