package org.bukkit.material;

import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.*;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DirectionalTest {
    static final BlockFace[] nsewArray = new BlockFace[]{BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
    static final List<Object[]> testList = new ArrayList<Object[]>();

    static {
        addTest(new Bed());
        addTest(new Button());
        addTest(new Chest());
        addTest(new CocoaPlant());
        addTest(new Diode());
        addTest(new Dispenser());
        // addTest(new Door()); // NOOP
        addTest(new EnderChest());
        addTest(new Furnace());
        addTest(new Gate());
        addTest(new OppositeDirectionTest(new Ladder())); // DONE
        addTest(new Lever());
        addTest(new PistonBaseMaterial(Material.PISTON_BASE));
        addTest(new PistonExtensionMaterial(Material.PISTON_EXTENSION));
        addTest(new Pumpkin());
        addTest(new RedstoneTorch());
        addSignPostTest(); // DONE
        addTest(new Sign(Material.SIGN)); // DONE
        addTest(new Skull());
        addTest(new Stairs(Material.WOOD));
        addTest(new Torch());
        addTest(new TrapDoor());
        addTest(new TripwireHook());
    }

    static void addSignPostTest() {
        List<BlockFace> faces = new ArrayList<BlockFace>();
        for (BlockFace face : BlockFace.values()) {
            switch (face) {
            case UP:
            case DOWN:
            case SELF:
                continue;
            default:
                faces.add(face);
            }
        }

        addTest(new DirectionTest(new Sign(Material.SIGN_POST), faces.toArray(new BlockFace[0])));
    }

    static void addTest(Directional object) {
        addTest(object, nsewArray);
    }

    static void addTest(Directional object, BlockFace[] faces) {
        addTest(new DirectionTest(object, faces));
    }

    static void addTest(DirectionTest value) {
        testList.add(new Object[]{value, value.object.toString()});
    }

    static class DirectionTest {
        final Directional object;
        final BlockFace[] faces;

        DirectionTest(final Directional object) {
            this(object, nsewArray);
        }

        DirectionTest(final Directional object, final BlockFace[] faces) {
            this.object = object;
            this.faces = faces;
        }

        void runTest() {
            for (BlockFace setFace : faces) {
                setNewFace(setFace);
                assertEquals(setFace, getNewFace());
            }
        }

        void setNewFace(BlockFace face) {
            object.setFacingDirection(face);
        }

        BlockFace getNewFace() {
            return object.getFacing();
        }
    }

    static class OppositeDirectionTest extends DirectionTest {
        OppositeDirectionTest(final Directional object) {
            super(object, nsewArray);
        }

        OppositeDirectionTest(final Directional object, final BlockFace[] faces) {
            super(object, faces);
        }

        BlockFace getNewFace() {
            return object.getFacing().getOppositeFace();
        }
    }

    @Parameters(name = "[{index}]:{1}")
    public static List<Object[]> data() {
        return testList;
    }
    @Parameter(0)
    public DirectionTest currentTest;
    @Parameter(1)
    public String testName;

    @Test
    public void runTest() {
        currentTest.runTest();
    }
}
