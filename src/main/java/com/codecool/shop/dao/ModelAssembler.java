package com.codecool.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ModelAssembler <T> {

    T assemble(ResultSet resultSet) throws SQLException;

}
