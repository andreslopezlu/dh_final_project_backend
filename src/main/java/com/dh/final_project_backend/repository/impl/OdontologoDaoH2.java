package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.Odontologo;
import com.dh.final_project_backend.repository.IDao;
import com.dh.final_project_backend.repository.configuration.ConnectionJDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final static Logger logger = LogManager.getLogger(OdontologoDaoH2.class);
    @Autowired
    private ConnectionJDBC connectionJDBC;

    public OdontologoDaoH2(ConnectionJDBC connectionJDBC) {
        this.connectionJDBC = connectionJDBC;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Guardando odontologo: " + odontologo.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String insert_sql = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES(?,?,?)";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, odontologo.getNombre());
            ps.setString(2, odontologo.getApellido());
            ps.setString(3, odontologo.getMatricula());

            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            if(ids.next()){
                odontologo.setId(ids.getInt(1));
            }

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {

                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Odontologo guardado: " + odontologo.toString());
        System.out.println(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        logger.info("Buscando odontologo con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;
        Odontologo odontologo = null;

        try{
            connection = connectionJDBC.connectToDB();

            String select_sql = "SELECT * FROM ODONTOLOGOS WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idOdontologo = rs.getInt(1);
                String nombreOdontologo = rs.getString(2);
                String apellidoOdontologo = rs.getString(3);
                String matriculaOdontologo = rs.getString(4);

                odontologo = new Odontologo(idOdontologo, nombreOdontologo, apellidoOdontologo, matriculaOdontologo);
            }

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Odontologo encontrado: " + odontologo.toString());
        System.out.println(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        logger.info("Actualizando odontologo: " + odontologo.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String update_sql = "UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=? WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(update_sql);
            ps.setString(1,odontologo.getNombre());
            ps.setString(2,odontologo.getApellido());
            ps.setString(3, odontologo.getMatricula());
            ps.setInt(4, odontologo.getId());

            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Odontologo actualizado: " + odontologo.toString());
        System.out.println(odontologo);
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando odontologo con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String delete_sql = "DELETE FROM ODONTOLOGOS WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(delete_sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Odontologo eliminado con id: " + id);
        System.out.println("Odontologo eliminado con id: " + id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Buscando odontologos...");
        Connection connection = null;
        PreparedStatement ps = null;
        Odontologo odontologo = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = connectionJDBC.connectToDB();

            String select_all_sql = "SELECT * FROM ODONTOLOGOS";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_all_sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idOdontologo = rs.getInt(1);
                String nombreOdontologo = rs.getString(2);
                String apellidoOdontologo = rs.getString(3);
                Integer matriculaOdontologo = rs.getInt(4);

                odontologo = new Odontologo(idOdontologo, nombreOdontologo, apellidoOdontologo, matriculaOdontologo);

                odontologos.add(odontologo);

            }

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Odontologos encontrados...");
        System.out.println(odontologos);
        return odontologos;
    }
}
