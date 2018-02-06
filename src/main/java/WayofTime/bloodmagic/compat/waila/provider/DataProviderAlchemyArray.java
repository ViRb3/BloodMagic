package WayofTime.bloodmagic.compat.waila.provider;

import WayofTime.bloodmagic.apibutnotreally.Constants;
import WayofTime.bloodmagic.core.RegistrarBloodMagicBlocks;
import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.tile.TileAlchemyArray;
import WayofTime.bloodmagic.util.helper.TextHelper;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class DataProviderAlchemyArray implements IWailaDataProvider {
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return new ItemStack(RegistrarBloodMagicItems.ARCANE_ASHES).setStackDisplayName(TextHelper.getFormattedText(RegistrarBloodMagicBlocks.ALCHEMY_ARRAY.getLocalizedName()));
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (!config.getConfig(Constants.Compat.WAILA_CONFIG_ARRAY))
            return currenttip;

        if (accessor.getPlayer().isSneaking() || config.getConfig(Constants.Compat.WAILA_CONFIG_BYPASS_SNEAK)) {
            TileEntity tile = accessor.getTileEntity();
            if (tile instanceof TileAlchemyArray) {
                TileAlchemyArray tileArray = (TileAlchemyArray) tile;
                if (!tileArray.getStackInSlot(0).isEmpty())
                    currenttip.add(TextHelper.localize("waila.bloodmagic.array.reagent", tileArray.getStackInSlot(0).getDisplayName()));

                if (!tileArray.getStackInSlot(1).isEmpty())
                    currenttip.add(TextHelper.localize("waila.bloodmagic.array.catalyst", tileArray.getStackInSlot(1).getDisplayName()));
            }
        } else {
            currenttip.add(TextHelper.localizeEffect("waila.bloodmagic.sneak"));
        }

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        return null;
    }
}
