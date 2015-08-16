package SH1;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.studentmodule.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH1_4_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH1_4_1 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // @private Private members

    public static final String CLASS_TYPE = "classType";
    public static final int TRIAL = 1 ;
    public static final int REGULAR = 2;
    public int classType = TRIAL;
    SH1_4_1 instance;
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;
    String[] dayName = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
    LinearLayout todayColumn= null;
    LinearLayout requestedClasses;
    LinearLayout timeTableContainer;
    ArrayList<LinearLayout> daysLayouts;
    TextView availabilityTableHeaderText;
    TextView seeRequestedClassesText;

    TextView noTimetableTextView;
    ProgressBar pb;
    Context context;
    LinearLayout.LayoutParams params;
    View view;
    ArrayList<DailyEvent> events = null;
    ArrayList<String> studentSeqNumberArrayForTrialRequests = null;
    AlertDialog.Builder alertBuilder;
    AlertDialog confirmationDialog;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1_4_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1_4_1 newInstance(String param1, String param2) {
        SH1_4_1 fragment = new SH1_4_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1_4_1()
    {
        // Required empty public constructor
        view = null;
    }

    private static Date[] getDaysOfWeek(Date refDate, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        Date[] daysOfWeek = new Date[7];
        for (int i = 0; i < 7; i++) {
            daysOfWeek[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysOfWeek;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh1_4_1, container, false);

        instance = this;
        context = getActivity();
        Bundle bundle = getArguments();

        classType = bundle.getInt(CLASS_TYPE, TRIAL);

        availabilityTableHeaderText = (TextView) view.findViewById(R.id.sh1_4_1AvailabilityTableHeader);

        daysLayouts = new ArrayList<>(7);
        daysLayouts.add((LinearLayout) view.findViewById(R.id.sh1_4_1MondayClassesLayout));
        daysLayouts.add((LinearLayout) view.findViewById(R.id.sh1_4_1TuesdayClassesLayout));
        daysLayouts.add((LinearLayout) view.findViewById(R.id.sh1_4_1WednesdayClassesLayout));
        daysLayouts.add((LinearLayout) view.findViewById(R.id.sh1_4_1ThursdayClassesLayout));
        daysLayouts.add((LinearLayout) view.findViewById(R.id.sh1_4_1FridayClassesLayout));
        daysLayouts.add((LinearLayout) view.findViewById(R.id.sh1_4_1SaturdayClassesLayout));
        daysLayouts.add((LinearLayout) view.findViewById(R.id.sh1_4_1SundayClassesLayout));

        requestedClasses = (LinearLayout) view.findViewById(R.id.sh1_4_1RequestedClasses);
        seeRequestedClassesText = (TextView) view.findViewById(R.id.sh1_4_1RequestedClassesTextView);
        noTimetableTextView = (TextView) view.findViewById(R.id.sh1_4_1NoTimetableText);
        timeTableContainer = (LinearLayout) view.findViewById(R.id.sh1_4_1TimetableContainer);

        pb = (ProgressBar) view.findViewById(R.id.sh1_4_1ClassesAvailabilityPb);

        if (classType == REGULAR)
        {
            seeRequestedClassesText.setText("Requested Regular Classes");
            availabilityTableHeaderText.setText("Regular Classes Availability");
        }
        else
        {
            seeRequestedClassesText.setText("Requested Trial Classes");
            availabilityTableHeaderText.setText("Trial Classes Availability");
        }

        requestedClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (events != null)
                {

                    if (classType == REGULAR)
                    {
                    }
                    else
                    {
                    }

                    /*
                    FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment fragment = new SeeRequestedClassesFragment();
                    Bundle bundle = new Bundle();

                    bundle.putParcelableArrayList("EventsList", events);
                    bundle.putStringArrayList("studentSeqNumberArrayForTrialRequests", studentSeqNumberArrayForTrialRequests);
                    bundle.putInt("classType", classType);
                    fragment.setArguments(bundle);

                    trans.replace(R.id.teacherProfile, fragment);
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                    */
                }
            }
        });

        /*
        if(classType == REGULAR)
            ViewPagerAdapter.studentPortalActivity.setToolbar("Current Availability (Regular Class)", true);
        else
            ViewPagerAdapter.studentPortalActivity.setToolbar("Current Availability (Trial Class)",true);
        */
        return view;
    }

    private TextView findDayLabelView(int day)
    {
        TextView dayLabel;

        if ( day == MONDAY)
            dayLabel = (TextView) view.findViewById(R.id.sh1_4_1MondayLabel);
        else if (day == TUESDAY)
            dayLabel = (TextView) view.findViewById(R.id.sh1_4_1TuesdayLabel);
        else if (day == WEDNESDAY)
            dayLabel = (TextView) view.findViewById(R.id.sh1_4_1WednesdayLabel);
        else if (day == THURSDAY)
            dayLabel = (TextView) view.findViewById(R.id.sh1_4_1ThursdayLabel);
        else if (day == FRIDAY)
            dayLabel = (TextView) view.findViewById(R.id.sh1_4_1FridayLabel);
        else if (day == SATURDAY)
            dayLabel = (TextView) view.findViewById(R.id.sh_1_4_1SaturdayLabel);
        else
            dayLabel = (TextView) view.findViewById(R.id.sh1_4_1SundayLabel);
        return dayLabel;
    }

    private boolean isToday(Calendar mCalendar, String today) {
        String todayName = mCalendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.US);
        return today.compareToIgnoreCase(todayName) == 0;
    }

    private void formatTodayColumn(int today) {
        Calendar mCalendar = Calendar.getInstance();
//        int today = mCalendar.get(Calendar.DAY_OF_WEEK);
//        if (today == 0)
//            today = 6;
//        else
//          today = today -1;
        if (isToday(mCalendar,dayName[today])){
            TextView dayLabel;
            todayColumn = daysLayouts.get(today);
            dayLabel = findDayLabelView(today);
            if(dayLabel != null)
                dayLabel.setTextColor(getResources().getColor(R.color.Red));
            todayColumn.setBackgroundColor(getResources().getColor(R.color.blue));
        }
    }

    private String getTimePostFix(int hour) {
        String timePostFix;
        if (hour >=12 && hour<=23)
            timePostFix = "PM";
        else
            timePostFix = "AM";
        return timePostFix;
    }

    public static int convertTo12HourFormat(int hour) {
        if(hour == 0) {
            hour = 12;
        }
        else if (hour>12)
        {
            hour = hour-12;
        }
        return hour;
    }

    /*
    public void currentAvailabilityDataFetchSuccess(JSONObject jObj)  {
        pb.setVisibility(View.GONE);
        timeTableContainer.setVisibility(View.VISIBLE);
        try {

            JSONArray seqArray = jObj.getJSONArray("seq");
            JSONArray dayNumberArray = jObj.getJSONArray("w_my");
            JSONArray hourArray = jObj.getJSONArray("h_my");
            JSONArray minuteArray = jObj.getJSONArray("m_my");

            JSONArray dayNumberArrayKorea = jObj.getJSONArray("w_korea");
            JSONArray hourArrayKorea = jObj.getJSONArray("h_korea");
            JSONArray minuteArrayKorea = jObj.getJSONArray("m_korea");

            JSONArray regDateArray = jObj.getJSONArray("reg_date");
            JSONArray classDurationArray = jObj.getJSONArray("class_duration");
            JSONArray classDateArray = jObj.getJSONArray("classes_date");

            if (dayNumberArray.length() == 0 && hourArray.length() == 0 && minuteArray.length() == 0) {
                pb.setVisibility(View.GONE);
                noTimetableTextView.setText("No Timetable found, Please Visit MonsterTutors.com");
                timeTableContainer.setVisibility(View.GONE);
                noTimetableTextView.setVisibility(View.VISIBLE);
                return;
            }
            else
            {
                events = null;
                events = new ArrayList<>();

                for ( int i = 0; i<dayNumberArray.length();i++)
                {
                    DailyEvent event;
                    int day = Integer.parseInt(dayNumberArray.get(i).toString())-1;
                    int hour = Integer.parseInt(hourArray.get(i).toString());
                    int minuteTemp = Integer.parseInt(minuteArray.get(i).toString());
                    String minute;

                    if ( minuteTemp == 0)
                        minute = "00";
                    else
                        minute = minuteTemp+"";

                    int dayKorea = Integer.parseInt(dayNumberArrayKorea.get(i).toString());
                    int hourKorea = Integer.parseInt(hourArrayKorea.get(i).toString());
                    int duration = Integer.parseInt(classDurationArray.get(i).toString());

                    String regDate = regDateArray.get(i).toString();
                    String classDate = classDateArray.get(i).toString();

                    int minuteTempKorea = Integer.parseInt(minuteArrayKorea.get(i).toString());
                    String minuteKorea;

                    if ( minuteTempKorea == 0)
                        minuteKorea = "00";
                    else
                        minuteKorea = minuteTemp+"";

                    int seq = Integer.parseInt(seqArray.get(i).toString());

                    String timePostFix,timePostFixKorea;
                    timePostFix = getTimePostFix(hour);
                    hour = convertTo12HourFormat(hour);
                    timePostFixKorea = getTimePostFix(hourKorea);
                    hourKorea = convertTo12HourFormat(hourKorea);
                    event = new DailyEvent(day,hour,minute,dayKorea,hourKorea,minuteKorea,regDate,duration,classDate,timePostFix,timePostFixKorea, 10,seq);
                    events.add(event);

                    makeEventAtDay(event);

                }
                for ( int d = MONDAY ; d<SUNDAY;d++)
                    formatTodayColumn(d);
            }
        } catch (JSONException e) {

            Log.e("JSON ERROR", "Came in Current availability success: Response : " + jObj.toString());
        }

        Log.e("Success","Came in Current availability success: Response : "+jObj.toString());
    }

    public void currentAvailabilityDataFetchSuccessTrial(JSONObject jObj){

        pb.setVisibility(View.GONE);
        timeTableContainer.setVisibility(View.VISIBLE);
        try {
            studentSeqNumberArrayForTrialRequests = new ArrayList<>();
            JSONArray titleArray = jObj.getJSONArray("title");
            JSONArray holidayArray = jObj.getJSONArray("holiday");
            JSONArray seqArray = jObj.getJSONArray("seq");
            JSONArray book_nameArray = jObj.getJSONArray("book_name");

            JSONArray book_urlArray = jObj.getJSONArray("book_url");
            JSONArray cptArray = jObj.getJSONArray("cpt");
            JSONArray stimeArray = jObj.getJSONArray("stime");

            JSONArray rtimeArray = jObj.getJSONArray("rtime");
            JSONArray memoArray = jObj.getJSONArray("memo");
            JSONArray statArray = jObj.getJSONArray("stat");

            JSONArray reg_dateArray = jObj.getJSONArray("reg_date");
            JSONArray student_seq_numberArray = jObj.getJSONArray("student_seq_number");
            JSONArray apply_dateArray = jObj.getJSONArray("apply_date");

            for(int i=0; i<student_seq_numberArray.length(); i++)
                studentSeqNumberArrayForTrialRequests.add(student_seq_numberArray.get(i).toString());

            if (stimeArray.length() == 0) {
                pb.setVisibility(View.GONE);
                noTimetableTextView.setText("No Timetable found, Please Visit MonsterTutors.com");
                noTimetableTextView.setVisibility(View.VISIBLE);
                return;
            }
            else
            {
                events = null;
                events = new ArrayList<>();

                for ( int i = 0; i<stimeArray.length();i++)
                {
                    DailyEvent event;
                    String trialTime = stimeArray.get(i).toString();
                    String classDate = holidayArray.get(i).toString();
                    int bookedStat = Integer.parseInt(statArray.get(i).toString());

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                    Date d = null;

                    try {
                        d = sdf.parse(trialTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(d);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minuteTemp = calendar.get(Calendar.MINUTE);
                    String minute;

                    if ( minuteTemp == 0)
                        minute = "00";
                    else
                        minute = minuteTemp+"";

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date trialClassDate = null;
                    try {
                        trialClassDate = formatter.parse(classDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    calendar = Calendar.getInstance();
                    calendar.setTime(trialClassDate);

                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    int duration = Integer.parseInt(rtimeArray.get(i).toString());
                    int seq = Integer.parseInt(seqArray.get(i).toString());

                    String regDate = reg_dateArray.get(i).toString();
                    String applyDate = apply_dateArray.get(i).toString();

                    String timePostFix,timePostFixKorea;
                    timePostFix = getTimePostFix(hour);
                    hour = convertTo12HourFormat(hour);
                    timePostFixKorea = "0";
                    int hourKorea = hour;
                    String minuteKorea = "00";
                    int dayKorea = day;
                    event = new DailyEvent(day,hour,minute,dayKorea,hourKorea,minuteKorea,regDate,duration,classDate,timePostFix,timePostFixKorea,bookedStat, seq);
                    events.add(event);

                    makeEventAtDay(event);

                }
                for ( int d = MONDAY ; d<SUNDAY;d++)
                    formatTodayColumn(d);
            }
        } catch (JSONException e) {

            Log.e("JSON ERROR", "Came in Current availability success: Response : " + jObj.toString());
        }

        Log.e("Success","Came in Current availability success: Response : "+jObj.toString());
    }

    */

    public void makeEventAtDay(final DailyEvent event)
    {
        final int day = event.dayNumber;
        String summarizedText = "Class at "+event.hour+":"+event.minute+" "+event.timePostFix;

        final TextView tv;
        final View inflatedLayout;
        final LayoutInflater inflater = LayoutInflater.from(context);

        inflatedLayout = inflater.inflate(R.layout.daily_event_layout, null, false);

        tv = (TextView) inflatedLayout.findViewById(R.id.daily_event_compact_view);
        tv.setText(summarizedText);
        if (event.stat == 20){
            tv.setBackgroundColor(getResources().getColor(R.color.tutor_tab_text_color));
        }
        inflatedLayout.setLayoutParams(params);
        inflatedLayout.setOnClickListener(new View.OnClickListener()
              {
                  @Override
                  public void onClick(View v)
                  {
                      View customTitle = inflater.inflate(R.layout.alertbox_title, null, false);

                      TextView titleTv = (TextView) customTitle.findViewById(R.id.alertboxTitleText);

                      customTitle.setBackgroundColor(getResources().getColor(R.color.tutor_content_background));
                      tv.setBackgroundColor(getResources().getColor(R.color.tutor_content_background));
                      tv.setTextColor(getResources().getColor(R.color.white));
                      titleTv.setText("Lecture Details");

                      View dialogContent = inflater.inflate(R.layout.daily_event_expanded, null, false);

                      TextView contentText = (TextView) dialogContent.findViewById(R.id.dailyEventText1);
                      TextView subText = (TextView) dialogContent.findViewById(R.id.dailyEventText2);
                      TextView priceText = (TextView) dialogContent.findViewById(R.id.dailyEventText3);

                      contentText.setText("Start Time: " + event.hour + ":" + event.minute + " " + event.timePostFix);
                      subText.setText("Duration: " + event.duration + " minutes.");
                      Button confirmBtn = (Button) dialogContent.findViewById(R.id.dailyEventConfirmDialogButton);

                      alertBuilder = new AlertDialog.Builder(context);
                      alertBuilder.setCustomTitle(customTitle).setIcon(R.drawable.monster).setView(dialogContent);

                      confirmationDialog = alertBuilder.create();
                      confirmBtn.setOnClickListener(new View.OnClickListener()
                      {
                          @Override
                          public void onClick(View v)
                          {
                              if (confirmationDialog.isShowing())
                                  confirmationDialog.dismiss();

                              if (event.stat == 20)
                                  tv.setBackgroundColor(getResources().getColor(R.color.tutor_tab_text_color));
                          }
                      });
                      confirmationDialog.show();
                  }
              }
        );
        daysLayouts.get(day).addView(inflatedLayout);
    }


    public class DailyEvent implements Parcelable {

        public int dayNumber;
        public int hour;
        public String minute;
        public int dayNumberKorea;
        public int hourKorea;
        public String minuteKorea;
        public String regDate;
        public int duration;
        public String classDate;
        public String timePostFix= "AM";
        public String timePostFixKorea ="AM";
        public int seq;
        public int stat = 10;


        public DailyEvent(int dayNumber, int hour, String minute, int dayNumberKorea, int hourKorea, String minuteKorea, String regDate, int duration, String classDate,String timePostFix,String timePostFixKorea, int stat, int seq) {

            this.dayNumber = dayNumber;
            this.hour = hour;
            this.minute = minute;
            this.dayNumberKorea = dayNumberKorea;
            this.hourKorea = hourKorea;
            this.minuteKorea = minuteKorea;
            this.regDate = regDate;
            this.duration = duration;
            this.classDate = classDate;
            this.timePostFix = timePostFix;
            this.timePostFixKorea = timePostFixKorea;
            this.stat = stat;
            this.seq = seq;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }

}
