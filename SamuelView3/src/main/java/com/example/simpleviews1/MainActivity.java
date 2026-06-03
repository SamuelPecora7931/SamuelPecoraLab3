// Samuel Pecora - Student ID: n01437931
package com.example.simpleviews1;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // Dynamic looping data trackers for image clicks
    private final int[] imageResources = {
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4
    };

    private final String[] imageNames = {
            "Image One",
            "Image Two",
            "Image Three",
            "Image Four"
    };

    private int currentImageIndex = 0;

    public void btnSaved_clicked (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.humber.ca"));
        startActivity(browserIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* =========================================================================
         * 1. INTERCEPT BACK KEY WITH ALERT DIALOG
         * ========================================================================= */
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.custom_exit_icon)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.dialog_message)
                        .setCancelable(false)
                        .setPositiveButton(R.string.dialog_yes, (dialog, which) -> finish())
                        .setNegativeButton(R.string.dialog_no, (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });

        /* =========================================================================
         * 2. OPEN BUTTON (LAUNCH DEVICE SETTINGS PANE)
         * ========================================================================= */
        Button btnOpen = (Button) findViewById(R.id.btnOpen);
        if (btnOpen != null) {
            btnOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent settingsIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    startActivity(settingsIntent);
                }
            });
        }

        /* =========================================================================
         * 3. SAVE BUTTON (LAUNCH WEBSITE IMPLICIT INTENT)
         * ========================================================================= */
        Button btnSave = (Button) findViewById(R.id.btnSave);
        if (btnSave != null) {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.humber.ca"));
                    startActivity(browserIntent);
                }
            });
        }

        /* =========================================================================
         * 4. IMAGEBUTTON SELECTION & SHIFT ROTATION
         * ========================================================================= */
        ImageButton imgButton = (ImageButton) findViewById(R.id.myImageButton);
        if (imgButton != null) {
            imgButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentImageIndex = (currentImageIndex + 1) % imageResources.length;
                    imgButton.setImageResource(imageResources[currentImageIndex]);

                    String toastMessage = getString(R.string.toast_image_clicked) + " " + imageNames[currentImageIndex];
                    DisplayToast(toastMessage);
                }
            });
        }

        /* =========================================================================
         * 5. SWITCH PATTERN WITH PERSISTENT LONG SNACKBAR ALERT
         * ========================================================================= */
        SwitchCompat uiSwitch = (SwitchCompat) findViewById(R.id.uiSwitch);
        if (uiSwitch != null) {
            uiSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                String snackMessage = isChecked ? getString(R.string.switch_on_msg) : getString(R.string.switch_off_msg);
                Snackbar.make(findViewById(android.R.id.content), snackMessage, Snackbar.LENGTH_LONG).show();
            });
        }


        CheckBox checkBox = (CheckBox) findViewById(R.id.chkAutosave);
        if (checkBox != null) {
            checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked())
                        DisplayToast("CheckBox is checked");
                    else
                        DisplayToast("CheckBox is unchecked");
                }
            });
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdbGp1);
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb1 = (RadioButton) findViewById(R.id.rdb1);
                    if (rb1 != null && rb1.isChecked()) {
                        DisplayToast("Option 1 checked!");
                    } else {
                        DisplayToast("Option 2 checked!");
                    }
                }
            });
        }

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle1);
        if (toggleButton != null) {
            toggleButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (((ToggleButton) v).isChecked())
                        DisplayToast("Toggle button is On");
                    else
                        DisplayToast("Toggle button is Off");
                }
            });
        }
    }

    private void DisplayToast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /* =========================================================================
     * 6. SYSTEM LIFECYCLE BACKGROUND HOOK
     * ========================================================================= */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifecycleCheck", "Samuel Pecora - n1323242");
    }
}