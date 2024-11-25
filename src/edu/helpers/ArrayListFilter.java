package edu.helpers;

import java.util.ArrayList;

import edu.dto.application.ChoiceDTO;

public class ArrayListFilter {

	
	public ArrayList<ChoiceDTO> getWebChoiceList(ArrayList<ChoiceDTO> choiceList){
		
		ArrayList<ChoiceDTO> webOnlyChoiceList=new ArrayList<ChoiceDTO>();
		for(ChoiceDTO choice:choiceList){
			if(choice.getVia().equalsIgnoreCase("W"))
				webOnlyChoiceList.add(choice);
		}
		
		return webOnlyChoiceList;
	}
}
