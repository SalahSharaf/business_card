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
        Spanned text = Html.fromHtml("<strong> Uber Technologies Inc. </strong><br/>is a peer-to-peer ridesharing, food delivery, and transportation network company headquartered in San Francisco, California, with operations in 633 cities worldwide. Its platforms can be accessed via its websites and mobile apps.<br/> for more information:<br/> <a href='https://en.wikipedia.org/wiki/Uber'> https://en.wikipedia.org/wiki/Uber</a>");
        textView = findViewById(R.id.expandable_text_view);
        textView.setText(text);
        animationTask();
    }

    public void webIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.udacity.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void phoneIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:650-555-5555"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void googleMapsIntent(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=an+2465 Latham St+Mountain View");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Sorry Something wrong has happend check if your device has google maps instaled", Toast.LENGTH_LONG).show();
        }

    }

    public void facebookIntent(View view) {
        String uri = getFacebookPageURL(this);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void twitterIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://twitter.com/udacity?lang=ar"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void linkedinIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://group/[164070]"));
        final PackageManager packageManager = getApplicationContext().getPackageManager();
        final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.isEmpty()) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/edu/udacity-164070"));
        }
        startActivity(intent);
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=https://www.facebook.com/Udacity";
            } else { //older versions of fb app
                return "fb://page/Udacity";
            }
        } catch (PackageManager.NameNotFoundException e) {
            return "https://www.facebook.com/Udacity"; //normal web url
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