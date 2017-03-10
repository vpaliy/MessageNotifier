package com.vpaliy.morsenotifier.domain.wrapper;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Vibrator;
import android.support.annotation.NonNull;

public class VibratorWrapper extends TransformWrapper {

    private Vibrator vibrator;

    public VibratorWrapper(@NonNull Context context) {

        this.vibrator=Vibrator.class.cast(context.getSystemService(Context.VIBRATOR_SERVICE));
    }

    public void transform(long[] durations) {
        vibrator.vibrate(durations,-1);
    }

}
