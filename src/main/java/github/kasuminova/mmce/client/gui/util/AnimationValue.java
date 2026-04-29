package github.kasuminova.mmce.client.gui.util;

public class AnimationValue {

    private float value;
    private float target;
    private int duration;

    public AnimationValue(float value, float target, int duration) {
        this.value = value;
        this.target = target;
        this.duration = duration;
    }

    public void update() {
        // Stub
    }

    public boolean isFinished() {
        return Math.abs(value - target) < 0.001F;
    }

    public boolean isAnimFinished() {
        return isFinished();
    }

    public float getValue() {
        return value;
    }

    public float get() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void set(float value) {
        this.value = value;
    }

    public void set(int value) {
        this.value = (float) value;
    }

    public void set(double value) {
        this.value = (float) value;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
