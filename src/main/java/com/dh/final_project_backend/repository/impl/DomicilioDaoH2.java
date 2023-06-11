package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.repository.IDao;
import com.dh.final_project_backend.repository.configuration.ConnectionJDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDaoH2 implements IDao<Domicilio> {
    private final static Logger logger = LogManager.getLogger(DomicilioDaoH2.class);
    @Autowired
    private ConnectionJDBC connectionJDBC;
    public DomicilioDaoH2() {
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        logger.info("Guardando domicilio: " + domicilio.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String insert_sql = "INSERT INTO DOMICILIOS (CALLE, NUMERO, PROVINCIA, PAIS, CODIGO_POSTAL) VALUES(?,?,?,?,?)";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
            ps.setString(3, domicilio.getProvincia());
            ps.setString(4, domicilio.getPais());
            ps.setInt(5, domicilio.getCodigoPostal());

            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            if(ids.next()){
                domicilio.setId(ids.getInt(1));
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
        logger.info("Domicilio guardado: " + domicilio.toString());
        System.out.println(domicilio);
        return domicilio;
    }

    @Override
    public Domicilio buscar(Integer id) {
        logger.info("Buscando domicilio con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;
        Domicilio domicilio = null;

        try{
            connection = connectionJDBC.connectToDB();

            String select_sql = "SELECT * FROM DOMICILIOS WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idDomicilio = rs.getInt(1);
                String calleDomicilio = rs.getString(2);
                Integer numeroDomicilio = rs.getInt(3);
                String provinciaDomicilio = rs.getString(4);
                String paisDomicilio = rs.getString(5);
                Integer codigoPostalDomicilio = rs.getInt(6);

                domicilio = new Domicilio(idDomicilio, calleDomicilio, numeroDomicilio, provinciaDomicilio, paisDomicilio, codigoPostalDomicilio);
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
        logger.info("Domicilio encontrado: " + domicilio.toString());
        System.out.println(domicilio);
        return domicilio;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        logger.info("Actualizando domicilio: " + domicilio.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String update_sql = "UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, PROVINCIA=?, PAIS=?, CODIGO_POSTAL=? WHERE ID=?";

            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(update_sql);
            ps.setString(1,domicilio.getCalle());
            ps.setInt(2,domicilio.getNumero());
            ps.setString(3, domicilio.getProvincia());
            ps.setString(4, domicilio.getPais());
            ps.setString(5, domicilio.getProvincia());
            ps.setInt(5, domicilio.getId());

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
        logger.info("Domicilio actualizado: " + domicilio.toString());
        System.out.println(domicilio);
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {

        logger.info("Buscando domicilio con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String delete_sql = "SELECT * FROM DOMICILIOS WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(delete_sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

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
        logger.info("Domicilio eliminado con id: " + id);
        System.out.println("Domicilio eliminado con id: " + id);
    }

    @Override
    public List<Domicilio> buscarTodos() {
        logger.info("Buscando domicilios...");
        Connection connection = null;
        PreparedStatement ps = null;
        Domicilio domicilio = null;
        List<Domicilio> domicilios = new ArrayList<Domicilio>();

        try{
            connection = connectionJDBC.connectToDB();

            String select_all_sql = "SELECT * FROM DOMICILIOS";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_all_sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idDomicilio = rs.getInt(1);
                String calleDomicilio = rs.getString(2);
                Integer numeroDomicilio = rs.getInt(3);
                String provinciaDomicilio = rs.getString(4);
                String paisDomicilio = rs.getString(5);
                Integer codigoPostalDomicilio = rs.getInt(6);

                domicilio = new Domicilio(idDomicilio, calleDomicilio, numeroDomicilio, provinciaDomicilio, paisDomicilio, codigoPostalDomicilio);

                domicilios.add(domicilio);

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
        logger.info("Domicilios encontrados...");
        System.out.println(domicilios);
        return domicilios;
    }

    @Override
    public void mostrarTodos() {
        logger.info("Mostrando domicilios...");
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String select_all_sql = "SELECT * FROM DOMICILIOS";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_all_sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getInt(1) + ";" + rs.getString(2) + ";" + rs.getInt(3) + ";" + rs.getString(4) + ";" + rs.getString(5) + ";" + rs.getInt(6));
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
        logger.info("Domicilios encontrados...");
        System.out.println("Domicilios encontrados...");
    }
}
