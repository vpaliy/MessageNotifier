package com.vpaliy.morsenotifier.domain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;

import com.vpaliy.morsenotifier.R;
import com.vpaliy.morsenotifier.domain.converter.Converter;
import com.vpaliy.morsenotifier.domain.converter.MorseConverter;
import com.vpaliy.morsenotifier.domain.wrapper.TransformWrapper;
import com.vpaliy.morsenotifier.domain.wrapper.VibratorWrapper;
import com.vpaliy.morsenotifier.utils.SHWrapper;

import java.util.List;

public class MessageHandler {

    private static final int MORSE=0;

    @NonNull
    private final List<SmsMessage> smsMessageList;

    @NonNull
    private final Context context;

    @NonNull
    private SHWrapper shWrapper;

    private static  MessageHandler INSTANCE;

    private MessageHandler(@NonNull Context context,@NonNull List<SmsMessage> smsMessageList) {
        this.smsMessageList=smsMessageList;
        this.context=context;
        this.shWrapper=SHWrapper.wrap(context.getSharedPreferences(context.
            getResources().getString(R.string.shPreferences),Context.MODE_PRIVATE));
    }

    public static MessageHandler buildHandler(@NonNull Context context, @NonNull List<SmsMessage> smsMessageList) {
        if(INSTANCE==null) {
            synchronized (MessageHandler.class) {
                if(INSTANCE==null) {
                    INSTANCE=new MessageHandler(context, smsMessageList);
                }
            }
        }
        return INSTANCE;
    }

    public void handle() {
        TransformWrapper wrapper=provideWithWrapper();
        Converter<? extends TransformWrapper> converter=provideWithConverter(wrapper);
        converter.convert();
    }


    private Converter<? extends TransformWrapper> provideWithConverter(TransformWrapper wrapper) {
        return new MorseConverter<>(wrapper);
    }

    private TransformWrapper provideWithWrapper() {
        return new VibratorWrapper(context);
    }

}
