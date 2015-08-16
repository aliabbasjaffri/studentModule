package SH1;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.studentmodule.R;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH1_4_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH1_4_2 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context context;
    View view;

    ListView requested_classes_list;
    String classTime = "Time is money";
    //CustomRequestedClassesListAdapter adapter;
    TextView no_list_text;
    ArrayList<SH1_4_1.DailyEvent> events = null;
    ArrayList<SH1_4_1.DailyEvent> eventsRequested = null;
    ArrayList<String> requestingStudentNames;
    ArrayList<String> requestingStudentSeqNumbers;
    ArrayList<String> studentNames_english;
    ArrayList<String> classTimings;
    ArrayList<String> studentSeqNumberArrayForTrialRequests;
    ProgressBar pb;
    int classType = SH1_4_1.TRIAL;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1_4_2.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1_4_2 newInstance(String param1, String param2) {
        SH1_4_2 fragment = new SH1_4_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1_4_2() {
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
        view = inflater.inflate(R.layout.fragment_sh1_4_2, container, false);

        context = getActivity();

        requestingStudentNames = new ArrayList<>();
        studentNames_english = new ArrayList<>();
        requestingStudentSeqNumbers = new ArrayList<>();
        classTimings = new ArrayList<>();
        eventsRequested = new ArrayList<>();
        Bundle bundle = this.getArguments();
        events = bundle.getParcelableArrayList("EventsList");
        studentSeqNumberArrayForTrialRequests = bundle.getStringArrayList("studentSeqNumberArrayForTrialRequests");
        classType = bundle.getInt("classType");

        pb = (ProgressBar) view.findViewById(R.id.requested_classes_loading);

        for(int i=0; i<events.size(); i++)
            if (events.get(i).stat == 20) {
                eventsRequested.add(events.get(i));
                requestingStudentSeqNumbers.add(studentSeqNumberArrayForTrialRequests.get(i));
                studentNames_english.add("Student's Name:");
                classTimings.add(events.get(i).classDate);
            }
        //getStudentNames();

        /*

        if(classType == CurrentAvailabilityFragment.REGULAR)
            ViewPagerAdapter.baseTutorActivity.setToolbar("Requested Regular Classes", true);
        else
            ViewPagerAdapter.baseTutorActivity.setToolbar("Requested Trial Classes", true);
        */

        return view;
    }

    public void assembleList()
    {
        no_list_text = (TextView) view.findViewById(R.id.no_list_text);
        requested_classes_list = (ListView) view.findViewById(R.id.requested_classes_list);
        requested_classes_list.setVisibility(View.VISIBLE);

        //adapter = new CustomRequestedClassesListAdapter(context, studentNames_english, requestingStudentNames);
        //requested_classes_list.setAdapter(adapter);


        /*
        requested_classes_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(context, "You've selected :\n " + studentNames_korean.get(position), Toast.LENGTH_LONG).show();
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                Fragment f = new TrialClassAcceptanceFragment();
                Bundle b = new Bundle();
                //b.putString("studentName_English", studentNames_english);
                b.putString("korean_name", requestingStudentNames.get(position).toString());
                b.putString("classTimings", classTimings.get(position).toString());
                b.putInt("position", position);
                b.putParcelable("event", eventsRequested.get(position));
                f.setArguments(b);
                trans.replace(R.id.root_frame, f);
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });
        */

        if(requested_classes_list!=null) {
            if(requestingStudentNames.size()==0){
                no_list_text.setVisibility(View.VISIBLE);
            }
            else {
                requested_classes_list.setVisibility(View.VISIBLE);
            }
        }

    }


}
