package com.example.acrigooo.plante_consulter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.acrigooo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class order extends Fragment {
    private View view;
    DatabaseReference rootRef, pesticidedelay,pesticidedone,soleildelay,soleildone;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);

        String QRcode=getActivity().getIntent().getExtras().getString("QR");
        //Toast.makeText(getActivity(),QRcode,Toast.LENGTH_LONG).show();


        final EditText tView = view.findViewById(R.id.pesticidedelay);
        final Button sButton = view.findViewById(R.id.pesticide);
        final EditText tViewsoleil = view.findViewById(R.id.soleildelay);
        final Button sButtonsoleil = view.findViewById(R.id.soleil);


        rootRef = FirebaseDatabase.getInstance().getReference();

        pesticidedelay = rootRef.child("pesticidedelay");
        pesticidedone = rootRef.child("pesticidedone");
        soleildelay=  rootRef.child("soleildelay");
        soleildone = rootRef.child("soleildone");


        final DatabaseReference databaseReferencepesticidedone = FirebaseDatabase.getInstance().getReference("pesticidedone").child("pesticidedone");
        final DatabaseReference databaseReferencesoleildone = FirebaseDatabase.getInstance().getReference("soleildone").child("soleildone");


        sButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               pesticidedelay.child("pesticidedelay").setValue(tView.getText().toString());
               pesticidedone.child("pesticidedone").setValue("notdone");

               sButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
               sButton.setText("ON");


               databaseReferencepesticidedone.addValueEventListener(
                       new ValueEventListener() {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot) {
                               String UIDs = dataSnapshot.getValue(String.class);

                               if(UIDs.equals("done"))
                               {
                                   sButton.setBackgroundColor(getResources().getColor(R.color.white));
                                   sButton.setText("OFF");
                                   tView.getText().clear();

                               }

                           }

                           @Override
                           public void onCancelled(DatabaseError databaseError) {
                           }
                       });



           }
       });

        sButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                sButton.setBackgroundColor(getResources().getColor(R.color.white));
                sButton.setText("OFF");
                pesticidedelay.child("pesticidedelay").setValue("");

                tView.getText().clear();
                return true;
            }
        });









        sButtonsoleil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               soleildelay.child("soleildelay").setValue(tView.getText().toString());
                soleildone.child("soleildone").setValue("notdone");

                sButtonsoleil.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sButtonsoleil.setText("ON");


                databaseReferencesoleildone.addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String UID = dataSnapshot.getValue(String.class);

                                if(UID.equals("done"))
                                {
                                    sButtonsoleil.setBackgroundColor(getResources().getColor(R.color.white));
                                    sButtonsoleil.setText("OFF");
                                    tViewsoleil.getText().clear();

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });



            }
        });

        sButtonsoleil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                soleildelay.child("soleildelay").setValue("");

                sButtonsoleil.setBackgroundColor(getResources().getColor(R.color.white));
                sButtonsoleil.setText("OFF");
                tViewsoleil.getText().clear();
                return true;
            }
        });

























        return view;



    }



}
