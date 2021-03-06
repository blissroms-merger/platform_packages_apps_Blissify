package com.blissroms.blissify.fragments.animations;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.Settings;

import com.android.internal.util.bliss.AwesomeAnimationHelper;
import java.util.Arrays;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

/**
 * Created by jackeagle on 31/12/17.
 */

public class SystemAnimation extends SettingsPreferenceFragment
            implements Preference.OnPreferenceChangeListener{


        private static final String TAG = "TrafficSettingsFragment";
        private static final String ACTIVITY_OPEN = "activity_open";
        private static final String ACTIVITY_CLOSE = "activity_close";
        private static final String TASK_OPEN = "task_open";
        private static final String TASK_CLOSE = "task_close";
        private static final String TASK_MOVE_TO_FRONT = "task_move_to_front";
        private static final String TASK_MOVE_TO_BACK = "task_move_to_back";
        private static final String ANIMATION_NO_OVERRIDE = "animation_no_override";
        private static final String WALLPAPER_OPEN = "wallpaper_open";
        private static final String WALLPAPER_CLOSE = "wallpaper_close";
        private static final String WALLPAPER_INTRA_OPEN = "wallpaper_intra_open";
        private static final String WALLPAPER_INTRA_CLOSE = "wallpaper_intra_close";
        private static final String TASK_OPEN_BEHIND = "task_open_behind";
        private static final String POWER_MENU_ANIMATIONS = "power_menu_animations";
        private static final String KEY_LISTVIEW_ANIMATION = "listview_animation";
        private static final String KEY_LISTVIEW_INTERPOLATOR = "listview_interpolator";

        ListPreference mActivityOpenPref;
        ListPreference mActivityClosePref;
        ListPreference mTaskOpenPref;
        ListPreference mTaskClosePref;
        ListPreference mTaskMoveToFrontPref;
        ListPreference mTaskMoveToBackPref;
        ListPreference mWallpaperOpen;
        ListPreference mWallpaperClose;
        ListPreference mWallpaperIntraOpen;
        ListPreference mWallpaperIntraClose;
        ListPreference mTaskOpenBehind;
        SwitchPreference mAnimNoOverride;
        private ListPreference mPowerMenuAnimations;
        private ListPreference mListViewAnimation;
        private ListPreference mListViewInterpolator;

        private int[] mAnimations;
        private String[] mAnimationsStrings;
        private String[] mAnimationsNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.system_animation);

            PreferenceScreen prefs = getPreferenceScreen();
            ContentResolver resolver = getActivity().getContentResolver();
            mAnimations = AwesomeAnimationHelper.getAnimationsList();
            int animqty = mAnimations.length;
            mAnimationsStrings = new String[animqty];
            mAnimationsNum = new String[animqty];
            for (int i = 0; i < animqty; i++) {
                mAnimationsStrings[i] = AwesomeAnimationHelper.getProperName(getActivity(), mAnimations[i]);
                mAnimationsNum[i] = String.valueOf(mAnimations[i]);
            }

            //mAnimNoOverride = (SwitchPreference) findPreference(ANIMATION_NO_OVERRIDE);
            //mAnimNoOverride.setChecked(Settings.System.getBoolean(mContentRes,
            //        Settings.System.ANIMATION_CONTROLS_NO_OVERRIDE, false));

            mActivityOpenPref = (ListPreference) findPreference(ACTIVITY_OPEN);
            mActivityOpenPref.setOnPreferenceChangeListener(this);
            mActivityOpenPref.setSummary(getProperSummary(mActivityOpenPref));
            mActivityOpenPref.setEntries(mAnimationsStrings);
            mActivityOpenPref.setEntryValues(mAnimationsNum);

            mActivityClosePref = (ListPreference) findPreference(ACTIVITY_CLOSE);
            mActivityClosePref.setOnPreferenceChangeListener(this);
            mActivityClosePref.setSummary(getProperSummary(mActivityClosePref));
            mActivityClosePref.setEntries(mAnimationsStrings);
            mActivityClosePref.setEntryValues(mAnimationsNum);

            mTaskOpenPref = (ListPreference) findPreference(TASK_OPEN);
            mTaskOpenPref.setOnPreferenceChangeListener(this);
            mTaskOpenPref.setSummary(getProperSummary(mTaskOpenPref));
            mTaskOpenPref.setEntries(mAnimationsStrings);
            mTaskOpenPref.setEntryValues(mAnimationsNum);

            mTaskClosePref = (ListPreference) findPreference(TASK_CLOSE);
            mTaskClosePref.setOnPreferenceChangeListener(this);
            mTaskClosePref.setSummary(getProperSummary(mTaskClosePref));
            mTaskClosePref.setEntries(mAnimationsStrings);
            mTaskClosePref.setEntryValues(mAnimationsNum);

            mTaskMoveToFrontPref = (ListPreference) findPreference(TASK_MOVE_TO_FRONT);
            mTaskMoveToFrontPref.setOnPreferenceChangeListener(this);
            mTaskMoveToFrontPref.setSummary(getProperSummary(mTaskMoveToFrontPref));
            mTaskMoveToFrontPref.setEntries(mAnimationsStrings);
            mTaskMoveToFrontPref.setEntryValues(mAnimationsNum);

            mTaskMoveToBackPref = (ListPreference) findPreference(TASK_MOVE_TO_BACK);
            mTaskMoveToBackPref.setOnPreferenceChangeListener(this);
            mTaskMoveToBackPref.setSummary(getProperSummary(mTaskMoveToBackPref));
            mTaskMoveToBackPref.setEntries(mAnimationsStrings);
            mTaskMoveToBackPref.setEntryValues(mAnimationsNum);

            mWallpaperOpen = (ListPreference) findPreference(WALLPAPER_OPEN);
            mWallpaperOpen.setOnPreferenceChangeListener(this);
            mWallpaperOpen.setSummary(getProperSummary(mWallpaperOpen));
            mWallpaperOpen.setEntries(mAnimationsStrings);
            mWallpaperOpen.setEntryValues(mAnimationsNum);

            mWallpaperClose = (ListPreference) findPreference(WALLPAPER_CLOSE);
            mWallpaperClose.setOnPreferenceChangeListener(this);
            mWallpaperClose.setSummary(getProperSummary(mWallpaperClose));
            mWallpaperClose.setEntries(mAnimationsStrings);
            mWallpaperClose.setEntryValues(mAnimationsNum);

            mWallpaperIntraOpen = (ListPreference) findPreference(WALLPAPER_INTRA_OPEN);
            mWallpaperIntraOpen.setOnPreferenceChangeListener(this);
            mWallpaperIntraOpen.setSummary(getProperSummary(mWallpaperIntraOpen));
            mWallpaperIntraOpen.setEntries(mAnimationsStrings);
            mWallpaperIntraOpen.setEntryValues(mAnimationsNum);

            mWallpaperIntraClose = (ListPreference) findPreference(WALLPAPER_INTRA_CLOSE);
            mWallpaperIntraClose.setOnPreferenceChangeListener(this);
            mWallpaperIntraClose.setSummary(getProperSummary(mWallpaperIntraClose));
            mWallpaperIntraClose.setEntries(mAnimationsStrings);
            mWallpaperIntraClose.setEntryValues(mAnimationsNum);

            mTaskOpenBehind = (ListPreference) findPreference(TASK_OPEN_BEHIND);
            mTaskOpenBehind.setSummary(getProperSummary(mTaskOpenBehind));
            mTaskOpenBehind.setEntries(mAnimationsStrings);
            mTaskOpenBehind.setEntryValues(mAnimationsNum);
            mTaskOpenBehind.setOnPreferenceChangeListener(this);

            mPowerMenuAnimations = (ListPreference) findPreference(POWER_MENU_ANIMATIONS);
            mPowerMenuAnimations.setValue(String.valueOf(Settings.System.getInt(
                    getContentResolver(), Settings.System.POWER_MENU_ANIMATIONS, 0)));
            mPowerMenuAnimations.setSummary(mPowerMenuAnimations.getEntry());
            mPowerMenuAnimations.setOnPreferenceChangeListener(this);

            // ListView Animations
            mListViewAnimation = (ListPreference) findPreference(KEY_LISTVIEW_ANIMATION);
            int listviewanimation =Settings.System.getInt(resolver,
                    Settings.System.LISTVIEW_ANIMATION, 0);
            mListViewAnimation.setValue(String.valueOf(listviewanimation));
            mListViewAnimation.setSummary(mListViewAnimation.getEntry());
            mListViewAnimation.setOnPreferenceChangeListener(this);

            mListViewInterpolator = (ListPreference) findPreference(KEY_LISTVIEW_INTERPOLATOR);
            int listviewinterpolator = Settings.System.getInt(resolver,
                    Settings.System.LISTVIEW_INTERPOLATOR, 0);
            mListViewInterpolator.setValue(String.valueOf(listviewinterpolator));
            mListViewInterpolator.setSummary(mListViewInterpolator.getEntry());
            mListViewInterpolator.setOnPreferenceChangeListener(this);
            mListViewInterpolator.setEnabled(listviewanimation > 0);
        }

        //@Override
        //public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
        //                                     Preference preference) {
        //   if (preference == mAnimNoOverride) {
        //        Settings.System.putBoolean(mContentRes,
        //                Settings.System.ANIMATION_CONTROLS_NO_OVERRIDE,
        //                    mAnimNoOverride.isChecked());
        //        return true;
        //    }
        //    return false;
        //}

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mActivityOpenPref) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[0], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mActivityClosePref) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[1], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mTaskOpenPref) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[2], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mTaskClosePref) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[3], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mTaskMoveToFrontPref) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[4], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mTaskMoveToBackPref) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[5], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mWallpaperOpen) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[6], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mWallpaperClose) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[7], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mWallpaperIntraOpen) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[8], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mWallpaperIntraClose) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[9], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mTaskOpenBehind) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.ACTIVITY_ANIMATION_CONTROLS[10], val);
            preference.setSummary(getProperSummary(preference));
            return true;
        } else if (preference == mPowerMenuAnimations) {
            Settings.System.putInt(getContentResolver(), Settings.System.POWER_MENU_ANIMATIONS,
                    Integer.valueOf((String) newValue));
            mPowerMenuAnimations.setValue(String.valueOf(newValue));
            mPowerMenuAnimations.setSummary(mPowerMenuAnimations.getEntry());
            return true;
        } else if (preference == mListViewAnimation) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.LISTVIEW_ANIMATION, val);
            mListViewAnimation.setSummary(mListViewAnimation.getEntry());
            mListViewInterpolator.setEnabled(val > 0);
            return true;
        } else if (preference == mListViewInterpolator) {
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(resolver,
                    Settings.System.LISTVIEW_INTERPOLATOR, val);
            mListViewInterpolator.setSummary(mListViewInterpolator.getEntry());
            return true;
        }
        return false;
        }

        private String getProperSummary(Preference preference) {
            String mString = "";
            if (preference == mActivityOpenPref) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[0];
            } else if (preference == mActivityClosePref) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[1];
            } else if (preference == mTaskOpenPref) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[2];
            } else if (preference == mTaskClosePref) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[3];
            } else if (preference == mTaskMoveToFrontPref) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[4];
            } else if (preference == mTaskMoveToBackPref) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[5];
            } else if (preference == mWallpaperOpen) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[6];
            } else if (preference == mWallpaperClose) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[7];
            } else if (preference == mWallpaperIntraOpen) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[8];
            } else if (preference == mWallpaperIntraClose) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[9];
            } else if (preference == mTaskOpenBehind) {
                mString = Settings.System.ACTIVITY_ANIMATION_CONTROLS[10];
            }

            int mNum = Settings.System.getInt(getActivity().getContentResolver(), mString, 0);
            return mAnimationsStrings[mNum];
        }


         @Override
         public int getMetricsCategory() {
            return MetricsProto.MetricsEvent.BLISSIFY;
         }

}
