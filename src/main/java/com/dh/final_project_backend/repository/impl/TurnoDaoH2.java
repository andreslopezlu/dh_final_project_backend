package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.Odontologo;
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
public class TurnoDaoH2 implements IDao<Turno> {

    private final static Logger logger = LogManager.getLogger(TurnoDaoH2.class);

    @Autowired
    private ConnectionJDBC connectionJDBC;

    @Autowired
    private PacienteDaoH2 pacienteDaoH2;

    @Autowired
    private OdontologoDaoH2 odontologoDaoH2;

    public TurnoDaoH2(ConnectionJDBC connectionJDBC) {
        this.connectionJDBC = connectionJDBC;
    }

    @Override
    public Turno guardar(Turno turno) {
        logger.info("Guardando turno: " + turno.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String insert_sql = "INSERT INTO TURNOS (PACIENTE_ID, ODONTOLOGO_ID, FECHA) VALUES(?,?,?)";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, turno.getPaciente().getId());
            ps.setInt(2, turno.getOdontologo().getId());
            ps.setDate(3, java.sql.Date.valueOf(turno.getFecha()));

            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            if(ids.next()){
                turno.setId(ids.getInt(1));
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
        logger.info("Turno guardado: " + turno.toString());
        System.out.println(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        logger.info("Buscando turno con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;
        Turno turno = null;

        try{
            connection = connectionJDBC.connectToDB();

            String select_sql = "SELECT * FROM TURNOS WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idTurno = rs.getInt(1);
                Paciente pacienteTurno = pacienteDaoH2.buscar(rs.getInt(2));
                Odontologo odontologoTurno = odontologoDaoH2.buscar(rs.getInt(3));
                LocalDate fechaTurno = rs.getDate(4).toLocalDate();

                turno = new Turno(idTurno, pacienteTurno, odontologoTurno, fechaTurno);
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
        logger.info("Turno encontrado: " + turno.toString());
        System.out.println(turno);
        return turno;
    }

    @Override
    public Turno actualizar(Turno turno) {
        logger.info("Actualizando turno: " + turno.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String update_sql = "UPDATE TURNOS SET PACIENTE_ID=?, ODONTOLOGO_ID=?, FECHA=? WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(update_sql);
            ps.setInt(1, turno.getPaciente().getId());
            ps.setInt(2, turno.getOdontologo().getId());
            ps.setDate(3, java.sql.Date.valueOf(turno.getFecha()));

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
        logger.info("Turno actualizado: " + turno.toString());
        System.out.println(turno);
        return turno;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando turno con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String delete_sql = "DELETE FROM TURNOS WHERE ID=?";

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
        logger.info("Turno eliminado con id: " + id);
        System.out.println("Turno eliminado con id: " + id);

    }

    @Override
    public List<Turno> buscarTodos() {
        logger.info("Buscando turnos...");
        Connection connection = null;
        PreparedStatement ps = null;
        Turno turno = null;
        List<Turno> turnos = new ArrayList<>();

        try {
            connection = connectionJDBC.connectToDB();

            String select_all_sql = "SELECT * FROM TURNOS";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_all_sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer idTurno = rs.getInt(1);
                Paciente pacienteTurno = pacienteDaoH2.buscar(rs.getInt(2));
                Odontologo odontologoTurno = odontologoDaoH2.buscar(rs.getInt(3));
                LocalDate fechaTurno = rs.getDate(4).toLocalDate();

                turno = new Turno(idTurno, pacienteTurno, odontologoTurno, fechaTurno);

                turnos.add(turno);

            }

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e) {
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Turnos encontrados...");
        System.out.println(turnos);
        return turnos;
    }
}
