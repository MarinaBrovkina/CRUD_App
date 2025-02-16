//package org.example.crudApp.dao;
//
//import org.example.crudApp.model.Person;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//public class PersonMapper implements RowMapper<Person> {
//
//    //т.к название полей в классе Person совпадает с названием колонки в БД, можно не писать свой RowMapper,
//    // а использовать new BeanPropertyRowMapper<>(Person.class)
//    @Override
//    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//        Person person = new Person();
//        person.setId(resultSet.getInt("id"));
//        person.setName(resultSet.getString("name"));
//        person.setEmail(resultSet.getString("email"));
//        person.setAge(resultSet.getInt("age"));
//        return person;
//    }
//}
//
