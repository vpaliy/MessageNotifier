package com.vpaliy.morsenotifier.domain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;

import com.vpaliy.morsenotifier.R;
import com.vpaliy.morsenotifier.domain.converter.Converter;
import com.vpaliy.morsenotifier.domain.converter.MorseConverter;
import com.vpaliy.morsenotifier.domain.model.ConvertModel;
import com.vpaliy.morsenotifier.domain.wrapper.TransformWrapper;
import com.vpaliy.morsenotifier.domain.wrapper.VibratorWrapper;
import com.vpaliy.morsenotifier.utils.SHWrapper;

import java.util.LinkedList;
import java.util.List;

public class MessageHandler {

    private static final int MORSE=0;

    @NonNull
    private final List<SmsMessage> smsMessageList;

    @NonNull
    private final Context context;

    @NonNull
    private SHWrapper shWrapper;


    private MessageHandler(@NonNull Context context,@NonNull List<SmsMessage> smsMessageList) {
        this.smsMessageList=smsMessageList;
        this.context=context;
        this.shWrapper=SHWrapper.wrap(context.getSharedPreferences(context.
            getResources().getString(R.string.shPreferences),Context.MODE_PRIVATE));
    }

    public static MessageHandler buildHandler(@NonNull Context context, @NonNull List<SmsMessage> smsMessageList) {
        return new MessageHandler(context,smsMessageList);
    }

    public void handle() {
        TransformWrapper wrapper=provideWithWrapper();
        Converter<? extends TransformWrapper> converter=provideWithConverter(wrapper);
        converter.convert();
    }


    private Converter<? extends TransformWrapper> provideWithConverter(TransformWrapper wrapper) {
        List<ConvertModel> convertModelList=new LinkedList<>();
        for(SmsMessage smsMessage:smsMessageList) {
            ConvertModel.Builder builder=new ConvertModel.Builder(smsMessage.getMessageBody(),
                smsMessage.getOriginatingAddress()).frequency(1).volume(1);
            convertModelList.add(builder.build());
        }
        return new MorseConverter<>(wrapper,convertModelList);
    }

    private TransformWrapper provideWithWrapper() {
        return new VibratorWrapper(context);
    }

}
