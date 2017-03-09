package com.vpaliy.morsenotifier.utils;

import android.support.annotation.NonNull;

public final class ProjectUtils {

    private ProjectUtils(){
        throw new UnsupportedOperationException();
    }

    public static String TAG(@NonNull Object object) {
        return object.getClass().getSimpleName();
    }
}
