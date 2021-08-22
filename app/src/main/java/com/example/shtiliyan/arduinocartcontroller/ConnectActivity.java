package com.example.shtiliyan.arduinocartcontroller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;


public class ConnectActivity extends ActionBarActivity {

    private BluetoothConnector btConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btConnector = new BluetoothConnector();
        btConnector.enableBluetooth();
        btConnector.findPairedDivices();

        setContentView(R.layout.activity_connect);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }

    private void initForwardButton(final ImageButton button) {
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        btConnector.OutputStream.write("F".getBytes());
                    }
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        btConnector.OutputStream.write("X".getBytes());
                    }
                } catch (IOException e) {
                    return false;
                }
                return true;
            }
        });
    }

    private void initBackwardButton(ImageButton button) {
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        btConnector.OutputStream.write("B".getBytes());
                    }
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        btConnector.OutputStream.write("X".getBytes());
                    }
                } catch (IOException e) {
                    return false;
                }
                return true;
            }
        });
    }

    private void initTurnLeftButton(ImageButton button) {
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        btConnector.OutputStream.write("L".getBytes());
                    }
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        btConnector.OutputStream.write("X".getBytes());
                    }
                } catch (IOException e) {
                    return false;
                }
                return true;
            }
        });
    }

    private void initTurnRightButton(ImageButton button) {
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    btConnector.OutputStream.write("R".getBytes());
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    btConnector.OutputStream.write("X".getBytes());
                }
                } catch (IOException e) {
                    return false;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_connect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_connect, container, false);

            return rootView;
        }
    }

    public void connectButtonClick(View v)
    {
        btConnector.openConnection("HC-06");
        Button button = (Button)v.findViewById(R.id.connectButton);
        button.setClickable(false);
        button.setEnabled(false);

        initForwardButton((ImageButton) findViewById(R.id.forwardButton));
        initBackwardButton((ImageButton) findViewById(R.id.backwardButton));
        initTurnLeftButton((ImageButton) findViewById(R.id.turnLeftButton));
        initTurnRightButton((ImageButton) findViewById(R.id.turnRightButton));
    }
}
