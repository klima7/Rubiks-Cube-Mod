package com.github.klima7.common.domain.operation.rotation;

import net.minecraft.core.Direction;

public enum RotationAxis {

    X(
            Direction.Axis.X,
            Direction.WEST, Direction.EAST,
            new Direction[] { Direction.NORTH, Direction.UP, Direction.SOUTH, Direction.DOWN},
            new int[] { 2, 0, 0, 2 }
    ),

    Y(
            Direction.Axis.Y,
            Direction.UP, Direction.DOWN,
            new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST },
            new int[] { 1, 1, 1, 1 }
    ),

    Z(
            Direction.Axis.Z,
            Direction.NORTH, Direction.SOUTH,
            new Direction[] { Direction.EAST, Direction.UP, Direction.WEST, Direction.DOWN },
            new int[] { 0, 0, 0, 0 }
    );

    private final Direction.Axis axis;
    private final Direction clockwiseFace;
    private final Direction counterclockwiseFace;
    private final Direction[] sideFaces;
    private final int[] sideRotations;

    RotationAxis(Direction.Axis axis, Direction clockwiseFace, Direction counterclockwiseFace, Direction[] sideFaces, int[] sideRotations) {
        this.axis = axis;
        this.clockwiseFace = clockwiseFace;
        this.counterclockwiseFace = counterclockwiseFace;
        this.sideFaces = sideFaces;
        this.sideRotations = sideRotations;
    }

    public static RotationAxis fromAxis(Direction.Axis axis) {
        for(RotationAxis rotationAxis : RotationAxis.values()) {
            if(rotationAxis.getAxis() == axis) {
                return rotationAxis;
            }
        }
        throw new IllegalArgumentException("Unable to find OrientationAxis with given axis");
    }

    public Direction.Axis getAxis() {
        return this.axis;
    }

    public Direction getClockwiseFace() {
        return clockwiseFace;
    }

    public Direction getCounterclockwiseFace() {
        return counterclockwiseFace;
    }

    public Direction getSideFace(int index) {
        return this.sideFaces[index];
    }

    public int getRotation(int index) {
        return this.sideRotations[index];
    }

}
