package com.example.demo.repository.row_mappers;

import com.example.demo.domain.BusSales;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class BusSalesRowMapper implements ResultSetExtractor<List<BusSales>> {

  public List<BusSales> extractData(ResultSet rs) throws SQLException, DataAccessException {
    Map<Long, BusSales> map = new HashMap<>();
    BusSales busSales;
    while (rs.next()) {
      Long busId = rs.getLong("bus_id");
          busSales = map.get(busId);
      if(busSales == null){
        busSales = new BusSales(busId, new ArrayList<>());
      }
      busSales.getSales().add(rs.getBigDecimal("purchase_amount"));
      map.put(busId, busSales);
    }
    ArrayList<BusSales> busSales1 = new ArrayList<>();
    busSales1.addAll(map.values());
    return busSales1;
  }
}
