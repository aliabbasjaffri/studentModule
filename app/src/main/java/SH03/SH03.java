package SH03;


import android.media.MediaRecorder;
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
 * Use the {@link SH03.newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH03 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private MediaRecorder myRecorder;
    private boolean recording = false;
    private String outputFile = null;

    private Button recordButton;
    private Button confirmButton;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH03.
     */
    // TODO: Rename and change types and number of parameters
    public static SH03 newInstance()
    {
        SH03 fragment = new SH03();
        return fragment;
    }

    public SH03() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        outputFile = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/mt_recording.3gpp";


    }

    void initializeMediaRecorder()
    {
        myRecorder = new MediaRecorder( );
        myRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myRecorder.setOutputFile(outputFile);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        initializeMediaRecorder();
    }

    @Override
    public void onPause()
    {
        super.onPause();

        initializeMediaRecorder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh03, container, false);

        recordButton = (Button) view.findViewById(R.id.sh03RecordButton);
        confirmButton = (Button) view.findViewById(R.id.sh03ConfirmButton);
        confirmButton.setEnabled(false);

        initializeMediaRecorder();

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recording = !recording;

                if (recording) {
                    start();
                    confirmButton.setBackgroundResource(R.drawable.circular_button_pink);
                    confirmButton.setTextColor(getResources().getColor(R.color.white));
                    confirmButton.setEnabled(false);

                    recordButton.setText("STOP");
                    recordButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopbtn, 0, 0, 0);

                } else {
                    stop();
                    confirmButton.setBackgroundResource(R.drawable.circular_button_purple);
                    confirmButton.setTextColor(getResources().getColor(R.color.black));
                    confirmButton.setEnabled(true);

                    recordButton.setText("RECORD");
                    recordButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.playbtn, 0, 0, 0);
                }
            }
        });

        final Fragment fragment = new SH03_1();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        return view;
    }


    public void start()
    {
        try
        {
            initializeMediaRecorder();
            myRecorder.prepare();
            myRecorder.start();

        } catch (IllegalStateException e) {
            // start:it is called before prepare()
            // prepare: it is called after start() or before setOutputFormat()
            e.printStackTrace();
        } catch (IOException e) {
            // prepare() fails
            e.printStackTrace();
        }
    }

    public void stop()
    {
        try
        {
            myRecorder.stop();
            myRecorder.release();
            myRecorder  = null;

        } catch (IllegalStateException e) {
            //  it is called before start()
            e.printStackTrace();
        } catch (RuntimeException e) {
            // no valid audio/video data has been received
            e.printStackTrace();
        }
    }

}
