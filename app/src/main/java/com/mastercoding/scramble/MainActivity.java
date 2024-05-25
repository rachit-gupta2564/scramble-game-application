package com.mastercoding.scramble;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private String[] words = {"Ace", "Act", "Ago", "Aim", "Ale", "Apt", "Arc", "Arm", "Art", "Ash", "Ask", "Ate", "Axe", "Bag",
            "Ball", "Ban", "Bar", "Bat", "Bay", "Bed", "Bee", "Bet", "Bit", "Boa", "Box", "Boy", "Bud", "Bug", "Bus", "Buy", "Can", "Cap", "Car", "Cat", "Caw",
            "Cod", "Cog", "Cup", "Cut", "Dad", "Dam", "Day", "Deal", "Den", "Dew", "Die", "Dig", "Dip", "Do", "Dog", "Don", "Ear", "Eat", "Eel", "End", "Era",
            "Eye", "Far", "Fat", "Fed", "Fee", "Few", "Fit", "Fix", "Flu", "Fly", "Fog", "For", "Fun", "Gap", "Gas", "Gay", "Get", "God", "Goon", "Goo", "Gut",
            "Guy", "Had", "Ham", "Han", "Hap", "Hat", "Hay", "Hear", "Hen", "Her", "Hit", "Hoe", "Hop", "Hot", "Hug", "Ice", "Ink", "Inn", "Ins", "Jam", "Jaw",
            "Jay", "Jet", "Joe", "Jug", "Key", "Kid", "Kin", "Kit", "Kneel", "Knew", "Knot", "Know", "Lad", "Lag", "Land", "Lap", "Law", "Lay", "Lea", "Led",
            "Leg", "Let", "Lie", "Lit", "Log", "Loo", "Look", "Lose", "Lot", "Love", "Man", "Map", "Mar", "Mat", "May", "Me", "Mean", "Men", "Met", "Mew", "Mix",
            "Mom", "Mug", "Must", "Nap", "Net", "New", "Nip", "Nod", "Nor", "Not", "Now", "Nut", "Oak", "Oat", "Ode", "Off", "Oh", "Oil", "Old", "Omit", "On",
            "One", "Ore", "Out", "Own", "Ox", "Pal", "Pan", "Pap", "Par", "Pat", "Paw", "Pay", "Pea", "Peg", "Pen", "Pet", "Pew", "Pie", "Pig", "Pin", "Pit",
            "Pot", "Put", "Rag", "Rat", "Raw", "Ray", "Red", "Ree", "Rep", "Rest", "Rip", "Rob", "Rock", "Roe", "Rot", "Row", "Run", "Rye", "Saw", "Say", "Sea",
            "See", "Set", "Sew", "She", "Ship", "Sir", "Sit", "Ski", "Sky", "Sly", "So", "Sow", "Spa", "Spy", "Sue", "Sun", "Sup", "Tab", "Tag", "Tan", "Tap",
            "Tar", "Tea", "Ten", "Tie", "Tin", "Tip", "Toe", "Top", "Try", "Tub", "Tug", "Two", "Ugh", "Ump", "Unc", "Up", "Us", "Use", "Van", "Vat", "Vie",
            "Wad", "Wage", "Wag", "Wal", "War", "Was", "Way", "We", "Wear", "Web", "Wed", "Wet", "Who", "Why", "Wig", "Win", "Wit", "Wok", "Won", "Woo", "Yap",
            "Yea", "Yet", "You", "Yum", "After", "Again", "Ahead", "Alone", "Along", "Angry", "Apart", "Apple", "Armful", "Around", "Awake", "Backed", "Bacon",
            "Ballot", "Beach", "Beast", "Beets", "Bench", "Bible", "Blind", "Blood", "Board", "Boring", "Bottle", "Bower", "Brain", "Brake", "Bread", "Brief",
            "Bring", "Brown", "Brush", "Built", "Bunch", "Cable", "Cake", "Calm", "Camp", "Candy", "Capon", "Card", "Care", "Carry", "Carve", "Cast", "Cause",
            "Chair", "Chant", "Chase", "Cheap", "Chest", "Chick", "Child", "Churn", "Clean", "Clear", "Climb", "Close", "Cloud", "Clown", "Coarse", "Coffee",
            "Collar", "Color", "Comb", "Come", "Comfort", "Comic", "Cook", "Cool", "Copper", "Copy", "Cord", "Count", "Court", "Cover", "Cozy", "Crash", "Crazy",
            "Cream", "Creek", "Crew", "Crisp", "Cross", "Crowd", "Cruel", "Crush", "Cry", "Dance", "Danger", "Dark", "Date", "Dawn", "Dead", "Deal", "Death", "Deep",
            "Deer", "Deny", "Depart", "Desk", "Deserve", "Desire", "Desk", "Devil", "Dinner", "Dirty", "Dish", "Dive", "Dozen", "Draft", "Drain", "Dress", "Drink",
            "Drive", "Drop", "Drum", "Dry", "Duck", "Dust", "Early", "Earth", "Easy", "Edge", "Eight", "Either", "Elect", "Empty", "Enter", "Envy", "Equal", "Erase",
            "Error", "Escape", "Estate", "Even", "Exact", "Except", "Excuse", "Exit", "Expect", "Extra", "Eyelid", "Face", "Fact", "Fade", "Fail", "Fair", "Fairy", "Faith",
            "Fall", "False", "Family", "Famous", "Fancy", "Farmyard", "Fast", "Fatten", "Favor", "Feast", "Feather", "Feed", "Feel", "Fight", "Find", "Finger", "Finish",
            "Fire", "Fish", "Fist", "Fit", "Fix", "Flame", "Flash", "Flat", "Flavor", "Flee", "Fleet", "Flesh", "Flier", "Flight", "Floor", "Flour", "Flower",
            "Flush", "Fly", "Fold", "Follow", "Food", "Fool", "Foot", "Force", "Forest", "Forget", "Form", "Fort", "Forty", "Forward", "Fowl", "Frame", "Frank",
            "Free", "Freeze", "Fresh", "Friend", "Fright", "Frolic", "Front", "Fruit", "Fry", "Full", "Funny", "Gain"};

    private Random random = new Random();
    private String currentWord;
    private int score = 0;
    private int bonusCounter = 0; // To count consecutive correct answers
    private int timeLeft = 180; // 180 seconds = 3 minutes
    private CountDownTimer timer;

    private TextView textViewScrambledWord;
    private EditText editTextGuess;
    private Button buttonSubmit;
    private Button buttonRestart; // New restart button
    private Button buttonBackToMainMenu; // Button to go back to main menu
    private TextView textViewScore;
    private TextView textViewTimer;
    private LottieAnimationView animationViewCorrect;
    private LottieAnimationView animationViewWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScrambledWord = findViewById(R.id.textViewScrambledWord);
        editTextGuess = findViewById(R.id.editTextGuess);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonRestart = findViewById(R.id.buttonRestart); // Initialize restart button
        buttonBackToMainMenu = findViewById(R.id.buttonBackToMainMenu); // Initialize back to main menu button
        textViewScore = findViewById(R.id.textViewScore);
        textViewTimer = findViewById(R.id.textViewTimer);
        animationViewCorrect = findViewById(R.id.animation_view_correct);
//        animationViewWrong = findViewById(R.id.animation_view_wrong);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        buttonBackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainMenu();
            }
        });

        chooseRandomWord();
        startTimer();
    }

    private void chooseRandomWord() {
        currentWord = words[random.nextInt(words.length)];
        String scrambledWord = scrambleWord(currentWord);
        textViewScrambledWord.setText(scrambledWord);
    }

    private String scrambleWord(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    private void checkGuess() {
        if (!buttonSubmit.isEnabled()) {
            return; // If the button is disabled, do nothing
        }

        String guess = editTextGuess.getText().toString().toLowerCase();
        if (guess.equals(currentWord.toLowerCase())) {
            // Handle correct guess
            score += 5; // Award 5 points
            textViewScore.setText("Score: " + score);
            textViewScrambledWord.setText("Correct!");
            bonusCounter++;
            if (bonusCounter == 3) {
                bonusCounter = 0; // Reset bonus counter
                timeLeft += 20; // Add bonus time of 20 seconds
                startTimer(); // Restart timer with updated time
            }
            chooseRandomWord(); // Choose a new word
            animationViewCorrect.setVisibility(View.VISIBLE);
            animationViewCorrect.setAlpha(1f);
            animationViewCorrect.playAnimation();
            int animationDuration = 2000; // 2 seconds in milliseconds

            new Handler().postDelayed(() -> fadeOutAnimation(animationViewCorrect), animationDuration);
        } else {
            // Handle incorrect guess
            if (score > 0) {
                score = Math.max(0, score - 5); // Deduct 5 points, but keep it non-negative
                textViewScore.setText("Score: " + score);
            } else {
                // Deduct 10 seconds when score equals 0
                timeLeft -= 10;
                startTimer(); // Update timer display
            }
        }
        chooseRandomWord();
        editTextGuess.getText().clear();
    }

    private void fadeOutAnimation(LottieAnimationView animationView) {
        animationView.animate().alpha(0f).setDuration(500).withEndAction(() -> animationView.pauseAnimation());
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel(); // Cancel previous timer
        }

        buttonSubmit.setEnabled(true); // Enable the submit button
        timer = new CountDownTimer(timeLeft * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = (int) millisUntilFinished / 1000;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Timer finished, handle accordingly (e.g., game over)
                textViewTimer.setText("Time's up!");
                buttonSubmit.setEnabled(false); // Disable the submit button
                showResultDialog();
            }
        }.start();
    }

    private void updateTimer() {
        textViewTimer.setText("Time: " + timeLeft + "s");
    }

    private void showResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_game_result, null);
        builder.setView(dialogView);

        TextView textViewResult = dialogView.findViewById(R.id.textViewResult);
        Button buttonRestart = dialogView.findViewById(R.id.buttonRestart);
        Button buttonExit = dialogView.findViewById(R.id.buttonExit);

        textViewResult.setText("Your Score: " + score);

        final AlertDialog dialog = builder.create();
        dialog.show();

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                restartGame();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
    }

    private void restartGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to restart the game?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Reset score, bonus counter, and time left
                        score = 0;
                        bonusCounter = 0;
                        timeLeft = 180;

                        // Update UI components
                        textViewScore.setText("Score: " + score);
                        textViewTimer.setText("Time: " + timeLeft + "s");
                        editTextGuess.getText().clear();
                        buttonSubmit.setEnabled(true); // Enable the submit button

                        // Restart the timer
                        startTimer();

                        // Choose a new word
                        chooseRandomWord();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void backToMainMenu() {
        Intent intent = new Intent(MainActivity.this, MyActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the timer to avoid memory leaks
        if (timer != null) {
            timer.cancel();
        }
    }
}
