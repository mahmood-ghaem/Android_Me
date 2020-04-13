package nl.mahmood.androidmeexercise;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import nl.mahmood.androidmeexercise.data.AndroidImageAssets;

//TODO (1) CREATE THIS CLASS EXTENDS 'Fragment'

public class BodyPartFragment extends Fragment
{
    /**
     * 15
     */
    // Create final Strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";
    /**
     * 5
     */
    // Variables to store a list of image resources and the index of the image that this fragment displays
    private List<Integer> mImageIds;
    // Default value for 'mListIndex' is 0 because when we create an object of this class 'mListIndex' automatically initialize
    private int mListIndex;

    /**
     * 18
     */
    // Tag for logging
    private static final String TAG = "BodyPartFragment";

    /**
     * 0 create empty constructor and also override onCreateView method
     */
    // Mandatory empty constructor for the fragment manager to instantiate the fragment
    public BodyPartFragment()
    {
    }

    // Inflates the fragment layout file and sets the correct resource for the image to display
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        /**
         * 47
         */
        // Load the saved state (the list of images and list index) if there is one
        if(savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }
        /**
         * 1
         */
        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        /**
         * 14 Make imageView as final
         */
        /**
         * 2
         */
        // Get a reference to the ImageView in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        /**
         * 3 set imageView... to comment
         */
        // Set the image to the first in our list of head images
        //imageView.setImageResource(AndroidImageAssets.getHeads().get(mListIndex));
        /**
         * 17 create if statement
         */
        //If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
        if (mImageIds != null) {
            /**
             * 12
             */
            // Set the image resource to the list item at the stored index
            imageView.setImageResource(mImageIds.get(mListIndex));
            /**
             * 13
             */
            // Set a click listener on the image view
            imageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    // Increment position as long as the index remains <= the size of the image ids list
                    if (mListIndex < mImageIds.size() - 1) {
                        mListIndex++;
                    } else {
                        // The end of list has been reached, so return to beginning index
                        mListIndex = 0;
                    }
                    // Set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
            /**
             * 19 set else
             */
        } else {
            Log.v(TAG, "This fragment has a null list of image id's");
        }
        /**
         * 4
         */
        // Return the rootView
        return rootView;
    }

    /**
     * 6
     */
    // Setter methods for keeping track of the list images this fragment can display and which image
    // in the list is currently being displayed
    public void setImageIds(List<Integer> imageIds)
    {
        mImageIds = imageIds;
    }

    public void setListIndex(int index)
    {
        mListIndex = index;
    }

    /**
     * 16
     */
    // Save the current state of this fragment
    @Override
    public void onSaveInstanceState(Bundle currentState)
    {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
