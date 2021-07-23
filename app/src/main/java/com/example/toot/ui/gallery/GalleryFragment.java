package com.example.toot.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.toot.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        EditText input;
        Button result,reset;
        TextView drink,intake;

        input=root.findViewById(R.id.type);
        result=root.findViewById(R.id.btnres);
        reset=root.findViewById(R.id.btnreset);
        drink=root.findViewById(R.id.txtinter);
        intake=root.findViewById(R.id.txtres);


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strage=input.getText().toString();

                if (strage.equals("")){
                    input.setError("Please Enter Your Age");
                    input.requestFocus();
                    return;

                }
                int age=Integer.parseInt(strage);


                int intakeValue = waterintake(age);
                drink.setText(interpreBMi(intakeValue));
                intake.setText("Every Day");


                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drink.setText("");
                        intake.setText("");
                        input.setText("");
                    }
                });
            }
        });
        return  root;
    }

    public int waterintake(int age){
        return age;
    }






    public String interpreBMi(int intakeValue) {
        if (intakeValue < 9) {
            return "1 liters Of Water";
        }
        else if (intakeValue< 14) {
            return "2 liters Of Water";

        }
        else if (intakeValue < 19) {
            return "2.5 liters Of Water";
        }
        else if(intakeValue<100){
            return "3 liters Of Water";
        }else
            return "Drink As Much You Like";

    }

}