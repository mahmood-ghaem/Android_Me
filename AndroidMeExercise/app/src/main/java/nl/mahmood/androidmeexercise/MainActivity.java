package nl.mahmood.androidmeexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import nl.mahmood.androidmeexercise.data.AndroidImageAssets;

/**
 * Pre Requirements
 * 1 - create dimens.xml in value folder and in values-w820dp folder
 * 2 - copy all images to drawable folder
 * do TODO (1) : create 'BodyPartFragment' class
 * do TODO (2) : create 'AndroidMeActivity' activity
 * do TODO (3) : set 'AndroidMeActivity' as start activity
 * do TODO (4) : create activity_android_me.xml
 * do TODO (5) : create fragment_body_part.xml
 * do TODO (6) : create AndroidImageAssets class in data package and write all code in class body (that's easy)
 * do steps 0 to 6 in BodyPartFragment class
 * do steps 7 to 11 in AndroidMeActivity Activity
 * now you can run project, in AndroidMeActivity appears 3 pictures head and body and leg but nothing change
 * do steps 12 to 19 in BodyPartFragment class
 * you can run project, images change with click and save the state
 * do TODO (7) : create fragment_master_list.xml
 * do TODO (8) : create MasterListAdapter class
 * do TODO (9) : create MasterListFragment class
 * do steps 20 to 23 in MasterListAdapter class
 * do steps 24 to 31 in MasterListFragment class
 * do steps 32 to 34 in MainActivity class
 * do TODO (10) : change the root element to Fragment in activity_main.xml
 * do TODO (11) : set 'MainActivity' as start activity
 * do steps 34 to 38 in AndroidMeActivity class
 * do TODO (12) : change elements in fragment_master_list.xml
 * do steps 39 to 43 in  MainActivity class
 * do TODO (13) : change elements in activity_main.xml
 * do steps 44 to 46 in MainActivity class
 * do step 47 in BodyPartFragment class
 * do step 48 in MasterListAdapter class
 */


/**
 * 32 implements MainActivity as MasterListFragment.OnImageClickListener interface
 */
// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener
{

    /**
     * 39 add 3 variables
     */
    // Variables to store the values for the list index of the selected images
    // The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    /**
     * 44 add a boolean variable
     */
    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 45 start
         */
        // Determine if you're creating a two-pane or single-pane display
        if (findViewById(R.id.android_me_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Change the GridView to space out the images more on tablet
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            // Getting rid of the "Next" button that appears on phones for launching a separate activity
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            if (savedInstanceState == null) {
                // In two-pane mode, add initial BodyPartFragments to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                // Add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // New body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // New leg fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }
        /**
         * 45 end
         */
    }

    /**
     * 33 implement method
     */
    // Define the behavior for onImageSelected
    @Override
    public void onImageSelected(int position)
    {
        /**
         * 34
         */
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        /**
         * 39
         */
        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position / 12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12 * bodyPartNumber;

        /**
         * 46
         */
        // Handle the two-pane case and replace existing fragments right when a new image is selected from the master list
        if (mTwoPane) {
            // Create two=pane interaction

            BodyPartFragment newFragment = new BodyPartFragment();

            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    // A head image has been clicked
                    // Give the correct image resources to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    // Replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            /**
             * 40
             */
            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
        }
        /**
         * 41
         */
        // Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        /**
         * 42
         */
        // Attach the Bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        /**
         * 43
         */
        // The "Next" button launches a new AndroidMeActivity
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(intent);
            }
        });

    }
}
