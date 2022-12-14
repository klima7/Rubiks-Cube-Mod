package com.github.klima7.domain.operation.move;

import net.minecraft.core.Direction;

public enum MoveFace {

    NORTH(
            Direction.NORTH,
            new SideStickersLocations(Direction.UP, 2, 1, 0),
            new SideStickersLocations(Direction.WEST, 2, 1, 0),
            new SideStickersLocations(Direction.DOWN, 2, 1, 0),
            new SideStickersLocations(Direction.EAST, 2, 1, 0)
    ),

    SOUTH(
            Direction.SOUTH,
            new SideStickersLocations(Direction.UP, 6, 7, 8),
            new SideStickersLocations(Direction.EAST, 6, 7, 8),
            new SideStickersLocations(Direction.DOWN, 6, 7, 8),
            new SideStickersLocations(Direction.WEST, 6, 7, 8)
    ),

    EAST(
            Direction.EAST,
            new SideStickersLocations(Direction.UP, 8, 5, 2),
            new SideStickersLocations(Direction.NORTH, 8, 5, 2),
            new SideStickersLocations(Direction.DOWN, 0, 3, 6),
            new SideStickersLocations(Direction.SOUTH, 8, 5, 2)
    ),

    WEST(
            Direction.WEST,
            new SideStickersLocations(Direction.UP, 0, 3, 6),
            new SideStickersLocations(Direction.SOUTH, 0, 3, 6),
            new SideStickersLocations(Direction.DOWN, 8, 5, 2),
            new SideStickersLocations(Direction.NORTH, 0, 3, 6)
    ),

    UP(
            Direction.UP,
            new SideStickersLocations(Direction.NORTH, 6, 7, 8),
            new SideStickersLocations(Direction.EAST, 0, 3, 6),
            new SideStickersLocations(Direction.SOUTH, 2, 1, 0),
            new SideStickersLocations(Direction.WEST, 8, 5, 2)
            ),

    DOWN(
            Direction.DOWN,
            new SideStickersLocations(Direction.NORTH, 2, 1, 0),
            new SideStickersLocations(Direction.WEST, 0, 3, 6),
            new SideStickersLocations(Direction.SOUTH, 6, 7, 8),
            new SideStickersLocations(Direction.EAST, 8, 5, 2)
    );

    private final Direction direction;
    private final SideStickersLocations[] sideStickersLocations;

    MoveFace(Direction direction, SideStickersLocations sideStickersLocations1, SideStickersLocations sideStickersLocations2,
             SideStickersLocations sideStickersLocations3, SideStickersLocations sideStickersLocations4) {
        this.direction = direction;
        this.sideStickersLocations = new SideStickersLocations[] {sideStickersLocations1, sideStickersLocations2,
                sideStickersLocations3, sideStickersLocations4};
    }

    public static MoveFace fromDirection(Direction direction) {
        for(MoveFace moveFace : MoveFace.values()) {
            if(moveFace.getDirection() == direction) {
                return moveFace;
            }
        }
        throw new IllegalArgumentException("Unable to find MoveFace with given direction");
    }

    public Direction getDirection() {
        return this.direction;
    }

    public SideStickersLocations getSideStickersLocations(int index) {
        return this.sideStickersLocations[index];
    }

}
