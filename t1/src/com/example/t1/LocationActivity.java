package com.example.t1;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends Activity {

	@ViewInject(value = R.id.tv_location_detail)
	private TextView mtvLocationDetail;
	private LocationManager mLM;
	private Context mContext;
	private StringBuffer _sb = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		mContext = this;
		ViewUtils.inject(this);

		mLM = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);

		// all providers
		List<String> lstProviders = mLM.getAllProviders();
		_sb.append(String.format("Provider 共 %d 个。\n", lstProviders.size()));

		// Criteria criteria = getCriteria();
		//
		// String strBestProverider = mLM.getBestProvider(criteria, true);
		// print provider status
		for (String provider : lstProviders) {

			mLM.requestLocationUpdates(provider, 500, (float) 0.1,
					new LocationListener() {

						@Override
						public void onLocationChanged(Location location) {
							showLocationInfo();
						}

						@Override
						public void onProviderDisabled(String provider) {
							Toast.makeText(mContext, provider + " Disabled",
									Toast.LENGTH_SHORT).show();

							showLocationInfo();
						}

						@Override
						public void onProviderEnabled(String provider) {
							Toast.makeText(mContext, provider + " Enabled",
									Toast.LENGTH_SHORT).show();

							showLocationInfo();
						}

						@Override
						public void onStatusChanged(String provider,
								int status, Bundle extras) {
							Toast.makeText(mContext,
									provider + " 状态:" + status,
									Toast.LENGTH_SHORT).show();

							showLocationInfo();
						}
					});
			// if (!isEnabled)
			// continue;
			// _sb.append(showLocationInfo(mLM.getLastKnownLocation(provider)));
		}

	}

	@SuppressWarnings("unused")
	private Criteria getCriteria() {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(true);
		criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
		criteria.setBearingRequired(true);
		criteria.setCostAllowed(true);
		criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
		criteria.setPowerRequirement(Criteria.POWER_HIGH);

		return criteria;
	}

	private void showLocationInfo() {
		_sb = new StringBuffer();
		List<String> lstProviders = mLM.getAllProviders();
		_sb.append(String.format("Provider 共 %d 个。\n", lstProviders.size()));
		for (String provider : lstProviders) {
			boolean isEnabled = mLM.isProviderEnabled(provider);

			_sb.append(String.format("%s:%s\n", provider,
					(isEnabled ? "Enabled" : "Disabled")));

			_sb.append(showLocationInfo(mLM.getLastKnownLocation(provider)));
		}

		mtvLocationDetail.setText(_sb);
	}

	private String showLocationInfo(Location location) {
		if (location == null)
			return "Location is null.\n";

		StringBuffer sb = new StringBuffer();

		sb.append(String.format("%s -> %s -> %s。\n", "accuracy【精度】",
				(location.hasAccuracy() ? "has" : "no"), location.getAccuracy()));
		sb.append(String.format("%s -> %s -> %s。\n", "altitude【海拔】",
				(location.hasAltitude() ? "has" : "no"), location.getAltitude()));
		sb.append(String.format("%s -> %s -> %s。\n", "bearing【方位】",
				(location.hasBearing() ? "has" : "no"), location.getBearing()));
		// location.getElapsedRealtimeNanos();
		sb.append(String.format("%s -> %s -> %s。\n", "latitude【纬度】", "has",
				location.getLatitude()));
		sb.append(String.format("%s -> %s -> %s。\n", "longitude【经度】", "has",
				location.getLongitude()));
		// location.getProvider();
		sb.append(String.format("%s -> %s -> %s。\n", "speed【速度】", "has",
				location.getSpeed()));
		// location.getTime();
		return sb.toString();
	}
}
