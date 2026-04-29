package github.kasuminova.mmce.common.util;

import github.kasuminova.ecoaeextension.common.util.BlockPos;
import github.kasuminova.mmce.common.helper.IDynamicPatternInfo;

public class DynamicPattern implements IDynamicPatternInfo {

    private Object pattern;
    private int priority;
    private int size;

    public DynamicPattern(Object pattern, int priority) {
        this.pattern = pattern;
        this.priority = priority;
    }

    @Override
    public DynamicPattern getPattern() {
        return this;
    }

    public void setPattern(Object pattern) {
        this.pattern = pattern;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BlockPos getStructureSizeOffset() {
        return BlockPos.ORIGIN;
    }

    public BlockPos getStructureSizeOffsetStart() {
        return BlockPos.ORIGIN;
    }

}
