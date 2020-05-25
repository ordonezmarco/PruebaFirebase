package com.enginegroupsolutions.pruebafirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference root = mRootReference.child("message");

    EditText editText;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        message = (TextView) findViewById(R.id.message);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    String mensaje = dataSnapshot.getValue().toString();
                    Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_LONG).show();
                    message.setText(mensaje);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void WriteFirebase(View view) {
        root.setValue(editText.getText().toString());
        editText.setText("");
    }
}
