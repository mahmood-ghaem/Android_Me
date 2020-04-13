package nl.mahmood.androidmeexercise;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

//TODO (8) CREATE THIS FILE EXTENDS 'BaseAdapter'

/**
 * 20 override all methods
 */
// Custom adapter class that displays a list of Android-Me images in a GridView
public class MasterListAdapter extends BaseAdapter
{
    /**
     * 21
     */
    // Keeps track of the context and list of images to display
    private Context mContext;
    private List<Integer> mImageIds;

    /**
     * 22 create constructor
     * Constructor method
     * @param imageIds The list of images to display
     */
    public MasterListAdapter(Context context, List<Integer> imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }

    // This 3 methods getCount(), getItem(int position), getItemId(int position)
    // Returns the number of items the adapter will display
    @Override
    public int getCount()
    {
        /**
         * 48 change return value
         */
        return mImageIds.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    // Creates a new ImageView for each item referenced by the adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        /**
         * 23 write body for this method (this step is easy so I put all these line in one step)
         */
        ImageView imageView;
        if (convertView == null) {
            // If the view is not recycled, this creates a new ImageView to hold an image
            imageView = new ImageView(mContext);
            // Define the layout parameters
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        // Set the image resource and return the newly created ImageView
        imageView.setImageResource(mImageIds.get(position));
        return imageView;
    }
}
