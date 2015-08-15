package SH2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.studentmodule.R;
import com.studentmodule.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH2_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH2_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView LV;
    private sh21ArrayAdapter adaptor;
    private ArrayList<sh2_1Data> items = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH2_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH2_1 newInstance(String param1, String param2) {
        SH2_1 fragment = new SH2_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH2_1() {
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
        View view = inflater.inflate(R.layout.fragment_sh2_1, container, false);

        clearAndAddToArray();

        ViewPagerAdapter.studentPortalActivity.setToolbar("마이 포인트" , true);

        adaptor = new sh21ArrayAdapter( getActivity() , R.layout.layout_sh2_1_custom_row , items );

        LV = (ListView) view.findViewById(R.id.sh2_1ListView);
        LV.setAdapter(adaptor);

        Button totalButton = (Button) view.findViewById(R.id.sh2_1TotalButton);
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        int totalValue = 100;

        String totalText = "총계 \t\t\t\t\t\t\t\t\t\t\t\t" + totalValue;

        totalButton.setText(totalText);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        clearAndAddToArray();
    }

    @Override
    public void onResume() {
        super.onResume();

        clearAndAddToArray();
    }

    void clearAndAddToArray()
    {
        if(!items.isEmpty())
            items.clear();

        items.add(new sh2_1Data("수강후기" , "bla bla" , "bla bla" ));
        items.add(new sh2_1Data("질문과 의견" , "bla bla" , "bla bla" ));
        items.add(new sh2_1Data("무료수업듣기" , "bla bla" , "bla bla" ));
        items.add(new sh2_1Data("수강후기" , "bla bla" , "bla bla" ));
        items.add(new sh2_1Data("5월" , "bla bla" , "bla bla" ));
    }
}
