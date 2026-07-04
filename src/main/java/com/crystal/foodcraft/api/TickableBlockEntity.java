package com.crystal.foodcraft.api;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

/**
 * <p>该接口的作用，使得原版tick形式参数减少（为零），使得tick方法变得简单</p>
 * <p>使用方法：</p>
 * <p>首先方块实体继承{@code TickableBlockEntity}接口，并实现tick抽象方法</p>
 * <pre><code>
 * public class ExampleBlockEntity extends TickableBlockEntity {
 *  public void tick() {
 *      // 代码块
 *  }
 * }
 * </code></pre>
 * <p>然后方块实现getTicker方法，在方法体中调用{@code TickableBlockEntity.getTicker}方法</p>
 * <pre><code>
 *     public class ExampleBlock extend Block {
 *         public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState blockState, BlockEntityType<T> type) {
 *             return TickableBlockEntity.getTicker(world);
 *         }
 *     }
 * </code></pre>
 * @see TickableBlockEntity#tick()
 */
public interface TickableBlockEntity {
    void tick();

    /**
     * <p>重写Tick方法，静态扩展方块实体</p>
     * <p>使得方法重写Tick方法变得简单，必须实现BlockEntityTicker中参数</p>
     * <p>由于是服务端，所以需要传入世界数据，判断是否为客户端</p>
     * @param world 世界
     * @return Tick时间
     * @param <T> 使静态方法更够通用到任何方块实体上
     */
    static <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world) {
        return world.isClientSide() ? null : (level, pos, state, blockEntity) -> {
            // 检查是否为具有Tick的方块实体
            if (blockEntity instanceof TickableBlockEntity tickableBlockEntity){
                tickableBlockEntity.tick();
            }
        };
    }
}
