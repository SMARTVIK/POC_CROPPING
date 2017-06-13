/*
 * Copyright (C) 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package saulmm.coordinatorexamples;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import static android.os.Build.VERSION_CODES.M;

public class MaterialUpConceptActivity extends AppCompatActivity
	implements AppBarLayout.OnOffsetChangedListener {

	private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
	private boolean mIsAvatarShown = true;
	Toolbar toolbar;
	private ImageView mProfileImage,cancel,right;
	private int mMaxScrollSize;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_material_up_concept);
		toolbar=(Toolbar)findViewById(R.id.toolbar);
		TabLayout tabLayout = (TabLayout) findViewById(R.id.materialup_tabs);
		ViewPager viewPager  = (ViewPager) findViewById(R.id.materialup_viewpager);
		AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.materialup_appbar);
		mProfileImage = (ImageView) findViewById(R.id.materialup_profile_image);



		appbarLayout.addOnOffsetChangedListener(this);
		mMaxScrollSize = appbarLayout.getTotalScrollRange();
		cancel=(ImageView)findViewById(R.id.cancel);
		right=(ImageView)findViewById(R.id.right);
		viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);
	}

	public static void start(Context c) {
		c.startActivity(new Intent(c, MaterialUpConceptActivity.class));
	}


	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

		int scrollValue= Math.abs(verticalOffset);

		int percentage=scrollValue* 100/appBarLayout.getTotalScrollRange();


		if (percentage==0||percentage<10)
		{
			setBackGround(R.color.full_transparent,R.color.white,R.color.white,(float)percentage/100);
		}
		else if (percentage>10 && percentage<15)
		{
			setBackGround(R.color.ten,R.color.ten_t, R.color.ten_t, (float) percentage / 100);
		}
		else if (percentage>15 && percentage<20)
		{
			setBackGround(R.color.fifteen,R.color.fifteen_t, R.color.fifteen_t, (float) percentage / 100);
		}
		else if (percentage>20 && percentage<25)
		{
			setBackGround(R.color.twenty,R.color.twenty_t, R.color.twenty_t, (float) percentage / 100);
		}
		else if (percentage>25 && percentage<30)
		{
			setBackGround(R.color.twentyfive,R.color.twentyfive_t, R.color.twentyfive_t, (float) percentage / 100);
		}
		else if (percentage>30 && percentage<35)
		{
			setBackGround(R.color.thirty,R.color.thirty_t, R.color.thirty_t, (float) percentage / 100);
		}
		else if (percentage>35 && percentage<40)
		{
			setBackGround(R.color.thirtyfive,R.color.thirtyfive_t, R.color.thirtyfive_t, (float) percentage / 100);
		}
		else if (percentage>40 && percentage<45)
		{
			setBackGround(R.color.forty,R.color.forty_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>45 && percentage<50)
		{
			setBackGround(R.color.fortyfive,R.color.fortyfive_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>50 && percentage<55)
		{
			setBackGround(R.color.fifty,R.color.fifty_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>55 && percentage<60)
		{
			setBackGround(R.color.fiftyfive,R.color.fiftyfive_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>60 && percentage<65)
		{
			setBackGround(R.color.sixty,R.color.sixty_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>65 && percentage<70)
		{
			setBackGround(R.color.sixtyfive,R.color.sixtyfive_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>70 && percentage<75)
		{
			setBackGround(R.color.seventy,R.color.seventy_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>75 && percentage<80)
		{
			setBackGround(R.color.seventyfive,R.color.seventyfive_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>80 && percentage<85)
		{
			setBackGround(R.color.eighty,R.color.eighty_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>85 && percentage<90)
		{
			setBackGround(R.color.eightyfive,R.color.eightyfive_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>90 && percentage<95)
		{
			setBackGround(R.color.ninety,R.color.ninety_t, R.color.tint_back, (float) percentage / 100);
		}
		else if (percentage>95)
		{
			setBackGround(R.color.back,R.color.black, R.color.tint_back, (float) percentage / 100);

		}
	}

	public void setBackGround(int color, int colortext, int tint, float alpha)
	{
		if (Build.VERSION.SDK_INT >= M) {
			//toolbar_title.setTextColor(getResources().getColor(colortext, null));

			toolbar.setBackground(getDrawable(color));
		} else {
			//toolbar_title.setTextColor(getResources().getColor(colortext));
			toolbar.setBackground(getResources().getDrawable(color));
		}
		cancel.setColorFilter(ContextCompat.getColor(this, tint));
		right.setColorFilter(ContextCompat.getColor(this, tint));
		if (alpha>0.95)
		{
			findViewById(R.id.name).setVisibility(View.VISIBLE);
		}
		else
		{
			findViewById(R.id.name).setVisibility(View.GONE);
		}
		/*rootView.findViewById(R.id.topLayout1).setAlpha((float) 1.0 - alpha);
		imageViewEdit.setAlpha((float) 1.0 - alpha);
		btnSetting.setAlpha((float) 1.0 - alpha);
		imageview_userImg.setAlpha((float) 1.0 - alpha);*/
	}


	private static class TabsAdapter extends FragmentPagerAdapter {
		private static final int TAB_COUNT = 2;

		TabsAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return TAB_COUNT;
		}

		@Override
		public Fragment getItem(int i) {
			return FakePageFragment.newInstance();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Tab " + String.valueOf(position);
		}
	}
}
