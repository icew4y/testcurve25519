package com.icew4y.testcurve25519;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.whispersystems.curve25519.NativeCurve25519Provider;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);


        //sharedkey
        NativeCurve25519Provider my = new NativeCurve25519Provider();
        byte[] my_privateKey = my.generatePrivateKey();
        byte[] my_publicKey = my.generatePublicKey(my_privateKey);

        NativeCurve25519Provider their = new NativeCurve25519Provider();
        byte[] their_privateKey = their.generatePrivateKey();
        byte[] their_publicKey = their.generatePublicKey(their_privateKey);

        byte[] my_sharedkey = my.calculateAgreement(my_privateKey, their_publicKey);
        byte[] their_sharedkey = their.calculateAgreement(their_privateKey, my_publicKey);

        //Sign
        String data = "hello";

        byte[] signature = my.calculateSignature(my.getRandom(64), my_privateKey, data.getBytes());
        boolean b = their.verifySignature(my_publicKey, data.getBytes(), signature);
        System.out.println(b + "");
    }
}