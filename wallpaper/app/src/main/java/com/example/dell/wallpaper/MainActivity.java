package com.example.dell.wallpaper;

import android.app.WallpaperManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase data1;
    EditText text;
    int i = 1;
    ArrayList<String> names;
    ArrayAdapter arrayAdapter;
/*
 try {
            String sQL = "INSERT INTO favorite(name) VALUES(?)";
            SQLiteStatement sqLiteStatement = data1.compileStatement(sQL);
             Log.i("info", String.valueOf(e1.getText()));
            sqLiteStatement.bindString(1, String.valueOf(e1.getText()));
            sqLiteStatement.execute();
        } catch (Exception e) {
            Log.i("erroir", e.toString());

        }
        show();
 */
    public void save(View view) {
        EditText text=(EditText)findViewById(R.id.content);
      Log.i("info", String.valueOf(text.getText()));
        try {
            String sQL = "INSERT INTO favorite(name) VALUES(?)";
            SQLiteStatement sqLiteStatement = data1.compileStatement(sQL);
            Log.i("info", String.valueOf(text.getText()));
            sqLiteStatement.bindString(1, String.valueOf(text.getText()));
            sqLiteStatement.execute();
        } catch (Exception e) {
            Log.i("erroir", e.toString());

        }
        show();
    }

    public void show() {
          names.clear();
        Cursor c = data1.rawQuery("SELECT * FROM   favorite", null);
        c.moveToFirst();
        int nameIndex = c.getColumnIndex("name");
        if(c!=null)
            names.add(c.getString(nameIndex));
        while (c.moveToNext())
        {
            names.add(c.getString(nameIndex));
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       text=(EditText)findViewById(R.id.content);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names= new ArrayList<String>();
       ListView list = (ListView)findViewById(R.id.list) ;
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);

        data1 = this.openOrCreateDatabase("Wallpaper", MODE_PRIVATE, null);
        //data.execSQL("DELETE FROM  favorite");
        data1.execSQL("CREATE TABLE IF NOT EXISTS favorite( name STRING) ");
        show();
        list.setAdapter(arrayAdapter);
      /*  String sQL = "INSERT INTO info(id,name)VALUES( ? ,? )";
        SQLiteStatement sqLiteStatement = database.compileStatement(sQL);
        sqLiteStatement.bindString(1, String.valueOf(id));
        sqLiteStatement.bindString(2, name);
        String s = "INSERT INTO info(id,name)VALUES( ? ,? )";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(s);
        sqLiteStatement1.bindString(1, String.valueOf(id++));
        sqLiteStatement1.bindString(2, "shreyas");

        sqLiteStatement.execute();*/
        WallpaperManager wallpaperManager =WallpaperManager.getInstance(getApplicationContext());
        int wall=R.drawable.w1;
        try {
            wallpaperManager.setResource(wall);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}