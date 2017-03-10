package com.vpaliy.morsenotifier.utils;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SHWrapper {

    private final SharedPreferences SH;

    private static SHWrapper wrapper;

    private SHWrapper(@NonNull SharedPreferences SH) {
        this.SH=SH;
    }

    public static SHWrapper wrap(@NonNull SharedPreferences SH) {
        if(wrapper==null) {
            synchronized (SHWrapper.class) {
                if(wrapper==null) {
                    wrapper = new SHWrapper(SH);
                }
            }
        }
        return wrapper;
    }

}
