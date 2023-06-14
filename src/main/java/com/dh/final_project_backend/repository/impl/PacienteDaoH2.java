package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.Paciente;
import com.dh.final_project_backend.entity.Turno;
import com.dh.final_project_backend.repository.IDao;
import com.dh.final_project_backend.repository.configuration.ConnectionJDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDaoH2 implements IDao<Paciente> {

    private final static Logger logger = LogManager.getLogger(PacienteDaoH2.class);
    @Autowired
    private ConnectionJDBC connectionJDBC;

    @Autowired
    private DomicilioDaoH2 domicilioDaoH2;

    @Autowired
    private TurnoDaoH2 turnoDaoH2;

    public PacienteDaoH2(ConnectionJDBC connectionJDBC) {
        this.connectionJDBC = connectionJDBC;
    }

    @Override
    public Paciente guardar(Paciente paciente) {

        logger.info("Guardando paciente: " + paciente.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String insert_sql = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DOMICILIO_ID, DNI, FECHA_ALTA, TURNO_ID) VALUES(?,?,?,?,?,?)";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setInt(3, paciente.getDomicilio().getId());
            ps.setInt(4, paciente.getDni());
            ps.setDate(5, java.sql.Date.valueOf(paciente.getFechaAlta()));
            ps.setInt(6,paciente.getTurno().getId());

            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            if(ids.next()){
                paciente.setId(ids.getInt(1));
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
        logger.info("Paciente guardado: " + paciente.toString());
        System.out.println(paciente);
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        logger.info("Buscando domicilio con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;
        Paciente paciente = null;

        try{
            connection = connectionJDBC.connectToDB();

            String select_sql = "SELECT * FROM PACIENTES WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idPaciente = rs.getInt(1);
                String nombrePaciente = rs.getString(2);
                String apellidoPaciente = rs.getString(3);
                Domicilio domicilioPaciente = domicilioDaoH2.buscar(rs.getInt(4));
                Integer dniPaciente = rs.getInt(5);
                LocalDate fechaAltaPaciente = rs.getDate(6).toLocalDate();
                Turno turnoPaciente = turnoDaoH2.buscar(rs.getInt(7));

                paciente = new Paciente(idPaciente, nombrePaciente, apellidoPaciente, domicilioPaciente, dniPaciente, fechaAltaPaciente, turnoPaciente);
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
        logger.info("Paciente encontrado: " + paciente.toString());
        System.out.println(paciente);
        return paciente;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        logger.info("Actualizando paciente: " + paciente.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String update_sql = "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DOMICILIO_ID=?, DNI=?, FECHA_ALTA=?, TURNO_ID=? WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(update_sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setInt(3, paciente.getDomicilio().getId());
            ps.setInt(4, paciente.getDni());
            ps.setDate(5, java.sql.Date.valueOf(paciente.getFechaAlta()));
            ps.setInt(6,paciente.getTurno().getId());
            ps.setInt(7, paciente.getId());

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
        logger.info("Paciente actualizado: " + paciente.toString());
        System.out.println(paciente);
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando paciente con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String delete_sql = "DELETE FROM PACIENTES WHERE ID=?";

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
        logger.info("Paciente eliminado con id: " + id);
        System.out.println("Paciente eliminado con id: " + id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.info("Buscando domicilios...");
        Connection connection = null;
        PreparedStatement ps = null;
        Paciente paciente = null;
        List<Paciente> pacientes = new ArrayList<>();

        try{
            connection = connectionJDBC.connectToDB();

            String select_all_sql = "SELECT * FROM PACIENTES";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_all_sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idPaciente = rs.getInt(1);
                String nombrePaciente = rs.getString(2);
                String apellidoPaciente = rs.getString(3);
                Domicilio domicilioPaciente = domicilioDaoH2.buscar(rs.getInt(4));
                Integer dniPaciente = rs.getInt(5);
                LocalDate fechaAltaPaciente = rs.getDate(6).toLocalDate();
                Turno turnoPaciente = turnoDaoH2.buscar(rs.getInt(7));

                paciente = new Paciente(idPaciente, nombrePaciente, apellidoPaciente, domicilioPaciente, dniPaciente, fechaAltaPaciente, turnoPaciente);

                pacientes.add(paciente);

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
        logger.info("Pacientes encontrados...");
        System.out.println(pacientes);
        return pacientes;
    }
}
