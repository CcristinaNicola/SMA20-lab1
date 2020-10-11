package com.example.hellloandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private boolean text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText eName = (EditText) findViewById((R.id.eName));
        Button bClick = (Button) findViewById((R.id.bClick));
        Button bShare = (Button) findViewById((R.id.bShare));
        Button bSearch = (Button) findViewById((R.id.bSearch));
        final TextView tName = (TextView) findViewById((R.id.tName));

        bClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               openDialog();

               String name = eName.getText().toString();
               tName.setText("Hello," + name +"!");

            }

            public void clicked(View view){
                switch (view.getId()){
                    case R.id.bClick:
                        String name = eName.getText().toString();
                        tName.setText("Hello," + name +"!");
                        break;
                }
            }


            public void openDialog(){

               NDialog nDialog = new NDialog();
               nDialog.show(getSupportFragmentManager(),"Text");

            }
        });

        Spinner coloredSpinner = findViewById(R.id.coloredSpinner);
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.Spinner_Items,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource((R.layout.spinner_dropdown_layout));
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);


        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                tName.setText("Hello," + name +"!");
                shareText(eName);
            }

            public void shareText(EditText eName){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,text);
                sendIntent.setType("text/plain");

                if(sendIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(sendIntent);
                }
            }
        });

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                tName.setText("Hello," + name +"!");
                searchText(eName);
            }

            public void searchText(EditText eName){
                String url = "http://www.google.com";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                sendIntent.setAction(Intent.ACTION_SEND);

                if(sendIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(sendIntent);
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> adapterView,View view,int i, long l) {
        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}