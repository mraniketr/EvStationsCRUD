package com.aniket.app.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVSTATION")
public class evStation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long station_id;
	@Column(name = "station_name")
	private String station_name;
	@Column(name = "station_image")
	@Lob
	private String station_image;
	@Column(name = "station_pricing")
	private Float station_pricing;
	@Column(name = "station_address")
	private String station_address;

	public evStation() {

	}

	public evStation(Long station_id, String station_name, String station_image, Float station_pricing,
			String station_address) {
		super();
		this.station_id = station_id;
		this.station_name = station_name;
		this.station_image = station_image;
		this.station_pricing = station_pricing;
		this.station_address = station_address;
	}

	public evStation(evStationRequest req) {
		super();
		this.station_name = req.getStation_name();
		try {
			this.station_image = new String(req.getImage()[0].getBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.station_image = null;
		}
		this.station_pricing = req.getStation_pricing();
		this.station_address = req.getStation_address();
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
