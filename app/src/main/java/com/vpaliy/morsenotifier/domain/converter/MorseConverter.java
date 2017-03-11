package com.vpaliy.morsenotifier.domain.converter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vpaliy.morsenotifier.domain.model.ConvertModel;
import com.vpaliy.morsenotifier.domain.wrapper.TransformWrapper;
import com.vpaliy.morsenotifier.utils.ProjectUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MorseConverter<T extends TransformWrapper> extends Converter<T> {

    private final T transformWrapper;

    private final List<ConvertModel> convertModelList;

    private static final long DOTS_IN_DASH       = 3;
    private static final long DOTS_IN_GAP        = 1;
    private static final long DOTS_IN_LETTER_GAP = 3;
    private static final long DOTS_IN_WORD_GAP   = 7;


    private static final String  CHARSET_MORSE  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,?'!/()&:;=+-_\"$@";

    private static final boolean     DOT  = true;
    private static final boolean     DASH = false;

    private static final boolean[][] MORSE  = {
            {DOT, DASH}, //A
            {DASH, DOT, DOT, DOT}, //B
            {DASH, DOT, DASH, DOT}, //C
            {DASH, DOT, DOT}, //D
            {DOT}, //E
            {DOT, DOT, DASH, DOT}, //F
            {DASH, DASH, DOT}, //G
            {DOT, DOT, DOT, DOT}, //H
            {DOT, DOT}, //I
            {DOT, DASH, DASH, DASH}, //J
            {DASH, DOT, DASH}, //K
            {DOT, DASH, DOT, DOT}, //L
            {DASH, DASH}, //M
            {DASH, DOT}, //N
            {DASH, DASH, DASH}, //O
            {DOT, DASH, DASH, DOT}, //P
            {DASH, DASH, DOT, DASH}, //Q
            {DOT, DASH, DOT}, //R
            {DOT, DOT, DOT}, //S
            {DASH}, //T
            {DOT, DOT, DASH}, //U
            {DOT, DOT, DOT, DASH}, //V
            {DOT, DASH, DASH}, //W
            {DASH, DOT, DOT, DASH}, //X
            {DASH, DOT, DASH, DASH}, //Y
            {DASH, DASH, DOT, DOT}, //Z
            {DASH, DASH, DASH, DASH, DASH}, //0
            {DOT, DASH, DASH, DASH, DASH}, //1
            {DOT, DOT, DASH, DASH, DASH}, //2
            {DOT, DOT, DOT, DASH, DASH}, //3
            {DOT, DOT, DOT, DOT, DASH}, //4
            {DOT, DOT, DOT, DOT, DOT}, //5
            {DASH, DOT, DOT, DOT, DOT}, //6
            {DASH, DASH, DOT, DOT, DOT}, //7
            {DASH, DASH, DASH, DOT, DOT}, //8
            {DASH, DASH, DASH, DASH, DOT}, //9
            {DOT, DASH, DOT, DASH, DOT, DASH}, //.
            {DASH, DASH, DOT, DOT, DASH, DASH}, //,
            {DOT, DOT, DASH, DASH, DOT, DOT}, //?
            {DOT, DASH, DASH, DASH, DASH, DOT}, //'
            {DASH, DOT, DASH, DOT, DASH, DASH}, //!
            {DASH, DOT, DOT, DASH, DOT}, ///
            {DASH, DOT, DASH, DASH, DOT}, //(
            {DASH, DOT, DASH, DASH, DOT, DASH}, //)
            {DOT, DASH, DOT, DOT, DOT}, //&
            {DASH, DASH, DASH, DOT, DOT, DOT}, //:
            {DASH, DOT, DASH, DOT, DASH, DOT}, //;
            {DASH, DOT, DOT, DOT, DASH}, //=
            {DOT, DASH, DOT, DASH, DOT}, //+
            {DASH, DOT, DOT, DOT, DOT, DASH}, //-
            {DOT, DOT, DASH, DASH, DOT, DASH}, //_
            {DOT, DASH, DOT, DOT, DASH, DOT}, //"
            {DOT, DOT, DOT, DASH, DOT, DOT, DASH}, //$
            {DOT, DASH, DASH, DOT, DASH, DOT} //@
    };

    public MorseConverter(@NonNull T wrapper, @NonNull List<ConvertModel> convertModelList) {
        this.transformWrapper=wrapper;
        this.convertModelList=convertModelList;
    }

    public MorseConverter(@NonNull T wrapper, @NonNull ConvertModel convertModel) {
        this(wrapper, Collections.singletonList(convertModel));
    }

    @Override
    protected long lengthFor(int charX) {
        return 0;
    }

    private List<Long> convertWord(@NonNull String word,long dot) {
        List<Long> durations=new LinkedList<>();

        final long dash=dot*DOTS_IN_DASH;
        final long letterGap = dot * DOTS_IN_LETTER_GAP;
        final long gap       = dot * DOTS_IN_GAP;

        for(int index=0;index<word.length();index++) {
            int jIndex=CHARSET_MORSE.indexOf(word.charAt(index));
            if(jIndex>=0) {
                boolean[] wordTable=MORSE[jIndex];
                jIndex=0;
                while(jIndex<=wordTable.length-1) {
                    durations.add(wordTable[jIndex++]?dot:dash);
                    if(jIndex<wordTable.length) {
                        durations.add(gap);
                    }
                }
            }

            if(index<word.length()) {
                durations.add(letterGap);
            }
        }
        return durations;
    }

    private long[] convertToArray(List<Long> durations) {
        long[] result=new long[durations.size()];
        for(int index=0;index<durations.size();index++) {
            result[index]=durations.get(index);
        }
        return result;
    }

    public void convert() {
        List<Long> durations=new LinkedList<>();
        for(ConvertModel convertModel:convertModelList) {
            final long dot=convertModel.getFrequency()*200;
            final long wordGap   = dot * DOTS_IN_WORD_GAP;
            String message=convertModel.getMessage();
            String[] words=message.toUpperCase().trim().split(" ");
            if(words.length!=0) {
                for(int index=0;index<words.length;index++) {
                    durations.addAll(convertWord(words[index],dot));
                    if(index<words.length) {
                        durations.add(wordGap);
                    }
                }
            }
        }
        //
        Log.d(ProjectUtils.TAG(this),Integer.toString(durations.size()));
        transformWrapper.transform(convertToArray(durations));
    }
}
