package com.fivethings.fivethings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    @Bind(R.id.edit_text)
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ask, container, false);
        ButterKnife.bind(this, rootView);

        button.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
       String msg =  editText.getText().toString();
        if (!msg.isEmpty()){
            sendEmail(msg);
        }
    }

    private void sendEmail(String msg){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"eliete_sr@hotmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
