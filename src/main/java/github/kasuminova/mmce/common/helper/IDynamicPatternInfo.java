package github.kasuminova.mmce.common.helper;

import github.kasuminova.mmce.common.util.DynamicPattern;

public interface IDynamicPatternInfo {

    DynamicPattern getPattern();

    int getPriority();

    int getSize();

}
