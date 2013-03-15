package com.arpith.crc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	boolean encoder = true;
	int l1, l2, i, j, k;
	long temp;
	long divisor;
	long dividend;
	int result, sum;
	int a[] = new int[20];
	int b[] = new int[20];
	int c[] = new int[20];
	int d[] = new int[20];
	EditText input1;
	EditText input2;
	Button b1;
	Button b2;
	TextView display;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input1 = (EditText) findViewById(R.id.text1);
		input2 = (EditText) findViewById(R.id.text2);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		display = (TextView) findViewById(R.id.textView1);
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				divisor = Long.parseLong(input1.getText().toString());
				dividend = Long.parseLong(input2.getText().toString());
				display.setText("Values: you input are: " + divisor + "  "
						+ dividend);
				l1 = String.valueOf(divisor).length();
				l2 = String.valueOf(dividend).length();
				display.setText("Values: you input are: " + l1 + "  " + l2);
				temp = divisor;
				for (i = l1 - 1; i >= 0; i--) {
					a[i] = (int) (temp % 10);
					temp = temp / 10;
				}
				temp = dividend;
				for (i = l2 - 1; i >= 0; i--) {
					b[i] = (int) (temp % 10);
					temp = temp / 10;
				}
				if (l2 < l1) {
					result = 0;
					for (i = 0; i < l2; i++) {
						result = (b[i] + (result * 10));
					}
					display.setText("Codeword: " + dividend + " " + result);
				} else {
					int x = l2 - l1 + 1;
					for (k = 0; k < l1; k++)
						c[k] = b[k];
					int z = k - 1;
					for (i = 1; i <= x; i++) {

						if (c[0] == 1) {
							d[i] = 1;
							for (j = 0; j < l1; j++) {
								if (a[j] == c[j])
									c[j] = 0;
								else
									c[j] = 1;
							}
						} else {
							d[i] = 0;
							for (j = 0; j < l1; j++) {
								if (c[j] == 0)
									c[j] = 0;
								else
									c[j] = 1;
							}
						}
						for (j = 1; j < l1; j++) {
							c[j - 1] = c[j];
						}
						z++;
						c[j - 1] = b[z];
					}
					result = 0;
					for (i = 0; i < l1 - 1; i++) {
						result = (c[i] + (10 * result));
					}
					dividend /= 1000;
					if (encoder)
						display.setText("Codeword: " + dividend + " " + result);
					else
						display.setText("Syndrome: " + result);

				}
			}
		});

		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (encoder) {
					encoder = false;
					b2.setText("Codeword -> Syndrome (Decoder)");
				} else {
					encoder = true;
					b2.setText("Dataword -> Codeword (Encoder)");
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
