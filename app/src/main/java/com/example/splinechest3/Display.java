package com.example.splinechest3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Display extends AppCompatActivity {

    private Classifier mClassifier;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    String label[]={"Atelectasis",
            "Cardiomegaly",
            "Consolidation",
           "Edema",
            "Effusion",
            "Emphysema",
            "Fibrosis",
            "Hernia",
            "Infiltration",
            "Mass",
            "Nodule",
           "NO_Finding",
            "Pleural_Thickening",
            "Pneumonia",
            "Pneumothorax"};

    TextView t;
    TextView personal;
    ImageView i;
    Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        init();

        i=findViewById(R.id.Display_image);
        t=findViewById(R.id.Message);
        personal=findViewById(R.id.info);

        Bundle b = getIntent().getExtras();
        String text1=b.getString("NUM");
        String name =b.getString("name");
        String date =b.getString("date");
        String mobile =b.getString("mobile");
        String hi="NAME :" +name+" \nDATE : "+date+" \nMOBILE : "+mobile;
        personal.setText(hi);

        i.setImageResource(Integer.parseInt(text1));

        BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
        image= drawable.getBitmap();

        image = Bitmap.createScaledBitmap(image,Classifier.IMG_WIDTH,Classifier.IMG_HEIGHT,false);

        Result result = mClassifier.classify(image);
        renderResult(result);

    }

    private void init() {
        try {
            mClassifier = new Classifier(this);
        } catch (IOException e) {
            Toast.makeText(this, "failed to create", Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, "init(): Failed to create Classifier", e);
        }
    }
    private void renderResult(Result result) {
        t.setText("Predicted Disease:"+label[result.getNumber()]+"\nProbability :"+result.getProbability()+"\nTimetaken:"+result.getTimeCost()+"ms");
    }
}
