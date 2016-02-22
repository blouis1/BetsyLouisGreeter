package edu.westga.betsylouisgreeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Betsy on 2/22/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity activity;
    private EditText nameEditText;
    TextView greetMessage;
    Button reverseButton;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() {
        this.activity = getActivity();
        this.nameEditText = (EditText) this.activity.findViewById(R.id.greet_edit_text);
        this.reverseButton = (Button) this.activity.findViewById(R.id.reverse_button);
        this.greetMessage = (TextView) this.activity.findViewById(R.id.message_text_view);
    }

    public void testActivityExists() {
        assertNotNull(this.activity);
    }

    public void testReverseButtonIsDisabled() {
        assertTrue(!this.reverseButton.isEnabled());
    }

    public void testGreet() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                //MainActivityTests.this.nameEditText.requestFocus();
                MainActivityTests.this.nameEditText.setText("Jake");
            }
        });

        /*getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");*/

        // Tap "Greet" button
        // ----------------------

        this.clickGreet();

        // Verify greet message
        // ----------------------

        String actualText = this.greetMessage.getText().toString();
        String testString = "Hello, Jake!";
        //String testString = "Hello, " + nameEditText.getText().toString() + "!";
        assertEquals(testString, actualText);
    }

    // Tap "Greet" button
    private void clickGreet() {
        Button greetButton =
                (Button) this.activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);
    }

    public void testReverseButtonIsEnabledWhenGreetIsClicked() {
        this.clickGreet();
        assertTrue(this.reverseButton.isEnabled());
    }
}
