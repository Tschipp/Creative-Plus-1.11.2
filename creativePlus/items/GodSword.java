package tschipp.creativePlus.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.creativePlus.items.damage.CreativeDamage;

import com.google.common.collect.Multimap;


public class GodSword extends ItemSword {

	private final Item.ToolMaterial material;
	private float attackDamage;


	public GodSword(ToolMaterial material) {

		super(material);
		this.material = material;
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.attackDamage = Float.MAX_VALUE;
		this.setNoRepair();



	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		
		return true;
	}





	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
		}

		return multimap;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{	

		if(entity instanceof EntityPlayer && ((EntityPlayer)entity).inventory.hasItemStack(new ItemStack(CustomItems.godRing))) {

		}

		else if(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode) 	
		{

			entity.attackEntityFrom(CreativeDamage.causePlayerDamage(player), Float.MAX_VALUE);


		} 

		else {
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), Float.MAX_VALUE);


		}


		return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Kills all Entites in 1 hit");
		tooltip.add("Can kill Players in Creative mode");
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}



}
