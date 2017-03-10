package com.vpaliy.morsenotifier.domain.model;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

public class ConvertModel {

    /* Speed */
    private int frequency;

    /* Noise factor*/
    private int volume;

    /* Main information */
    private String message;
    private String sender;

    private ConvertModel(@NonNull Builder builder) {
        this.message=builder.message;
        this.sender=builder.sender;
        this.frequency=builder.frequency;
        this.volume=builder.volume;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getVolume() {
        return volume;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public static class Builder {
        /* Required  */
        private final String message;
        private final String sender;

        /*Optional */
        private int frequency=1;
        private int volume=1;

        public Builder(@NonNull String message, @NonNull String sender) {
            this.message=message;
            this.sender=sender;
        }

        public Builder frequency(@IntRange(from = 0, to = 10) int frequency) {
            this.frequency=frequency;
            return this;
        }

        public Builder volume(@IntRange(from = 0,to = 10) int volume) {
            this.volume=volume;
            return this;
        }

        public ConvertModel build() {
            return new ConvertModel(this);
        }

    }



}
