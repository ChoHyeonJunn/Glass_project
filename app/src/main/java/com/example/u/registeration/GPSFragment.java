package com.example.u.registeration;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;


public class GPSFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener{

    private static final int REQUEST_CODE_PERMISSIONS = 1000;

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    LocationManager manager;
    Marker selectedMarker;
    TextView building_marker;


    final static double mLatitude = 37.451077;  //기본위치 학교 위도
    final static double mLongitude = 127.128644;    //기본위치 학교 경도

    private FusedLocationProviderClient mFusedLocationClient;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GPSFragment() {
    }

    public static GPSFragment newInstance(String param1, String param2) {
        GPSFragment fragment = new GPSFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    public class MarkerItem {
        double lat;
        double lon;
        String buildingname;

        public MarkerItem(double lat, double lon, String buildingname){
            this.lat = lat;
            this.lon = lon;
            this.buildingname = buildingname;
        }
        public double getLat() {
            return lat;
        }
        public void setLat(double lat) {
            this.lat = lat;
        }
        public double getLon() {
            return lon;
        }
        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getBuildingname() {
            return buildingname;
        }

        public void setBuildingname(String buildingname) {
            this.buildingname = buildingname;
        }
    }
    ArrayList<MarkerItem> buildingList = new ArrayList<>(  );
    public void getBuildingMarkerItems(){


        buildingList.add(new MarkerItem( 37.450992, 127.127119, "IT대학"  ));
        buildingList.add(new MarkerItem( 37.450564, 127.129896, "가천관"  ));
        buildingList.add(new MarkerItem( 37.451540, 127.127940, "공과대학1"  ));
        buildingList.add(new MarkerItem( 37.449265, 127.128474, "공과대학2"  ));
        buildingList.add(new MarkerItem( 37.451803, 127.131671, "교육대학원"  ));
        buildingList.add(new MarkerItem( 37.451905, 127.127154, "글로벌센터"  ));
        buildingList.add(new MarkerItem( 37.451173, 127.129808, "바이오나노대학"  ));
        buildingList.add(new MarkerItem( 37.449836, 127.128086, "바이오나노연구원"  ));
        buildingList.add(new MarkerItem( 37.449539, 127.127178, "비전타워"  ));
        buildingList.add(new MarkerItem( 37.449466, 127.129572, "산학협력관"  ));
        buildingList.add(new MarkerItem( 37.452247, 127.128732, "예술대학1"  ));
        buildingList.add(new MarkerItem( 37.451662, 127.129642, "예술대학2"  ));
        buildingList.add(new MarkerItem( 37.450000, 127.128641, "한의과대학"  ));


        for(MarkerItem markerItem : buildingList){
            addMarker(markerItem, false);
        }
    }

    private void setCustomMarker(){
        mView = LayoutInflater.from( getContext() ).inflate( R.layout.fragment_gps, null );
        building_marker = (TextView) mView.findViewById( R.id.building_marker );
    }

    private Marker addMarker(MarkerItem markerItem, boolean isSelectedMarker) {
        LatLng position = new LatLng( markerItem.getLat(), markerItem.getLon() );
        String buildingname = markerItem.getBuildingname();
        building_marker.setText( buildingname );

        if (isSelectedMarker) {
            building_marker.setBackgroundResource( R.drawable.patch1 );
            building_marker.setTextColor( Color.WHITE );
        } else {
            building_marker.setBackgroundResource( R.drawable.patch2 );
            building_marker.setTextColor( Color.BLACK );
        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(buildingname);
        markerOptions.position( position );
        markerOptions.icon( BitmapDescriptorFactory.fromBitmap( createDrawableFromView(getContext(), mView) ) );

        return mGoogleMap.addMarker( markerOptions );
    }

    private Bitmap createDrawableFromView(Context context, View view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics( displayMetrics );
        view.setLayoutParams( new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
        view.measure( displayMetrics.widthPixels, displayMetrics.heightPixels );
        view.layout( 0,0, displayMetrics.widthPixels, displayMetrics.heightPixels );
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap( view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888 );

        Canvas canvas = new Canvas(bitmap);
        view.draw( canvas );

        return bitmap;
    }

    private Marker addMarker(Marker marker, boolean isSelectedMarker){
        double lat = marker.getPosition().latitude;
        double lon = marker.getPosition().longitude;
        String buildingname = marker.getTitle();
        MarkerItem temp = new MarkerItem( lat, lon, buildingname );
        return addMarker( temp, isSelectedMarker );
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        CameraUpdate center = CameraUpdateFactory.newLatLng( marker.getPosition() );
        mGoogleMap.animateCamera( center );
        changeSelectedMarker(marker);


        for(int i=0; i<buildingList.size(); i++) {
            if (marker.getTitle().equals(buildingList.get(i).getBuildingname())) {
                GPSBottomSheetDialog gpsBottomSheetDialog = GPSBottomSheetDialog.getInstance();
                gpsBottomSheetDialog.show(getFragmentManager(), "GPS검색");
            }
        }

        return true;
    }

    private void changeSelectedMarker(Marker marker){
        if(selectedMarker != null){
            addMarker( selectedMarker, false );
            selectedMarker.remove();
        }

        if(marker != null) {
            selectedMarker = addMarker( marker, true );
            marker.remove();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        changeSelectedMarker( null );
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        googleMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );



        LatLng position = new LatLng( mLatitude, mLongitude );
        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( position, 17 ) );  //기본위치

        getDeviceLocation();
        mGoogleMap.setOnMarkerClickListener( this );
        mGoogleMap.setOnMapClickListener( this );
        setCustomMarker();
        getBuildingMarkerItems();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }


    //Button mapTest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        mView = inflater.inflate( R.layout.fragment_gps, container, false );
        mMapView = mView.findViewById( R.id.map );
        mMapView.onCreate( savedInstanceState );
        mMapView.onResume();
        mMapView.getMapAsync( this );

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient( getContext() );

        return mView;
    }

    private void getDeviceLocation(){
        if (ActivityCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( getActivity(),
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PERMISSIONS);
            return;

        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener( getActivity(), new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {

                if(location != null){
                    LatLng myLocation = new LatLng( location.getLatitude(), location.getLongitude() );

                    mGoogleMap.moveCamera( CameraUpdateFactory.newLatLng( myLocation ) );
                    mGoogleMap.animateCamera( CameraUpdateFactory.zoomTo(17) );

                }
            }
        } );

        mGoogleMap.setMyLocationEnabled( true );
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        mMapView = (MapView) mView.findViewById( R.id.map );
        if (mMapView != null) {
            mMapView.onCreate( null );
            mMapView.onResume();
            mMapView.getMapAsync( this );
            }
    }
    /**
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }
    **/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu( menu, inflater );
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState( outState );
        mMapView.onSaveInstanceState( outState );
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        switch (requestCode){
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "권한 체크 거부 됨", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
