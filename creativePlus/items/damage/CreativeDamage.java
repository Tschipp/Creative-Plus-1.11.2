package tschipp.creativePlus.items.damage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class CreativeDamage extends DamageSource{

	public CreativeDamage(String damageTypeIn) {
		super(damageTypeIn);
	}
	
	
	 public static DamageSource causePlayerDamage(EntityPlayer player)
	    {
	        return new EntityDamageSource("player", player).setDamageAllowedInCreativeMode();
	    }

}
