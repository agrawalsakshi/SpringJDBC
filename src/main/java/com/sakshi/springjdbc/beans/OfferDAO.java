package com.sakshi.springjdbc.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offerDAO")
public class OfferDAO {

	
	private NamedParameterJdbcTemplate jdbc;
	
	

	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate( jdbc);
	}
	
	
	
	public List<Offer>  getOffers(){

		return this.jdbc.query("select * from offers",new RowMapper<Offer>(){

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				return offer;
			}
			
		});
	}
	
public Offer  getOffer(int id){
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return (Offer) jdbc.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>(){

			
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				return offer;
			}
			
		});
	}

public boolean deleteOffer(int id){
	
	MapSqlParameterSource param = new MapSqlParameterSource("id",id);
	
	return jdbc.update("delete from offers where id = :id", param) ==1;
	
}

public boolean createOffer(Offer offer){
	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(offer);
	return  jdbc.update("insert into offers (name, text, email) values(:name , :text, :email)",param) ==1;
}

public boolean updateOffer(Offer offer){
	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(offer);
	
	return jdbc.update("update offers set name =:name, text =:text ,email=:email where id=:id", param) == 1;
}
}
