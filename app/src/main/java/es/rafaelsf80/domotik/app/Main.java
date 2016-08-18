package es.rafaelsf80.domotik.app;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Log;
import android.view.Window;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.sync.DomotikSyncAdapter;

public class Main extends AppCompatActivity {

    public static final String OPEN_WEATHER_MAP_API_KEY = "72349a95ba28a2319bec222ef811f744";
    public static final String TAG = Main.class.getSimpleName();

    // The authority for the sync adapter's content provider
    public static final String AUTHORITY = "com.rafaelsf80.d4w.retail.sync.D4WSyncAdapter";

    private boolean mTwoPane;

    public RecyclerView recyclerView;
    //public ControlAdapter controlAdapter;

    // Instead of Content Providers, we use Singleton classes for storage
    //public static ArrayList<Item> items = new ArrayList<Item>();
    //public static Config config = new Config();

    public ContentObserver mObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // enable window content transition
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set the transition
        Transition ts = new Explode();
        ts.setStartDelay(500);
        // set the duration
        ts.setDuration(500);
        getWindow().setEnterTransition(ts);
        // set an exit transition so it is activated when the current activity exits
        getWindow().setExitTransition(ts);
        //setContentView(R.layout.main);

        setContentView(R.layout.control);


        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO: CREATE LAYOUT FOR TABLETS
//        if (findViewById(R.id.weather_detail_container) != null) {
//            // The detail container view will be present only in the large-screen layouts
//            // (res/layout-sw600dp). If this view is present, then the activity should be
//            // in two-pane mode.
//            mTwoPane = true;
//            // In two-pane mode, show the detail view in this activity by
//            // adding or replacing the detail fragment using a
//            // fragment transaction.
//            if (savedInstanceState == null) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.weather_detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
//                        .commit();
//            }
//        } else {
//            mTwoPane = false;
//            getSupportActionBar().setElevation(0f);
//        }


//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setHasFixedSize(true);
//        controlAdapter = new ControlAdapter(this, this);//, items, config.colorScheme);
//        recyclerView.setAdapter( controlAdapter );
//        recyclerView.setItemAnimator(new DefaultItemAnimator());




//        List<SampleData> sampleData = getSampleData();
//        adapter.setSample2Data(sampleData);


        // Get Data
        //Comtrend comtrend = new Comtrend(this, adapter);
        //comtrend.start();

        //MainFragment mainFragment =  ((MainFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.fragment_main));
        //mainFragment.setUseTodayLayout(!mTwoPane);


        // Init sync adapter: 1) weather updates; 2) router updates
        DomotikSyncAdapter.initializeSyncAdapter(this);
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(DomotikSyncAdapter.getSyncAccount(this),
                getString(R.string.content_authority), bundle);


        // Even if we use a stub (dummy) content provider, we declare an observer
        // to talk to our sync adapter and receive updates
        mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            public void onChange(boolean selfChange) {
                Log.d(TAG, "ContentObserver on Change()");

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                SampleEnumMapAdapter adapter = new SampleEnumMapAdapter();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main.this));


                //actionBar.setTitle(config.appName);
                //actionBar.setSubtitle(config.subTitle);
//                Picasso.with(mActivity)
//                        .load(config.logo)
//                        .into(new Target() {
//                            @Override
//                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                                Drawable d = new BitmapDrawable(getResources(), bitmap);
//                                //actionBar.setIcon(d);
//                                //actionBar.setDisplayShowHomeEnabled(true);
//                                //actionBar.setDisplayHomeAsUpEnabled(true);
//                                //actionBar.setDisplayShowTitleEnabled(true);
//                            }
//
//                            @Override
//                            public void onBitmapFailed(Drawable errorDrawable) {
//                            }
//
//                            @Override
//                            public void onPrepareLoad(Drawable placeHolderDrawable) {
//                            }
//                        });
//
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setAdapter(new SimpleArrayAdapter(mActivity, mActivity, items, config.colorScheme));
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
        };

        getContentResolver().registerContentObserver(Uri.parse("content://rafa"), true, mObserver);
    }
}