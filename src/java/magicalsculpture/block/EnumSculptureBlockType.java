package magicalsculpture.block;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum EnumSculptureBlockType implements IStringSerializable {
    Base(0, false, "base"),
    Stone(1, false, "stone"),

    FormedBase(2, true, "formedbase"),
    FormedStone(3, true, "formedstone"),

    Render(4, true, "render");

    public static final PropertyEnum<EnumSculptureBlockType> property = PropertyEnum.create("blocktype", EnumSculptureBlockType.class);

    public static final EnumSculptureBlockType[] values = new EnumSculptureBlockType[5];
    static final EnumSculptureBlockType[] rawStructure = new EnumSculptureBlockType[2];
    static final EnumSculptureBlockType[] formedStructure = new EnumSculptureBlockType[3];

    static {
        int i = 0;
        int j = 0;

        for (EnumSculptureBlockType value : EnumSculptureBlockType.values()) {
            if (value.formed) {
                formedStructure[j] = value;
                j++;
            } else {
                rawStructure[i] = value;
                i++;
            }
            values[value.index] = value;
        }
    }


    public final int index;
    public final boolean formed;
    private final String name;
    EnumSculptureBlockType(int index, boolean formed, String name) {
        this.index = index;
        this.formed = formed;
        this.name = name;
    }

    public static String[] getRawStructureNames() {
        int length = rawStructure.length;
        String[] ret = new String[length];

        for (int i = 0; i < length; i++) {
            ret[i] = rawStructure[i].name;
        }

        return ret;
    }

    public static EnumSculptureBlockType fromInt(int in) {
        if (in >= values.length || in < 0) {
            return null;
        }

        return values[in];
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }
}
