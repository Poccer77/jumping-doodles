package Utilities;

public abstract class Tools {

    public static float RangeToRangeMapping(float value, float fromLowerBound, float fromUpperBound, float toLowerBound, float toUpperBound) {
        return (value - fromLowerBound) / (toLowerBound - fromLowerBound) * (toUpperBound - fromUpperBound) + fromUpperBound;
    }
}
