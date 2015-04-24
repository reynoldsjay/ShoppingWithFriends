package com.generict.shoppingwithfriends;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by Vignesh Prasad 03/02/2015
 * Text Validator class taken from http://stackoverflow.com/a/11838715
 * It is used to validate the various text fields in the SignUpFragment
 * The class is abstract and an object of this class is created for each
 * of the text forms each one having its own validator.
 */
public abstract class TextValidator implements TextWatcher {

    private final TextView textView;

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = textView.getText().toString();
        validate(textView, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        /* Never used */
    }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {
        /* Never used */
    }

}
