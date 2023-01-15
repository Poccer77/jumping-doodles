package Utilities;

public enum Values {

    SIZE(0.8f),
    GAME_GAP(0.5f * SIZE.value),
    GAME_SPEED(0.001f),
    PLAYER_WIDTH(0.05f * SIZE.value),
    PLAYER_HEIGHT(PLAYER_WIDTH.value * 2),
    PLAYER_JUMP_STRENGTH(GAME_GAP.value / 10f),
    PLAYER_HORIZONTAL_MAX_SPEED(0.02f * SIZE.value),
    PLATFORM_WIDTH_LB(0.1f * SIZE.value),
    PLATFORM_WIDTH_UB(0.3f * SIZE.value),
    PLATFORM_HEIGHT(0.05f * SIZE.value);

    public final float value;

    private Values(float value) {
        this.value = value;
    }
}
