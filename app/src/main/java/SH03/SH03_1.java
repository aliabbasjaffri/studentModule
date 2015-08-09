package SH03;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.studentmodule.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH03_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH03_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button playButton;
    private Button noButton;
    private Button yesButton;
    private MediaPlayer mPlayer;
    Boolean player = false;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH03_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH03_1 newInstance(String param1, String param2) {
        SH03_1 fragment = new SH03_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH03_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mPlayer = MediaPlayer.create( getActivity() , Uri.parse( Environment.getExternalStorageDirectory().getAbsolutePath() + "/mt_recording.3gpp" ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh03_1, container, false);

        playButton = (Button) view.findViewById(R.id.sh03_1PlayButton);
        noButton = (Button) view.findViewById(R.id.sh03_1NoButton);
        yesButton = (Button) view.findViewById(R.id.sh03_1YesButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = !player;

                if (!player)
                {
                    mPlayer.reset();
                    playButton.setBackgroundResource(R.drawable.playbtn_white);
                }
                else
                {
                    mPlayer = MediaPlayer.create(getActivity(), Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/mt_recording.3gpp"));
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        public void onCompletion(MediaPlayer mp) {
                            mPlayer.reset();
                            playButton.setBackgroundResource(R.drawable.playbtn_white);
                        }
                    });
                    mPlayer.start();
                    playButton.setBackgroundResource(R.drawable.pause_icon);
                }
            }
        });



        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        final Fragment fragment = new SH03_2();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });



        return view;
    }


}
