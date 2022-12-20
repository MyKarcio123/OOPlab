package agh.ics.oop;

public enum BiomeType {
    SNOWY,
    ICY,
    BAGNO,
    FOREST,
    JUNGLE,
    DESERT;
    @Override
    public String toString() {
        return switch (this) {
            case SNOWY -> "#00aeff";
            case ICY -> "#8aceed";
            case BAGNO -> "#2c3b30";
            case FOREST -> "#4dff00";
            case JUNGLE -> "#024d16";
            case DESERT -> "#ffd900";
        };
    }
}
