package com.vpaliy.morsenotifier.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ConvertModelTest {

    private static final String MESSAGE="Just a message";
    private static final String SENDER="Just a sender";

    private static final int FREQUENCY=1;
    private static final int VOLUME=2;

    //100% coverage of the ConvertModel class
    @Test
    public void testModelCreationUsingBuilder() {
        ConvertModel.Builder builder=new ConvertModel.Builder(MESSAGE,SENDER);
        builder.frequency(FREQUENCY);
        builder.volume(VOLUME);
        ConvertModel model=builder.build();

        assertThat(model.getMessage(),is(MESSAGE));
        assertThat(model.getSender(),is(SENDER));
        assertThat(model.getVolume(),is(VOLUME));
        assertThat(model.getFrequency(),is(FREQUENCY));

        model.setFrequency(FREQUENCY);
        model.setVolume(VOLUME);
        model.setMessage(MESSAGE);
        model.setSender(SENDER);

        assertThat(model.getMessage(),is(MESSAGE));
        assertThat(model.getSender(),is(SENDER));
        assertThat(model.getVolume(),is(VOLUME));
        assertThat(model.getFrequency(),is(FREQUENCY));

    }
}
