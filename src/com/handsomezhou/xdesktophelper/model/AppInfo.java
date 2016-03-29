package com.handsomezhou.xdesktophelper.model;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import android.graphics.drawable.Drawable;

import com.handsomezhou.xdesktophelper.util.CommonUtil;
import com.pinyinsearch.model.PinyinSearchUnit;

public class AppInfo extends BaseAppInfo{
	public enum SearchByType{
		SearchByNull,SearchByLabel,
	}
	
	private String mSortKey; // as the sort key word
	private PinyinSearchUnit mLabelPinyinSearchUnit;// save the mLabel converted to Pinyin characters.
	private SearchByType mSearchByType; // Used to save the type of search
	private StringBuffer mMatchKeywords;// Used to save the type of Match Keywords.(label)
	private int mMatchStartIndex;		//the match start  position of mMatchKeywords in original string(label).
	private int mMatchLength;			//the match length of mMatchKeywords in original string(name or phoneNumber).
	private long mSetToTop;
	private long mCommonWeights;       //Common weights 


    public AppInfo() {
		super();
		setLabelPinyinSearchUnit(new PinyinSearchUnit());
		setSearchByType(SearchByType.SearchByNull);
		setMatchKeywords(new StringBuffer());
		getMatchKeywords().delete(0, getMatchKeywords().length());
		setMatchStartIndex(-1);
		setMatchLength(0);
		setSetToTop(0);
		setCommonWeights(0);
	}
	
	public AppInfo(String label, Drawable icon, String packageName,String name) {
		super(label, icon, packageName,name);
		setLabelPinyinSearchUnit(new PinyinSearchUnit(label));
		setSearchByType(SearchByType.SearchByNull);
		setMatchKeywords(new StringBuffer());
		getMatchKeywords().delete(0, getMatchKeywords().length());
		setMatchStartIndex(-1);
		setMatchLength(0);
		setSetToTop(0);
	    setCommonWeights(0);
	}

	private static Comparator<Object> mChineseComparator = Collator.getInstance(Locale.CHINA);
	
	public static Comparator<AppInfo> mSortBySortKeyDes = new Comparator<AppInfo>() {

		@Override
		public int compare(AppInfo lhs, AppInfo rhs) {
		
			return mChineseComparator.compare(rhs.mSortKey, lhs.mSortKey);
		}
	};

	public static Comparator<AppInfo> mSortBySortKeyAsc = new Comparator<AppInfo>() {

		@Override
		public int compare(AppInfo lhs, AppInfo rhs) {
			return mChineseComparator.compare(lhs.mSortKey, rhs.mSortKey);
		}
	};
	
	   
	public static Comparator<AppInfo> mSortByDefault = new Comparator<AppInfo>() {

        @Override
        public int compare(AppInfo lhs, AppInfo rhs) {      
            long compareResult=rhs.getSetToTop()-lhs.getSetToTop();
            int compareValue=CommonUtil.compare(compareResult);
            
            if(compareValue==0){
                compareResult=rhs.mCommonWeights-lhs.mCommonWeights;
                compareValue=CommonUtil.compare(compareResult);
                if(0==compareValue){
                    compareValue=mChineseComparator.compare(lhs.mSortKey, rhs.mSortKey);
                }
            }else{
                compareValue=CommonUtil.compare(compareResult);
            }
            
           
            
            return compareValue;
        }
    };
    
	public static Comparator<AppInfo> mSortBySearch = new Comparator<AppInfo>() {

		@Override
		public int compare(AppInfo lhs, AppInfo rhs) {
			int compareMatchStartIndex=(lhs.mMatchStartIndex-rhs.mMatchStartIndex);
			int compareMatchLength=rhs.mMatchLength-lhs.mMatchLength;
			long compareSetToTop=rhs.getSetToTop()-lhs.getSetToTop();
			int compareSetToTopValue=CommonUtil.compare(compareSetToTop);
			long compareCommonWeights=rhs.mCommonWeights-lhs.mCommonWeights;
	        int compareCommonWeightsValue=CommonUtil.compare(compareCommonWeights);
	       
			return ((0!=compareMatchStartIndex)?(compareMatchStartIndex):((0!=compareMatchLength)?(compareMatchLength):((0!=compareSetToTopValue)?(compareSetToTopValue):( (0!=compareCommonWeightsValue)?(compareCommonWeightsValue):(lhs.getLabel().length()-rhs.getLabel().length())))));
		}
	};

	public PinyinSearchUnit getLabelPinyinSearchUnit() {
		return mLabelPinyinSearchUnit;
	}

	public void setLabelPinyinSearchUnit(PinyinSearchUnit labelPinyinSearchUnit) {
		mLabelPinyinSearchUnit = labelPinyinSearchUnit;
	}

	public String getSortKey() {
		return mSortKey;
	}

	public void setSortKey(String sortKey) {
		mSortKey = sortKey;
	}

	public SearchByType getSearchByType() {
		return mSearchByType;
	}

	public void setSearchByType(SearchByType searchByType) {
		mSearchByType = searchByType;
	}

	public StringBuffer getMatchKeywords() {
		return mMatchKeywords;
	}

	public void setMatchKeywords(StringBuffer matchKeywords) {
		mMatchKeywords = matchKeywords;
	}

	public void setMatchKeywords(String matchKeywords) {
		mMatchKeywords.delete(0, mMatchKeywords.length());
		mMatchKeywords.append(matchKeywords);
	}

	public void clearMatchKeywords() {
		mMatchKeywords.delete(0, mMatchKeywords.length());
	}
	
	public int getMatchStartIndex() {
		return mMatchStartIndex;
	}

	public void setMatchStartIndex(int matchStartIndex) {
		mMatchStartIndex = matchStartIndex;
	}

	public int getMatchLength() {
		return mMatchLength;
	}

	public void setMatchLength(int matchLength) {
		mMatchLength = matchLength;
	}
	
	public long getSetToTop() {
		return mSetToTop;
	}

	public void setSetToTop(long setToTop) {
		mSetToTop = setToTop;
	}
	
	public long getCommonWeights() {
        return mCommonWeights;
    }

    public void setCommonWeights(long commonWeights) {
        mCommonWeights = commonWeights;
    }
    
    public String getKey(){
    	return getPackageName()+getName();
    }
}
