package SH1;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.studentmodule.R;
import java.io.IOException;
import SH4.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SH1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SH1#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SH1 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String tutorNameParam = "param1";

    // TODO: Rename and change types of parameters
    private String mtutorName;

    public static String profile_pic_path;

    public static Drawable original_dp;
    public static Drawable blurred_dp;

    public Bitmap bitmap_original_dp;
    public static Bitmap bitmap_blurred_dp;

    LinearLayout blurredBackground;
    LinearLayout dpContainer;
    LinearLayout profilePicture;
    ImageView profilePictureBadge;
    ImageView countryPicture;
    TextView profileName;
    TextView profileDetail;
    TextView universityName;
    Button video;
    Button audio;
    Button button3;
    Button button4;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1 newInstance(String param1)
    {
        SH1 fragment = new SH1();
        Bundle args = new Bundle();
        args.putString(tutorNameParam, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mtutorName = getArguments().getString(tutorNameParam);
        }

        bitmap_original_dp = null;
        bitmap_blurred_dp = null;
        profile_pic_path = null;
        original_dp = null;
        blurred_dp = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh1, container, false);

        blurredBackground = (LinearLayout) view.findViewById(R.id.sh1TopContainer);

        dpContainer = (LinearLayout) view.findViewById(R.id.sh1DisplayImageContainer);
        profilePicture = (LinearLayout) view.findViewById(R.id.sh1ProfileImage);

        profilePictureBadge = (ImageView) view.findViewById(R.id.sh1ProfileBadge);
        countryPicture = (ImageView) view.findViewById(R.id.sh1ProfileCountry);

        profileName = (TextView) view.findViewById(R.id.sh1ProfileName);
        profileDetail = (TextView) view.findViewById(R.id.sh1PersonalDetails);
        universityName = (TextView) view.findViewById(R.id.sh1UniversityName);

        profileName.setText(mtutorName);

        video = (Button) view.findViewById(R.id.sh1VideoButton);
        audio = (Button) view.findViewById(R.id.sh1AudioButton);
        button3 = (Button) view.findViewById(R.id.sh1Button3);
        button4 = (Button) view.findViewById(R.id.sh1Button4);

        bitmap_original_dp = BitmapFactory.decodeResource(getResources(), R.drawable.jane);


        ViewTreeObserver vto = dpContainer.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {

                if (original_dp == null) {
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap_original_dp, dpContainer.getWidth(), dpContainer.getHeight(), false);
                    Drawable drawable_dp = new BitmapDrawable(getResources(), scaledBitmap);
                    original_dp = drawable_dp;
                }

                //if (profile_pic_path != null && !profile_pic_path.isEmpty()) {
                    /*

                    ExifInterface ei = null;
                    try
                    {
                        ei = new ExifInterface(profile_pic_path);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                    */
                    profilePicture.setVisibility(View.VISIBLE);

                    int sdk = android.os.Build.VERSION.SDK_INT;
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        profilePicture.setBackgroundDrawable(original_dp);
                    } else {
                        profilePicture.setBackground(original_dp);
                    }

                    ViewTreeObserver obs = dpContainer.getViewTreeObserver();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    {
                        obs.removeOnGlobalLayoutListener(this);
                    }
                    else
                    {
                        obs.removeGlobalOnLayoutListener(this);
                    }
                    if (blurred_dp == null)
                    {
                        Bitmap blurredBitmap = Bitmap.createScaledBitmap(bitmap_original_dp, 15, 15, true);

                        Paint paint = new Paint( Paint.FILTER_BITMAP_FLAG );
                        paint.setAntiAlias(true);
                        paint.setFilterBitmap(true);
                        paint.setDither(true);

                        Canvas canvas = new Canvas();
                        canvas.drawBitmap(blurredBitmap, blurredBackground.getWidth(), blurredBackground.getHeight(), paint);

                        Drawable blurredDrawable = new BitmapDrawable(getResources(), blurredBitmap);
                        blurred_dp = blurredDrawable;
                    }

                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        blurredBackground.setBackgroundDrawable(blurred_dp);
                    } else {
                        blurredBackground.setBackground(blurred_dp);
                    }
                //}

            }
        });

        //blurredBackground.setBackgroundResource(R.drawable.jane);
        //profilePicture.setBackgroundResource(R.drawable.jane);
        profilePictureBadge.setBackgroundResource(R.drawable.tutor_profile_badge);
        countryPicture.setBackgroundResource(R.drawable.country);

        final Fragment fragment = new SH1_5();

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.teacherProfile, SH4.newInstance() ).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.teacherProfile, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        return view;
    }
}
