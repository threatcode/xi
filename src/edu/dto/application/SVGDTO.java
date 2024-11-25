package edu.dto.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class SVGDTO {

	private HashSet<String> shiftList=new HashSet<String>();
	private Multimap<String,String> shiftVersionList= HashMultimap.create();
	private Multimap<String,String> versionGroupList= HashMultimap.create();
	private Multimap<String,String> groupEQList= HashMultimap.create();
	private Multimap<String,String> svgAvailableSeat= HashMultimap.create();
	private Multimap<String,String> svgTotalSeat= HashMultimap.create();
	private Multimap<String,String> svgTotalSeat1= HashMultimap.create();
	
	public Multimap<String, String> getSvgTotalSeat1() {
		return svgTotalSeat1;
	}
	public void setSvgTotalSeat1(Multimap<String, String> svgTotalSeat1) {
		this.svgTotalSeat1 = svgTotalSeat1;
	}
	public Multimap<String, String> getSvgTotalSeat() {
		return svgTotalSeat;
	}
	public void setSvgTotalSeat(Multimap<String, String> svgTotalSeat) {
		this.svgTotalSeat = svgTotalSeat;
	}
	public HashSet<String> getShiftList() {
		return shiftList;
	}
	public void setShiftList(HashSet<String> shiftList) {
		this.shiftList = shiftList;
	}
	public Multimap<String, String> getShiftVersionList() {
		return shiftVersionList;
	}
	public void setShiftVersionList(Multimap<String, String> shiftVersionList) {
		this.shiftVersionList = shiftVersionList;
	}
	public Multimap<String, String> getVersionGroupList() {
		return versionGroupList;
	}
	public void setVersionGroupList(Multimap<String, String> versionGroupList) {
		this.versionGroupList = versionGroupList;
	}
	
	public Multimap<String, String> getGroupEQList() {
		return groupEQList;
	}
	public void setGroupEQList(Multimap<String, String> groupEQList) {
		this.groupEQList = groupEQList;
	}
	
	public Multimap<String, String> getSvgAvailableSeat() {
		return svgAvailableSeat;
	}
	public void setSvgAvailableSeat(Multimap<String, String> svgAvailableSeat) {
		this.svgAvailableSeat = svgAvailableSeat;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		HashSet<String> shiftList=new  HashSet<String>();
		shiftList.add("1");
		shiftList.add("1");
		shiftList.add("1");
		shiftList.add("1");
		shiftList.add("2");
		System.out.println(shiftList.size());
		
		HashMap<String, List<String>> mapList=new  HashMap<String, List<String>>();
		//mapList.put("a", "shakil");
		List<String> abc=(List<String>) (mapList.get("a")==null?new ArrayList<String>():mapList.get("a").add("shakil"));
		
		mapList.put("a",abc);
		System.out.println(mapList.get("a"));
	}
	
}
