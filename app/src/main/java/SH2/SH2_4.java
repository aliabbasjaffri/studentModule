package SH2;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.studentmodule.R;
import com.studentmodule.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH2_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH2_4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String[] levels;
    private TextView englishLevel;
    private Spinner englishLevelSpinner;
    private Button modifyLevelButton;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH2_4.
     */
    // TODO: Rename and change types and number of parameters
    public static SH2_4 newInstance(String param1, String param2) {
        SH2_4 fragment = new SH2_4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH2_4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        levels = getResources().getStringArray(R.array.spinnerItems);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_sh2_4, container, false);

        ViewPagerAdapter.studentPortalActivity.setToolbar("나의 레벨" , true);

        englishLevel = (TextView) view.findViewById(R.id.sh2_4EnglishLevelTextView);
        englishLevel.setText(sharedPreferences.getString("EnglishLevel", ""));

        final String checker = sharedPreferences.getString("EnglishLevel", "");
        int pos = 0;
        for(int i = 0; i < levels.length; i++)
        {
            if( checker.equals(levels[i]) )
                pos = i;
        }

        ArrayAdapter my_Adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinneritem , levels );
        englishLevelSpinner = (Spinner) view.findViewById(R.id.sh2_4EnglishLevelSpinner);
        englishLevelSpinner.setAdapter(my_Adapter);
        englishLevelSpinner.setSelection(pos);

        modifyLevelButton = (Button) view.findViewById(R.id.sh2_4ModifyLevelButton);
        modifyLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                modifyLevelButton.setVisibility(v.INVISIBLE);
                englishLevelSpinner.setVisibility(v.VISIBLE);
            }
        });

        englishLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if( !checker.equals(levels[position]) )
                {
                    Toast.makeText(getActivity() , "Position " + position  , Toast.LENGTH_SHORT).show();

                    englishLevel.setText(levels[position]);
                    editor.putString("EnglishLevel", levels[position]);
                    editor.commit();

                    modifyLevelButton.setVisibility(view.VISIBLE);
                    englishLevelSpinner.setVisibility(view.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        return view;
    }
}
