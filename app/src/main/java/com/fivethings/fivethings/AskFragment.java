package com.fivethings.fivethings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eliete on 3/2/16.
 */
public class AskFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.send_button)
    Button button;
    @Bind(R.id.edit_dought)
    EditText editTextDought;
    @Bind(R.id.edit_email)
    EditText editTextEmail;
    @Bind(R.id.dought_text_input)
    TextInputLayout textInputLayoutDought;
    @Bind(R.id.dought_text_email)
    TextInputLayout textInputLayoutEmail;


    public static final String MSG = "msg";
    public static final String EMAIL = "email";

    private String msg;
    private String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ask, container, false);
        ButterKnife.bind(this, rootView);

        if (savedInstanceState != null){
            msg = savedInstanceState.getString(MSG);
            email = savedInstanceState.getString(EMAIL);
        }

        button.setOnClickListener(this);
        cleanErrorMessage(editTextDought, textInputLayoutDought);
        cleanErrorMessage(editTextEmail, textInputLayoutEmail);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        msg =  editTextDought.getText().toString();
        email = editTextEmail.getText().toString();

        if (!msg.isEmpty() && !email.isEmpty()){
            sendEmail(msg);
        }else if (msg.isEmpty() && !email.isEmpty()){
            setEmptyMessage(textInputLayoutDought, "mensagem");
        } else if (!msg.isEmpty() && email.isEmpty()){
            setEmptyMessage(textInputLayoutEmail, "email");
        }
    }

    private void sendEmail(String msg){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Dúvida Five Things");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void cleanErrorMessage(EditText editText, final TextInputLayout textInputLayout){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0)
                    textInputLayout.setError(null);
            }
        });

    }

    public void setEmptyMessage(TextInputLayout textInputLayout, String errorMessage){
        textInputLayout.setError(errorMessage + " é obrigatório");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MSG, msg);
        outState.putString(EMAIL, email);
    }
}
