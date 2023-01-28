package com.aniket.app.model;

import org.springframework.web.multipart.MultipartFile;


public class evStationRequest {
	private MultipartFile image[];
	private Long station_id;
	private String station_name;
	private String station_image;
	private Float station_pricing;
	private String station_address;

	public MultipartFile[] getImage() {
		return image;
	}

	public void setImage(MultipartFile[] image) {
		this.image = image;
	}

	public Long getStation_id() {
		return station_id;
	}

	public void setStation_id(Long station_id) {
		this.station_id = station_id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public String getStation_image() {
		return station_image;
	}

	public void setStation_image(String station_image) {
		this.station_image = station_image;
	}

	public Float getStation_pricing() {
		return station_pricing;
	}

	public void setStation_pricing(Float station_pricing) {
		this.station_pricing = station_pricing;
	}

	public String getStation_address() {
		return station_address;
	}

	public void setStation_address(String station_address) {
		this.station_address = station_address;
	}

}
