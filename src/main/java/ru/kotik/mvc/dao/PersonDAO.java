package ru.kotik.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kotik.mvc.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    /*private static int PEOPLE_COUNT;*/

    /*-- создали в конфигах --*/
    /*private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "email_1@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 30, "email_2@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 33, "email_3@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 40, "email_4@mail.ru"));
    }*/

    public List<Person> index() {
        /*return people;*/

        /*-- перехало в JdbcTemplate в SpringConfig --*/
        /*List<Person> people = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = stm.executeQuery(SQL);
            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;*/

        // кастомный вариант маппера можно не использоват, т.к. названия полей одиннаковы
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class) /*new PersonMapper()*/);
    }

    public Person show(int id) {
//        return people.stream()
//                .filter(person -> person.getId() == id)
//                .findAny()
//                .orElse(null);
//        return null;

        /*-- переехало в JdbcTemplate --*/
        /*Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;*/
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream()
                .findAny().orElse(null);
    }

    public void save(Person person) {
        /*person.setId(++PEOPLE_COUNT);
        people.add(person);*/

        /*try {
            Statement stm = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 +
                    ",'" + person.getName() + "'," +
                    person.getAge() +
                    ",'" + person.getEmail() + "');";
            stm.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/



        /*try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES (1, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person personUpdated) {
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(personUpdated.getName());
//        personToBeUpdated.setAge(personUpdated.getAge());
//        personToBeUpdated.setEmail(personUpdated.getEmail());



        /*try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, personUpdated.getName());
            preparedStatement.setInt(2, personUpdated.getAge());
            preparedStatement.setString(3, personUpdated.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", personUpdated.getName(), personUpdated.getAge(), personUpdated.getEmail(), id);
    }

    public void delete(int id) {
        /*try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
