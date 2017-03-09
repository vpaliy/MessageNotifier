package com.vpaliy.morsenotifier.domain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;

import com.vpaliy.morsenotifier.domain.converter.Converter;
import com.vpaliy.morsenotifier.domain.converter.MorseConverter;
import com.vpaliy.morsenotifier.domain.wrapper.TransformWrapper;
import com.vpaliy.morsenotifier.domain.wrapper.VibratorWrapper;

import java.util.List;

public class MessageHandler {

    @NonNull
    private final List<SmsMessage> smsMessageList;

    @NonNull
    private final Context context;

    private static  MessageHandler INSTANCE;

    private MessageHandler(Context context, List<SmsMessage> smsMessageList) {
        this.smsMessageList=smsMessageList;
        this.context=context;
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
        return null;
    }

    private TransformWrapper provideWithWrapper() {
        return null;
    }

}
