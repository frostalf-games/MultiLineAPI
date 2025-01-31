package net.blitzcube.mlapi.structure.transactions;

import java.util.List;

import net.blitzcube.mlapi.tag.RenderedTagLine;
import net.blitzcube.peapi.api.entity.IEntityIdentifier;

/**
 * Created by iso2013 on 7/31/2018.
 */
public class MoveTransaction extends StructureTransaction {

    private final IEntityIdentifier below;
    private final IEntityIdentifier above;
    private final List<RenderedTagLine> moved;
    private final boolean toSameLevel;

    public MoveTransaction(IEntityIdentifier below, IEntityIdentifier above, List<RenderedTagLine> moved, boolean toSameLevel) {
        this.below = below;
        this.above = above;
        this.moved = moved;
        this.toSameLevel = toSameLevel;
    }

    public IEntityIdentifier getBelow() {
        return below;
    }

    public IEntityIdentifier getAbove() {
        return above;
    }

    public List<RenderedTagLine> getMoved() {
        return moved;
    }

    public boolean isToSameLevel() {
        return toSameLevel;
    }
}
