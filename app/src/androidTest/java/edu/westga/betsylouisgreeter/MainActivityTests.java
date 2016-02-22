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
    Button greetButton;
    Button reverseButton;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() {
        this.activity = getActivity();
        this.nameEditText = (EditText) this.activity.findViewById(R.id.greet_edit_text);
        this.greetMessage = (TextView) this.activity.findViewById(R.id.message_text_view);
        this.greetButton = (Button) this.activity.findViewById(R.id.greet_button);
        this.reverseButton = (Button) this.activity.findViewById(R.id.reverse_button);
    }

    public void testActivityExists() {
        assertNotNull(this.activity);
    }

    public void testReverseButtonIsDisabled() {
        assertTrue(!this.reverseButton.isEnabled());
    }

    public void testGreet() {

        this.enterInput();
        this.clickGreet();

        String expectedString = "Hello, Jake!";
        String actualText = this.greetMessage.getText().toString();
        assertEquals(expectedString, actualText);
    }

    private void enterInput() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                //MainActivityTests.this.nameEditText.requestFocus();
                MainActivityTests.this.nameEditText.setText("Jake");
            }
        });
    }

    // Tap "Greet" button
    private void clickGreet() {
        TouchUtils.clickView(this, this.greetButton);
    }

    public void testReverseButtonIsEnabledWhenGreetIsClicked() {
        this.clickGreet();
        assertTrue(this.reverseButton.isEnabled());
    }

    // Tap "Reverse" button
    private void clickReverse() {
        TouchUtils.clickView(this, this.reverseButton);
    }

    public void testTextIsReversedWhenBothButtonsAreClicked() {
        this.enterInput();
        this.clickGreet();
        this.clickReverse();
        String expectedString = "!ekaJ ,olleH";
        String actualText = this.greetMessage.getText().toString();
        assertEquals(expectedString, actualText);
    }
}
