package com.example.android.business_card;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableTextView textView;
    Animation anim, anim1, anim2, anim3, anim4;

    //String text="Uber Technologies Inc.\n is a peer-to-peer ridesharing, food delivery, and transportation network company headquartered in San Francisco, California, with operations in 633 cities worldwide. Its platforms can be accessed via its websites and mobile apps.\n for more information:\n https://en.wikipedia.org/wiki/Uber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spanned text = Html.fromHtml("<strong> Uber Technologies Inc. </strong><br/>is a peer-to-peer ridesharing, food delivery, and transportation network company headquartered in San Francisco, California, with operations in 633 cities worldwide. Its platforms can be accessed via its websites and mobile apps.<br/> for more information:<br/>-<br/> <a href='https://en.wikipedia.org/wiki/Uber'> https://en.wikipedia.org/wiki/Uber</a>");
        textView = findViewById(R.id.expandable_text_view);
        textView.setText(text);
        animationTask();
    }

    public void webIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("www.uber.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void phoneIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:800 101 3611(65)\n31584255"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void googleMapsIntent(View view) {
        String uri = Uri.encode("San Francisco, California, United States");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Sorry Something wrong has happened\n check if your device has google maps installed", Toast.LENGTH_LONG).show();
        }
    }

    public void animationTask() {
        anim = AnimationUtils.loadAnimation(this, R.anim.movex);
        anim.setDuration(1500);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.movex);
        anim1.setDuration(1700);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.movex);
        anim2.setDuration(2000);
        anim3 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        anim3.setDuration(1500);
        anim4 = AnimationUtils.loadAnimation(this, R.anim.movey);
        anim4.setDuration(2200);
        TextView textLocation = findViewById(R.id.location);
        TextView textWebSite = findViewById(R.id.website);
        TextView textPhone = findViewById(R.id.phoneNumber);
        ImageView cover = findViewById(R.id.logoImage);
        TextView welcomeText = findViewById(R.id.welcomeText);
        textLocation.setAnimation(anim);
        textWebSite.setAnimation(anim1);
        textPhone.setAnimation(anim2);
        cover.setAnimation(anim3);
        welcomeText.setAnimation(anim4);
    }
}