package net.blitzcube.mlapi.renderer;

import com.google.common.base.Preconditions;

import net.blitzcube.peapi.api.entity.fake.IFakeEntity;
import net.blitzcube.peapi.api.entity.fake.IFakeEntityFactory;
import net.blitzcube.peapi.api.entity.hitbox.IHitbox;
import net.blitzcube.peapi.api.entity.modifier.IEntityModifier;
import net.blitzcube.peapi.api.entity.modifier.IEntityModifierRegistry;
import net.blitzcube.peapi.api.entity.modifier.IModifiableEntity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * Created by iso2013 on 6/4/2018.
 */
public class LineEntityFactory {

    //Armor stand modifiers
    private final IEntityModifier<Boolean> armorStandInvisible;
    private final IEntityModifier<String> name;
    private final IEntityModifier<Boolean> nameVisible;
    private final IEntityModifier<Boolean> marker;

    //Slime modifiers
    private final IEntityModifier<Integer> size;

    //Silverfish modifiers
    private final IEntityModifier<Boolean> silent;
    private final IEntityModifier<Boolean> noAI;

    //Entity modifiers
    private final IEntityModifier<Boolean> entityInvisible;

    private final IFakeEntityFactory factory;

    public LineEntityFactory(IEntityModifierRegistry registry, IFakeEntityFactory factory) {
        Preconditions.checkArgument(registry != null, "Modifier registry must not be null");
        Preconditions.checkArgument(factory != null, "Fake entity factory must not be null");

        this.factory = factory;
        this.name = registry.lookup(EntityType.ARMOR_STAND, "CUSTOM_NAME", String.class);
        this.nameVisible = registry.lookup(EntityType.ARMOR_STAND, "CUSTOM_NAME_VISIBLE", Boolean.class);
        this.size = registry.lookup(EntityType.SLIME, "SIZE", Integer.class);
        this.marker = registry.lookup(EntityType.ARMOR_STAND, "MARKER", Boolean.class);
        this.entityInvisible = registry.lookup(EntityType.SILVERFISH, "INVISIBLE", Boolean.class);
        this.armorStandInvisible = registry.lookup(EntityType.ARMOR_STAND, "INVISIBLE", Boolean.class);
        this.noAI = registry.lookup(EntityType.SILVERFISH, "NO_AI", Boolean.class);
        this.silent = registry.lookup(EntityType.SILVERFISH, "SILENT", Boolean.class);
    }

    public IFakeEntity createArmorStand(Location location) {
        IFakeEntity entity = factory.createFakeEntity(EntityType.ARMOR_STAND);
        entity.setLocation(location);

        IModifiableEntity modifiableEntity = entity.getModifiableEntity();
        this.armorStandInvisible.setValue(modifiableEntity, true);
        this.marker.setValue(modifiableEntity, true);
        this.nameVisible.setValue(modifiableEntity, false);
        this.name.setValue(modifiableEntity, "");

        return entity;
    }

    public IFakeEntity createSlime(Location location) {
        IFakeEntity entity = factory.createFakeEntity(EntityType.SLIME);
        entity.setLocation(location);

        IModifiableEntity modifiable = entity.getModifiableEntity();
        this.entityInvisible.setValue(modifiable, true);
        this.size.setValue(modifiable, -1);

        return entity;
    }

    public IFakeEntity createSilverfish(Location location) {
        IFakeEntity entity = factory.createFakeEntity(EntityType.SILVERFISH);
        entity.setLocation(location);

        IModifiableEntity modifiable = entity.getModifiableEntity();
        this.entityInvisible.setValue(modifiable, true);
        this.silent.setValue(modifiable, true);
        this.noAI.setValue(modifiable, true);

        return entity;
    }

    public void updateName(IFakeEntity entity, String newName) {
        if (newName != null) {
            this.nameVisible.setValue(entity.getModifiableEntity(), true);
            this.name.setValue(entity.getModifiableEntity(), newName);
        } else {
            this.name.setValue(entity.getModifiableEntity(), ":D");
            this.nameVisible.setValue(entity.getModifiableEntity(), false);
        }
    }

    public void updateLocation(Location location, IFakeEntity entity) {
        entity.setLocation(location);
    }

    public IHitbox getHitbox(Entity newEntity) {
        return factory.createHitboxFromEntity(newEntity);
    }
}
