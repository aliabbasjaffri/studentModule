package SH2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.studentmodule.R;
import com.studentmodule.ViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH2.
     */
    // TODO: Rename and change types and number of parameters
    public static SH2 newInstance(String param1, String param2) {
        SH2 fragment = new SH2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh2, container, false);

        ViewPagerAdapter.studentPortalActivity.setToolbar("마이페이지", false);

        final Fragment myPointFragment = new SH2_1();
        final Fragment myClassFragment = new SH2_2();
        final Fragment myTutorFragment = new SH2_3();
        final Fragment myLevelFragment = new SH2_4();

        Button myPoint = (Button) view.findViewById(R.id.sh2MyPointButton);
        myPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, myPointFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        Button myClass = (Button) view.findViewById(R.id.sh2MyClassButton);
        myClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, myClassFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        Button myTutor = (Button) view.findViewById(R.id.sh2MyTutorButton);
        myTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, myTutorFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        Button myLevel = (Button) view.findViewById(R.id.sh2MyLevelButton);
        myLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, myLevelFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ViewPagerAdapter.studentPortalActivity.setToolbar("마이페이지", false);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        ViewPagerAdapter.studentPortalActivity.setToolbar("마이페이지", false);
    }
}
