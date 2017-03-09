package com.vpaliy.morsenotifier.domain.converter;

import com.vpaliy.morsenotifier.domain.wrapper.TransformWrapper;

public abstract class Converter<T extends TransformWrapper> {
    public abstract void convert();
    protected abstract long lengthFor(int charX);
}
