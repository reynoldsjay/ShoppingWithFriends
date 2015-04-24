package com.generict.shoppingwithfriends;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Tutorial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tutorial extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String POSITION = "position";
    private int position;
    private static final String TAG = "TutorialFragment";

    String[] TEXTS = {
            "Add any friend as your user simply by tapping on their name",
            "Add items to your wishlist so you can be if a sales report matches it",
            "Post new sales for your friends to check out"};

    int[] IMAGES = {
            R.drawable.tutorial1,
            R.drawable.tutorial2,
            R.drawable.tutorial3, };

    TextView mTextView;
    ImageView mImageView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param position Parameter position.
     * @return A new instance of fragment TutorialFragment.
     */
    public static Tutorial newInstance(int position) {
        Tutorial fragment = new Tutorial();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);
        mTextView = (TextView)view.findViewById(R.id.tutorial_content);
        mTextView.setText(TEXTS[position]);
        mImageView = (ImageView) view.findViewById(R.id.tutorial_image);
        mImageView.setImageResource(IMAGES[position]);
        try {
            AnimationDrawable tutorialAnimation = (AnimationDrawable) mImageView.getDrawable();
            tutorialAnimation.start();
        } catch(OutOfMemoryError e) {
            // do nothing.
        } catch (ClassCastException ex) {
            // do nothing.
        }
        return view;
    }

}
