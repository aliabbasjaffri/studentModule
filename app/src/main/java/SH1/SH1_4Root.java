package SH1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studentmodule.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SH1_4Root.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SH1_4Root#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH1_4Root extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView trialClasses;
    TextView regularClasses;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1_4Root.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1_4Root newInstance(String param1, String param2) {
        SH1_4Root fragment = new SH1_4Root();
        Bundle args = new Bundle();
        //args.putString(ClassType, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1_4Root() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh1_4_root, container, false);

        trialClasses = (TextView) view.findViewById(R.id.sh1_4RootTrialClassAvailability);
        regularClasses = (TextView) view.findViewById(R.id.sh1_4RootRegularClassAvailability);

        return view;
    }

}
