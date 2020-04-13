package nl.mahmood.androidmeexercise;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import nl.mahmood.androidmeexercise.data.AndroidImageAssets;

// TODO (9) CREATE THIS FILE EXTENDS 'Fragment'

// This fragment displays all of the AndroidMe images in one large list
// The list appears as a grid of images
public class MasterListFragment extends Fragment
{
    /**
     * 27
     */
    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnImageClickListener
    {
        void onImageSelected(int position);
    }
    /**
     * 28
     */
    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    OnImageClickListener mCallback;

    /**
     * 29 override onAttach
     */
    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        /**
         * 30
         */
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }
    /**
     * 24 create constructor
     */
    // Mandatory empty constructor
    public MasterListFragment()
    {
    }

    /**
     * 25 override onCreateView
     */
    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        /**
         * 26 write body for this method (this step is easy so I put all these line in one step)
         */
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        // Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        /**
         * 31
         */
        // Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Trigger the callback method and pass in the position that was clicked
                mCallback.onImageSelected(position);
            }
        });
        // Return the root view
        return rootView;
    }
}
