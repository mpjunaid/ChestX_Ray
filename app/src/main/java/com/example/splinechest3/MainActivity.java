package com.example.splinechest3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ListView listv;
    String ltitle[]={"Patient-1","Patient-2","Patient-3","Patient-4","Patient-5","Patient-6"};
    String ldate[]={"1/1/2019","2/2/2019","3/3/2019","1/1/2019","2/2/2019","3/3/2019"};
    String lmobile[]={"(+91) 6735748523","(+91) 5455556356","(+91) 5543685214","(+91) 6735748523","(+91) 5455556356","(+91) 5543685214"};
    int images[]={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listv= findViewById(R.id.ListView);

        Myadapter myadapter=new Myadapter(this,ltitle,ldate,lmobile,images);

        listv.setAdapter(myadapter);


        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"Pressed:"+i,Toast.LENGTH_SHORT).show();
                Intent intentnext;
                intentnext = new Intent(MainActivity.this,Display.class);
                intentnext.putExtra("NUM",images[i]+"");
                intentnext.putExtra("name",ltitle[i]+"");
                intentnext.putExtra("date",ldate[i]+"");
                intentnext.putExtra("mobile",lmobile[i]+"");
                startActivity(intentnext);
            }
        });
    }

    class Myadapter extends ArrayAdapter<String>{
        Context mcont;
        String name[];
        String date[];
        String mobi[];
        int img[];

        Myadapter(Context mcont,String name[],String date[],String mobi[],int img[]){
            super(mcont,R.layout.listitem,R.id.name,name);
            this.date=date;
            this.img=img;
            this.mobi=mobi;
            this.name=name;
            this.mcont=mcont;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.listitem,parent,false);
            ImageView im=row.findViewById(R.id.image1);
            TextView t1=row.findViewById(R.id.name);
            TextView t2=row.findViewById(R.id.date);
            TextView t3=row.findViewById(R.id.mobile);

            im.setImageResource(img[position]);
            t1.setText(name[position]);
            t2.setText(date[position]);
            t3.setText(mobi[position]);
            return row;
        }
    }
}
