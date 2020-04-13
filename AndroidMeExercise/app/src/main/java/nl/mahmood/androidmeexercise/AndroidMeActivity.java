package nl.mahmood.androidmeexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import nl.mahmood.androidmeexercise.data.AndroidImageAssets;

// TODO (2) CREATE THIS ACTIVITY
// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        /**
         * 18 Only create new fragments when there is no previously saved state
         */
        // Only create new fragments when there is no previously saved state
        if (savedInstanceState == null) {
                // Retrieve list index values that were sent through an intent; use them to display the desired Android-Me body part image
                    // Use setListindex(int index) to set the list index for all BodyPartFragments
            /**
             * 7
             */
            // Create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();
            /**
             * 8
             */
            // Set the list of image id's for the head fragment and set the position to the second image in the list
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            /**
             * 35 set to comment this line
             */
            //headFragment.setListIndex(1);
            /**
             * 36
             */
            // Get the correct index to access in the array of head images from the intent
            // Set the default value to 0
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            headFragment.setListIndex(headIndex);
            /**
             * 9
             */
            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();
            /**
             * 10
             */
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();
            /**
             * 11
             */
            // Create and display the body and leg BodyPartFragments
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            /**
             * 37 insert this 2 lines here between step 11
             */
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyFragment.setListIndex(bodyIndex);
            /**
             * 11
             */
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            /**
             * 38 insert this 2 lines here between step 11
             */
            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legFragment.setListIndex(legIndex);
            /**
             * 11
             */
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
