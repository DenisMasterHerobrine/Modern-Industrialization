package aztech.modern_industrialization.pipes.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * A pipe network. It is very important that you create a new empty data object if your constructor was passed null.
 */
public abstract class PipeNetwork {
    protected int id;
    public PipeNetworkManager manager;
    public PipeNetworkData data;
    public Map<BlockPos, PipeNetworkNode> nodes = new HashMap<>();
    public boolean ticked = false;

    public PipeNetwork(int id, PipeNetworkData data) {
        this.id = id;
        this.data = data;
    }

    public void fromTag(CompoundTag tag) {
        id = tag.getInt("id");
        data.fromTag(tag.getCompound("data"));
    }

    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("id", id);
        tag.put("data", data.toTag(new CompoundTag()));
        return tag;
    }

    public void tick(World world) {

    }

    /**
     * Allow merging networks when the player explicitly requests to do so.
     * When this function is called, it must return a new PipeNetworkData without modifying either itself or its parameter.
     * @return null if there can be no merge, or the new pipe network data should there be a merge.
     */
    public PipeNetworkData merge(PipeNetwork other) {
        return null;
    }
}