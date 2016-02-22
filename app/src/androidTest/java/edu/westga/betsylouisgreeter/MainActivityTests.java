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
    MainActivity activity;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.activity = getActivity();
    }

    public void testActivityExists() {
        assertNotNull(this.activity);
    }

    public void testReverseButtonIsDisabled() {
        Button reverseButton =
                (Button) this.activity.findViewById(R.id.reverse_button);
        assertTrue(!reverseButton.isEnabled());
    }

    public void testGreet() {
        final EditText nameEditText =
                (EditText) this.activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                //nameEditText.requestFocus();
                nameEditText.setText("Jake");
            }
        });

        /*getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");*/

        // Tap "Greet" button
        // ----------------------

        Button greetButton =
                (Button) this.activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);

        // Verify greet message
        // ----------------------

        TextView greetMessage =
                (TextView) this.activity.findViewById(R.id.message_text_view);

        String actualText = greetMessage.getText().toString();
        String testString = "Hello, Jake!";
        //String testString = "Hello, " + nameEditText.getText().toString() + "!";
        assertEquals(testString, actualText);
    }
}
