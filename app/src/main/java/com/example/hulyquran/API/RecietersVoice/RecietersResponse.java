package com.example.hulyquran.API.RecietersVoice;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RecietersResponse{

	@SerializedName("Radios")
	private List<RadiosItem> radios;

	public void setRadios(List<RadiosItem> radios){
		this.radios = radios;
	}

	public List<RadiosItem> getRadios(){
		return radios;
	}

	@Override
 	public String toString(){
		return 
			"RecietersResponse{" + 
			"radios = '" + radios + '\'' + 
			"}";
		}
}