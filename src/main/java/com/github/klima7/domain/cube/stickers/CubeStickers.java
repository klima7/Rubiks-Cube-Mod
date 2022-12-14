package com.github.klima7.domain.cube.stickers;

import com.github.klima7.domain.cube.locations.OnCubeLocation;
import net.minecraft.core.Direction;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;

public class CubeStickers {

    private static final Direction[] DIRECTIONS_ORDER = {
            Direction.DOWN,
            Direction.UP,
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST
    };

    private static final Sticker[] FACES_STICKERS = {
            Sticker.WHITE,
            Sticker.YELLOW,
            Sticker.BLUE,
            Sticker.GREEN,
            Sticker.ORANGE,
            Sticker.RED
    };

    private static final CubeStickers solved = createSolved();
    private static final CubeStickers stickerless = createStickerless();

    public static final int FACES_COUNT = 6;
    public static final int STICKERS_COUNT = FACES_COUNT * FaceStickers.STICKERS_COUNT;

    private final EnumMap<Direction, FaceStickers> faces = new EnumMap<>(Direction.class);

    public CubeStickers(Sticker[] stickers) {
        if(stickers.length != STICKERS_COUNT)
            throw new IllegalArgumentException("Stickers count must be " + STICKERS_COUNT);

        for(int i=0; i<FACES_COUNT; i++) {
            int bottomBound = i * FaceStickers.STICKERS_COUNT;
            int upperBound = (i+1) * FaceStickers.STICKERS_COUNT;
            Sticker[] stickersRange = Arrays.copyOfRange(stickers, bottomBound, upperBound);
            FaceStickers faceStickers = new FaceStickers(stickersRange);
            faces.put(DIRECTIONS_ORDER[i], faceStickers);
        }
    }

    public static CubeStickers fromText(String text) {
        Sticker[] stickers = text.chars()
                .mapToObj(c -> Sticker.fromLetter((char) c))
                .toArray(Sticker[]::new);
        return new CubeStickers(stickers);
    }

    public static CubeStickers copyOf(CubeStickers cubeStickers) {
        return CubeStickers.fromText(cubeStickers.toText());
    }

    public static CubeStickers getSolved() {
        return CubeStickers.copyOf(solved);
    }

    public static CubeStickers getStickerless() {
        return CubeStickers.copyOf(stickerless);
    }

    public String toText() {
        StringBuilder builder = new StringBuilder();
        for(Direction direction : DIRECTIONS_ORDER) {
            String faceText = faces.get(direction).toText();
            builder.append(faceText);
        }
        return builder.toString();
    }

    public void setSticker(OnCubeLocation location, Sticker sticker) {
        getFaceStickers(location.getDirection()).setSticker(location, sticker);
    }

    public Sticker getSticker(OnCubeLocation location) {
        return getFaceStickers(location.getDirection()).getSticker(location);
    }

    public FaceStickers getFaceStickers(Direction direction) {
        return faces.get(direction);
    }

    public FaceStickers setFaceStickers(Direction direction, FaceStickers faceStickers) {
        return faces.put(direction, faceStickers);
    }

    public int getSolvedFacesCount() {
        int solvedFacesCount = 0;
        for(FaceStickers faceStickers : faces.values()) {
            if(faceStickers.isSolved()) {
                solvedFacesCount++;
            }
        }
        return solvedFacesCount;
    }

    public boolean isSolved() {
        return getSolvedFacesCount() == FACES_COUNT;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName()).append("(");
        for(Direction direction : DIRECTIONS_ORDER) {
            builder.append(direction)
                    .append(": ")
                    .append(getFaceStickers(direction).toText())
                    .append(", ");
        }
        builder.delete(builder.length()-2, builder.length());
        builder.append(")");
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        CubeStickers cubeStickers = (CubeStickers) other;
        return this.toText().equals(cubeStickers.toText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(faces);
    }

    private static CubeStickers createSolved() {
        Sticker[] stickers = new Sticker[FACES_COUNT * FaceStickers.STICKERS_COUNT];
        for(int faceIndex=0; faceIndex < FACES_COUNT; faceIndex++) {
            Sticker sticker = FACES_STICKERS[faceIndex];
            for(int stickerIndex=0; stickerIndex < FaceStickers.STICKERS_COUNT; stickerIndex++) {
                stickers[faceIndex * FaceStickers.STICKERS_COUNT + stickerIndex] = sticker;
            }
        }
        return new CubeStickers(stickers);
    }

    private static CubeStickers createStickerless() {
        Sticker[] stickers = new Sticker[FACES_COUNT * FaceStickers.STICKERS_COUNT];
        Arrays.fill(stickers, Sticker.NONE);
        return new CubeStickers(stickers);
    }

}
